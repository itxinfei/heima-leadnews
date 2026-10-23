package com.xftt.article.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.xftt.article.service.ApArticleSearchService;
import com.xftt.article.utils.Trie;
import com.xftt.common.common.contants.Contants;
import com.xftt.common.common.contants.ESIndexConstants;
import com.xftt.model.article.dtos.UserSearchDto;
import com.xftt.model.article.pojos.ApArticle;
import com.xftt.model.article.pojos.ApAssociateWords;
import com.xftt.model.article.pojos.ApHotWords;
import com.xftt.model.behavior.pojos.ApBehaviorEntry;
import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.common.enums.AppHttpCodeEnum;
import com.xftt.model.mappers.app.ApArticleMapper;
import com.xftt.model.mappers.app.ApAssociateWordsMapper;
import com.xftt.model.mappers.app.ApBehaviorEntryMapper;
import com.xftt.model.mappers.app.ApHotWordsMapper;
import com.xftt.model.mappers.app.ApUserSearchMapper;
import com.xftt.model.user.pojos.ApUser;
import com.xftt.model.user.pojos.ApUserSearch;
import com.xftt.utils.threadlocal.AppThreadLocalUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("all")
public class ApArticleSearchServiceImpl implements ApArticleSearchService {

    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;

    public ResponseResult getEntryId(UserSearchDto dto) {
        ApUser user = AppThreadLocalUtils.getUser();
        if (user == null && dto.getEquipmentId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        Long userId = null;
        if (user != null) {
            userId = user.getId();
        }
        ApBehaviorEntry apBehaviorEntry = apBehaviorEntryMapper.selectByUserIdOrEquipemntId(userId, dto.getEquipmentId());
        if (apBehaviorEntry == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        return ResponseResult.okResult(apBehaviorEntry.getId());
    }

    @Autowired
    private ApUserSearchMapper apUserSearchMapper;

    @Override
    public ResponseResult findUserSearch(UserSearchDto dto) {
        if (dto.getPageSize() > 50 || dto.getPageSize() < 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        ResponseResult result = getEntryId(dto);
        if (result.getCode() != AppHttpCodeEnum.SUCCESS.getCode()) {
            return result;
        }
        List<ApUserSearch> list = apUserSearchMapper.selectByEntryId((int) result.getData(), dto.getPageSize());
        return ResponseResult.okResult(list);
    }

    @Override
    public ResponseResult delUserSearch(UserSearchDto dto) {
        if (dto.getHisList() == null || dto.getHisList().size() <= 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        ResponseResult result = getEntryId(dto);
        if (result.getCode() != AppHttpCodeEnum.SUCCESS.getCode()) {
            return result;
        }
        List<Integer> ids = dto.getHisList().stream().map(ApUserSearch::getId).collect(Collectors.toList());
        int count = apUserSearchMapper.delUserSearch((int) result.getData(), ids);
        return ResponseResult.okResult(count);
    }

    @Override
    public ResponseResult clearUserSearch(UserSearchDto dto) {
        ResponseResult result = getEntryId(dto);
        if (result.getCode() != AppHttpCodeEnum.SUCCESS.getCode()) {
            return result;
        }
        int count = apUserSearchMapper.clearUserSearch((int) result.getData());
        return ResponseResult.okResult(count);
    }

    @Autowired
    private ApHotWordsMapper apHotWordsMapper;

    @Override
    public ResponseResult hotkeywords(String date) {
        if (StringUtils.isEmpty(date)) {
            date = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        }
        List<ApHotWords> apHotWords = apHotWordsMapper.queryByHotDate(date);
        return ResponseResult.okResult(apHotWords);
    }

    @Autowired
    private ApAssociateWordsMapper apAssociateWordsMapper;

    @Override
    public ResponseResult searchAssociate(UserSearchDto dto) {
        if (dto.getPageSize() > 50 || dto.getPageSize() < 1) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        List<ApAssociateWords> apAssociateWords = apAssociateWordsMapper
                .selectByAssociateWords("%" + dto.getSearchWords() + "%", dto.getPageSize());
        return ResponseResult.okResult(apAssociateWords);
    }

    @Override
    public ResponseResult saveUserSearch(Integer entryId, String searchWords) {
        int count = apUserSearchMapper.checkExist(entryId, searchWords);
        if (count > 0) {
            return ResponseResult.okResult(1);
        }
        ApUserSearch apUserSearch = new ApUserSearch();
        apUserSearch.setEntryId(entryId);
        apUserSearch.setKeyword(searchWords);
        apUserSearch.setStatus(1);
        apUserSearch.setCreatedTime(new Date());
        int insert = apUserSearchMapper.insert(apUserSearch);
        return ResponseResult.okResult(insert);
    }

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Autowired
    private ApArticleMapper apArticleMapper;

    @Override
    public ResponseResult esArticleSearch(UserSearchDto dto) {
        if (dto.getFromIndex() == 0) {
            ResponseResult ret = getEntryId(dto);
            if (ret.getCode() != AppHttpCodeEnum.SUCCESS.getCode()) {
                return ret;
            }
            saveUserSearch((int) ret.getData(), dto.getSearchWords());
        }
        try {
            SearchResponse<ApArticle> searchResult = elasticsearchClient.search(s -> s
                            .index(ESIndexConstants.ARTICLE_INDEX)
                            .from(dto.getFromIndex())
                            .size(dto.getPageSize())
                            .query(q -> q.match(m -> m.field("title").query(dto.getSearchWords()))),
                    ApArticle.class);
            List<ApArticle> resultList = new ArrayList<>();
            for (Hit<ApArticle> hit : searchResult.hits().hits()) {
                ApArticle source = hit.source();
                if (source == null || source.getId() == null) {
                    continue;
                }
                ApArticle apArticle = apArticleMapper.selectById(Long.valueOf(source.getId()));
                if (apArticle != null) {
                    resultList.add(apArticle);
                }
            }
            return ResponseResult.okResult(resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public ResponseResult searchAssocicationV2(UserSearchDto dto) {
        if (dto == null || dto.getPageSize() > 50) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        String assoStr = redisTemplate.opsForValue().get("associte_list");
        List<ApAssociateWords> aw;
        if (StringUtils.isNotEmpty(assoStr)) {
            aw = Contants.objectMapper.readValue(assoStr, new TypeReference<List<ApAssociateWords>>() {
            });
        } else {
            aw = apAssociateWordsMapper.selectAllAssociateWords();
            redisTemplate.opsForValue().set("associte_list", Contants.objectMapper.writeValueAsString(aw));
        }
        Trie t = new Trie();
        for (ApAssociateWords a : aw) {
            t.insert(a.getAssociateWords());
        }
        List<String> ret = t.startWith(dto.getSearchWords());
        List<ApAssociateWords> list = new ArrayList<>();
        for (String s : ret) {
            ApAssociateWords apAssociateWords = new ApAssociateWords();
            apAssociateWords.setAssociateWords(s);
            list.add(apAssociateWords);
        }
        return ResponseResult.okResult(list);
    }

    @Override
    public ResponseResult backfillIndex() {
        try {
            // 索引缺失时自动创建，title 用 ik 分词以支持关键词检索
            boolean exists = elasticsearchClient.indices().exists(e -> e.index(ESIndexConstants.ARTICLE_INDEX)).value();
            if (!exists) {
                elasticsearchClient.indices().create(c -> c
                        .index(ESIndexConstants.ARTICLE_INDEX)
                        .mappings(m -> m.properties("title", p -> p.text(t -> t.analyzer("ik_max_word")))));
            }
            long lastId = 0L;
            int total = 0;
            while (true) {
                List<ApArticle> batch = apArticleMapper.selectBackfillBatch(lastId, 500);
                if (batch == null || batch.isEmpty()) {
                    break;
                }
                BulkRequest.Builder br = new BulkRequest.Builder();
                for (ApArticle article : batch) {
                    br.operations(op -> op.index(idx -> idx
                            .index(ESIndexConstants.ARTICLE_INDEX)
                            .id(String.valueOf(article.getId()))
                            .document(article)));
                    lastId = article.getId();
                }
                elasticsearchClient.bulk(br.build());
                total += batch.size();
            }
            return ResponseResult.okResult(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
    }
}
