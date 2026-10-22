package com.xftt.crawler.test;

import com.xftt.common.kafka.KafkaSender;
import com.xftt.common.kafka.messages.SubmitArticleAuthMessage;
import com.xftt.model.mess.admin.SubmitArticleAuto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JobTest {

    @Autowired
    KafkaSender kafkaSender;

    @Test
    public void test() {
        SubmitArticleAuto submitArticleAuto = new SubmitArticleAuto();
        submitArticleAuto.setArticleId(35182);
        submitArticleAuto.setType(SubmitArticleAuto.ArticleType.CRAWLER);
        SubmitArticleAuthMessage authMessage = new SubmitArticleAuthMessage();
        authMessage.setData(submitArticleAuto);
        kafkaSender.sendSubmitArticleAuthMessage(authMessage);
    }
}
