package com.xftt.model.mess.admin;


import com.xftt.model.article.pojos.ApArticleConfig;
import com.xftt.model.article.pojos.ApArticleContent;
import com.xftt.model.article.pojos.ApAuthor;
import lombok.Data;

@Data
public class AutoReviewClNewsSuccess {
    private ApArticleConfig apArticleConfig;
    private ApArticleContent apArticleContent;
    private ApAuthor apAuthor;

}
