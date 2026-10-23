package com.xftt.article.service.impl;

import com.xftt.article.service.AppArticleInfoService;
import com.xftt.model.article.dtos.ArticleInfoDto;
import com.xftt.model.article.pojos.ApArticle;
import com.xftt.model.article.pojos.ApArticleConfig;
import com.xftt.model.article.pojos.ApArticleContent;
import com.xftt.model.article.pojos.ApAuthor;
import com.xftt.model.article.pojos.ApCollection;
import com.xftt.model.behavior.pojos.ApBehaviorEntry;
import com.xftt.model.behavior.pojos.ApLikesBehavior;
import com.xftt.model.behavior.pojos.ApUnlikesBehavior;
import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.common.enums.AppHttpCodeEnum;
import com.xftt.model.mappers.app.*;
import com.xftt.model.user.pojos.ApUser;
import com.xftt.model.user.pojos.ApUserFollow;
import com.xftt.utils.common.BurstUtils;
import com.xftt.utils.common.ZipUtils;
import com.xftt.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@SuppressWarnings("all")
public class AppArticleInfoServiceImpl implements AppArticleInfoService {


    @Autowired
    private ApArticleConfigMapper apArticleConfigMapper;

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Autowired
    private ApArticleMapper apArticleMapper;

    @Override
    public ResponseResult getArticleInfo(Integer articleId) {

        if(articleId ==null || articleId < 1){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        Map<String,Object> data = new HashMap<>();

        //根据文章id查询config的信息
        ApArticleConfig apArticleConfig = apArticleConfigMapper.selectByArticleId(articleId);
        //判断当前文章是否删除
        if(apArticleConfig==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }else if(!apArticleConfig.getIsDelete()){
            ApArticleContent apArticleContent = apArticleContentMapper.selectByArticleId(articleId);
            String content = ZipUtils.gunzip(apArticleContent.getContent());
            apArticleContent.setContent(content);
            data.put("content",apArticleContent);
        }
        //聚合 ap_article 基础信息（标题/作者/阅读数等），供前端详情页回显
        ApArticle apArticle = apArticleMapper.selectById(articleId.longValue());
        if(apArticle != null){
            data.put("article",apArticle);
        }
        data.put("config",apArticleConfig);

        return ResponseResult.okResult(data);
    }

    @Override
    public ResponseResult getArticleDetail(Integer articleId) {
        if(articleId == null || articleId < 1){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        ApArticleConfig apArticleConfig = apArticleConfigMapper.selectByArticleId(articleId);
        if(apArticleConfig == null || apArticleConfig.getIsDelete()){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        ApArticle apArticle = apArticleMapper.selectById(articleId.longValue());
        if(apArticle == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        Map<String,Object> data = new HashMap<>();
        data.put("id",apArticle.getId());
        data.put("title",apArticle.getTitle());
        data.put("authorId",apArticle.getAuthorId());
        data.put("authorName",apArticle.getAuthorName());
        data.put("channelId",apArticle.getChannelId());
        data.put("channelName",apArticle.getChannelName());
        data.put("images",apArticle.getImages());
        data.put("labels",apArticle.getLabels());
        data.put("likes",apArticle.getLikes());
        data.put("collection",apArticle.getCollection());
        data.put("comment",apArticle.getComment());
        data.put("views",apArticle.getViews());
        data.put("publishTime",apArticle.getPublishTime());
        data.put("createdTime",apArticle.getCreatedTime());
        data.put("isComment",apArticleConfig.getIsComment());
        data.put("isForward",apArticleConfig.getIsForward());
        data.put("isDown",apArticleConfig.getIsDown());
        ApArticleContent apArticleContent = apArticleContentMapper.selectByArticleId(articleId);
        data.put("content",apArticleContent != null ? ZipUtils.gunzip(apArticleContent.getContent()) : "");

        return ResponseResult.okResult(data);
    }

    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;

    @Autowired
    private ApCollectionMapper apCollectionMapper;

    @Autowired
    private ApLikesBehaviorMapper apLikesBehaviorMapper;

    @Autowired
    private ApUnlikesBehaviorMapper apUnlikesBehaviorMapper;

    @Autowired
    private ApAuthorMapper apAuthorMapper;

    @Autowired
    private ApUserFollowMapper apUserFollowMapper;

    @Override
    public ResponseResult loadArticleBehavior(ArticleInfoDto dto) {
        ApUser user = AppThreadLocalUtils.getUser();
        //用户与设备不能同时为空
        if(user==null && dto.getEquipmentId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        //1，通过equipmentId或用户id查看行为实体  --->entryId
        Long userId = null;
        if(user!=null){
            userId = user.getId();
        }
        ApBehaviorEntry apBehaviorEntry = apBehaviorEntryMapper.selectByUserIdOrEquipemntId(userId, dto.getEquipmentId());
        //无行为实体（未登录且无设备记录）时直接返回全 false，避免空指针
        if(apBehaviorEntry == null){
            Map<String,Object> empty = new HashMap<>();
            empty.put("isfollow",false);
            empty.put("islike",false);
            empty.put("isunlike",false);
            empty.put("iscollection",false);
            return ResponseResult.okResult(empty);
        }

        boolean isUnlike = false,isLike = false,isCollection = false,isFollow=false;
        String burst = BurstUtils.groudOne(apBehaviorEntry.getId());

        //2，通过entryId和articleId去查看收藏表，看是否有数据，有数据就说明已经收藏，否则就是没有收藏
        ApCollection apCollection = apCollectionMapper.selectForEntryId(burst, apBehaviorEntry.getId(), dto.getArticleId(), ApCollection.Type.ARTICLE.getCode());
        if(apCollection!=null){
            isCollection=true;
        }
        // 3，通过entryId和articleId去查看点赞表，看是否有数据，有数据就说明已经点赞，否则就是没有点赞
        ApLikesBehavior apLikesBehavior = apLikesBehaviorMapper.selectLastLike(burst, apBehaviorEntry.getId(), dto.getArticleId(), ApCollection.Type.ARTICLE.getCode());
        if(apLikesBehavior!=null && apLikesBehavior.getOperation() == ApLikesBehavior.Operation.LIKE.getCode()){
            isLike = true;
        }
        //4，通过entryId和articleId去查看不喜欢表，看是否有数据，有数据就说明不喜欢，否则就是喜欢
        ApUnlikesBehavior apUnlikesBehavior = apUnlikesBehaviorMapper.selectLastUnLike(apBehaviorEntry.getId(), dto.getArticleId());
        if(apUnlikesBehavior!=null && apUnlikesBehavior.getType()==ApUnlikesBehavior.Type.UNLIKE.getCode()){
            isUnlike = true;
        }

        //5，通过authorId去app的id  userId(app账号信息id)
        ApAuthor apAuthor = apAuthorMapper.selectById(dto.getAuthorId());
        //查看关注表，根据当前登录人的userId与作者的app账号id去查询，如果有数据，就说明已经关注，没有则说明没有关注
        if(user!=null && apAuthor!=null && apAuthor.getUserId()!=null){
            ApUserFollow apUserFollow = apUserFollowMapper.selectByFollowId(BurstUtils.groudOne(user.getId()), user.getId(), apAuthor.getUserId().intValue());
            if(apUserFollow!=null){
                isFollow=true;
            }
        }
        Map<String,Object> data = new HashMap<>();
        data.put("isfollow",isFollow);
        data.put("islike",isLike);
        data.put("isunlike",isUnlike);
        data.put("iscollection",isCollection);

        return ResponseResult.okResult(data);
    }
}
