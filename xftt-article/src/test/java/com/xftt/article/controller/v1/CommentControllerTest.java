package com.xftt.article.controller.v1;

import com.xftt.common.kafka.KafkaSender;
import com.xftt.common.kafka.messages.UpdateArticleMessage;
import com.xftt.model.article.pojos.ApComment;
import com.xftt.model.mappers.app.ApCommentMapper;
import com.xftt.model.mess.app.UpdateArticle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * CommentController 评论热度上报单元测试。
 */
@ExtendWith(MockitoExtension.class)
class CommentControllerTest {

    @Mock private ApCommentMapper apCommentMapper;
    @Mock private KafkaSender kafkaSender;
    @InjectMocks private CommentController controller;

    @Test
    void create_valid_sendsCommentMessage() {
        ApComment c = new ApComment();
        c.setEntryId(100);
        c.setContent("好文章");
        controller.create(c);
        ArgumentCaptor<UpdateArticleMessage> captor = ArgumentCaptor.forClass(UpdateArticleMessage.class);
        verify(kafkaSender).sendArticleUpdateBus(captor.capture());
        UpdateArticle data = captor.getValue().getData();
        assertEquals(UpdateArticle.UpdateArticleType.COMMENT, data.getType());
        assertEquals(100, data.getArticleId());
        assertEquals(1, data.getAdd());
    }

    @Test
    void create_emptyContent_noSend() {
        ApComment c = new ApComment();
        c.setEntryId(100);
        c.setContent("");
        controller.create(c);
        verify(apCommentMapper, never()).insert(any());
        verifyNoInteractions(kafkaSender);
    }
}
