package com.xftt.login.service.impl;

import com.xftt.login.service.AppUserCenterService;
import com.xftt.model.article.pojos.ApArticle;
import com.xftt.model.article.pojos.ApCollection;
import com.xftt.model.common.dtos.PageResponseResult;
import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.common.enums.AppHttpCodeEnum;
import com.xftt.model.mappers.app.ApArticleMapper;
import com.xftt.model.mappers.app.ApCollectionMapper;
import com.xftt.model.mappers.app.ApUserFanMapper;
import com.xftt.model.mappers.app.ApUserFollowMapper;
import com.xftt.model.user.pojos.ApUser;
import com.xftt.model.user.pojos.ApUserFan;
import com.xftt.model.user.pojos.ApUserFollow;
import com.xftt.utils.common.BurstUtils;
import com.xftt.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户中心（BE-F03）：关注/粉丝/收藏分页，数据按当前登录用户分片路由
 */
@Service
@SuppressWarnings("all")
public class AppUserCenterServiceImpl implements AppUserCenterService {

    @Autowired
    private ApUserFollowMapper apUserFollowMapper;

    @Autowired
    private ApUserFanMapper apUserFanMapper;

    @Autowired
    private ApCollectionMapper apCollectionMapper;

    @Autowired
    private ApArticleMapper apArticleMapper;

    @Override
    public ResponseResult follows(Integer page, Integer size) {
        ApUser user = requireLogin();
        if (user == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        int[] ps = normalize(page, size);
        String burst = BurstUtils.groudOne(user.getId());
        List<ApUserFollow> rows = apUserFollowMapper.selectFollowsByUserId(burst, user.getId(), (ps[0] - 1) * ps[1], ps[1]);
        int total = apUserFollowMapper.countByUserId(burst, user.getId());
        PageResponseResult result = new PageResponseResult(ps[0], ps[1], total);
        result.setData(rows);
        return result;
    }

    @Override
    public ResponseResult fans(Integer page, Integer size) {
        ApUser user = requireLogin();
        if (user == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        int[] ps = normalize(page, size);
        String burst = BurstUtils.groudOne(user.getId());
        List<ApUserFan> rows = apUserFanMapper.selectFansByUserId(burst, user.getId().intValue(), (ps[0] - 1) * ps[1], ps[1]);
        int total = apUserFanMapper.countFansByUserId(burst, user.getId().intValue());
        PageResponseResult result = new PageResponseResult(ps[0], ps[1], total);
        result.setData(rows);
        return result;
    }

    @Override
    public ResponseResult collections(Integer page, Integer size) {
        ApUser user = requireLogin();
        if (user == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        int[] ps = normalize(page, size);
        String burst = BurstUtils.groudOne(user.getId());
        List<ApCollection> rows = apCollectionMapper.selectCollectionsByEntry(burst, user.getId().intValue(),
                ApCollection.Type.ARTICLE.getCode(), (ps[0] - 1) * ps[1], ps[1]);
        int total = apCollectionMapper.countCollectionsByEntry(burst, user.getId().intValue(), ApCollection.Type.ARTICLE.getCode());
        // 补充文章基础信息（标题/封面），供收藏列表展示
        List<Map<String, Object>> data = new ArrayList<>();
        for (ApCollection row : rows) {
            Map<String, Object> item = new HashMap<>();
            item.put("articleId", row.getEntryId());
            item.put("collectionTime", row.getCollectionTime());
            ApArticle article = apArticleMapper.selectById(row.getEntryId().longValue());
            if (article != null) {
                item.put("title", article.getTitle());
                item.put("images", article.getImages());
                item.put("authorName", article.getAuthorName());
            }
            data.add(item);
        }
        PageResponseResult result = new PageResponseResult(ps[0], ps[1], total);
        result.setData(data);
        return result;
    }

    private ApUser requireLogin() {
        return AppThreadLocalUtils.getUser();
    }

    private int[] normalize(Integer page, Integer size) {
        int p = (page == null || page < 1) ? 1 : page;
        int s = (size == null || size < 1 || size > 100) ? 10 : size;
        return new int[]{p, s};
    }
}
