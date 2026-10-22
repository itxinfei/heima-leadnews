package com.xftt.common.kafka.messages;

import com.xftt.common.kafka.KafkaMessage;
import com.xftt.model.mess.admin.ArticleAuditSuccess;

/**
 * 审核成功发送消息
 */
public class ArticleAuditSuccessMessage extends KafkaMessage<ArticleAuditSuccess> {

    public ArticleAuditSuccessMessage() {
    }

    public ArticleAuditSuccessMessage(ArticleAuditSuccess data) {
        super(data);
    }

    @Override
    public String getType() {
        return "admin_audit_success";
    }

}