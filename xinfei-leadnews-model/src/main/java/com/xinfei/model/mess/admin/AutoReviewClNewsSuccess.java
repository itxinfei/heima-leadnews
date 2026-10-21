package com.xinfei.model.mess.admin;


import com.xinfei.model.article.pojos.ApArticleConfig;
import com.xinfei.model.article.pojos.ApArticleContent;
import com.xinfei.model.article.pojos.ApAuthor;
import lombok.Data;

@Data
public class AutoReviewClNewsSuccess {
    private ApArticleConfig apArticleConfig;
    private ApArticleContent apArticleContent;
    private ApAuthor apAuthor;

}
