package com.xftt.article.controller.v1;

import com.xftt.model.article.pojos.ApComment;
import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.mappers.app.ApCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * APP 端文章评论接口
 */
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @Autowired
    private ApCommentMapper apCommentMapper;

    @GetMapping("/list")
    public ResponseResult list(@RequestParam("articleId") Integer articleId) {
        List<ApComment> comments = apCommentMapper.selectByEntryId(articleId);
        return ResponseResult.okResult(comments);
    }

    @PostMapping("/create")
    public ResponseResult create(@RequestBody ApComment comment) {
        if (comment.getEntryId() == null || comment.getContent() == null || comment.getContent().isEmpty()) {
            return ResponseResult.errorResult(500, "评论内容不能为空");
        }
        apCommentMapper.insert(comment);
        return ResponseResult.okResult(null);
    }

    @PostMapping("/like")
    public ResponseResult like(@RequestBody ApComment comment) {
        if (comment.getId() == null) {
            return ResponseResult.errorResult(500, "评论ID不能为空");
        }
        apCommentMapper.addLike(comment.getId());
        return ResponseResult.okResult(null);
    }
}
