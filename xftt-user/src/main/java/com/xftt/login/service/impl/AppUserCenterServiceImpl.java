package com.xftt.login.service.impl;

import com.xftt.common.zookeeper.sequence.Sequences;
import com.xftt.login.service.AppUserCenterService;
import com.xftt.model.article.pojos.ApArticle;
import com.xftt.model.article.pojos.ApCollection;
import com.xftt.model.common.dtos.PageResponseResult;
import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.common.enums.AppHttpCodeEnum;
import com.xftt.model.mappers.app.ApArticleMapper;
import com.xftt.model.mappers.app.ApCollectionMapper;
import com.xftt.model.mappers.app.ApUserMapper;
import com.xftt.model.mappers.app.ApUserFanMapper;
import com.xftt.model.mappers.app.ApUserFollowMapper;
import com.xftt.model.user.dtos.CollectionDto;
import com.xftt.model.user.dtos.ProfileDto;
import com.xftt.model.user.pojos.ApUser;
import com.xftt.model.user.pojos.ApUserFan;
import com.xftt.model.user.pojos.ApUserFollow;
import com.xftt.utils.common.BurstUtils;
import com.xftt.utils.common.IdsUtils;
import com.xftt.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private Sequences sequences;

    @Autowired
    private ApUserMapper apUserMapper;

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
            //文章 ID 按 IdEncrypt 契约输出，供前端跳转详情/上报行为
            try {
                item.put("articleId", IdsUtils.encryptNumber(row.getEntryId().longValue()));
            } catch (Exception e) {
                item.put("articleId", row.getEntryId());
            }
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

    @Override
    public ResponseResult collection(CollectionDto dto) {
        ApUser user = requireLogin();
        if (user == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        if (dto.getArticleId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE, "articleId不能为空");
        }
        Short operation = dto.getOperation();
        if (operation == null || operation < 0 || operation > 1) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "无效的operation参数");
        }
        ApArticle article = apArticleMapper.selectById(dto.getArticleId().longValue());
        if (article == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "文章不存在");
        }
        String burst = BurstUtils.groudOne(user.getId());
        Integer objectId = user.getId().intValue();
        if (operation == 0) {
            //收藏：已收藏则直接返回，避免重复插入
            ApCollection exist = apCollectionMapper.selectForEntryId(burst, objectId, dto.getArticleId(), ApCollection.Type.ARTICLE.getCode());
            if (exist != null) {
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST, "已收藏");
            }
            ApCollection entity = new ApCollection();
            entity.setId(sequences.sequenceApCollection());
            entity.setBehaviorEntryId(objectId);
            entity.setEntryId(dto.getArticleId());
            entity.setType(ApCollection.Type.ARTICLE.getCode());
            entity.setCollectionTime(new Date());
            entity.setPublishedTime(article.getPublishTime());
            entity.setBurst(BurstUtils.encrypt(entity.getId(), user.getId()));
            return ResponseResult.okResult(apCollectionMapper.insert(entity));
        }
        //取消收藏
        int rows = apCollectionMapper.deleteForEntryId(burst, objectId, dto.getArticleId(), ApCollection.Type.ARTICLE.getCode());
        return rows > 0
                ? ResponseResult.okResult(rows)
                : ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "未收藏");
    }

    @Override
    public ResponseResult userCenter() {
        ApUser user = requireLogin();
        if (user == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        String burst = BurstUtils.groudOne(user.getId());
        Map<String, Object> data = new HashMap<>();
        data.put("follows", apUserFollowMapper.countByUserId(burst, user.getId()));
        data.put("fans", apUserFanMapper.countFansByUserId(burst, user.getId().intValue()));
        data.put("collections", apCollectionMapper.countCollectionsByEntry(burst, user.getId().intValue(), ApCollection.Type.ARTICLE.getCode()));
        // APP-F12：当前用户资料（头像/昵称/认证状态），供“我的”页头部与资料编辑回显
        Map<String, Object> profile = new HashMap<>();
        profile.put("id", IdsUtils.encryptNumber(user.getId()));
        profile.put("name", user.getName());
        profile.put("image", user.getImage());
        profile.put("phone", user.getPhone());
        profile.put("sex", user.getSex());
        profile.put("isCertification", user.getIsCertification());
        profile.put("isIdentityAuthentication", user.getIsIdentityAuthentication());
        data.put("user", profile);
        return ResponseResult.okResult(data);
    }

    @Override
    public ResponseResult updateProfile(ProfileDto dto) {
        ApUser user = requireLogin();
        if (user == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        ApUser update = new ApUser();
        update.setId(user.getId().intValue());
        update.setName(dto.getName());
        update.setImage(dto.getImage());
        update.setSex(dto.getSex());
        int rows = apUserMapper.updateProfile(update);
        return rows > 0
                ? ResponseResult.okResult(null)
                : ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR, "资料更新失败");
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
