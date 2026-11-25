package com.xftt.model.user.dtos;

import lombok.Data;

/**
 * 个人资料编辑入参（APP-F12）
 * 仅允许修改昵称、头像、性别，其余字段由服务端忽略。
 */
@Data
public class ProfileDto {

    /** 昵称 */
    private String name;

    /** 头像 URL */
    private String image;

    /** 性别：true 男 / false 女 */
    private Boolean sex;
}
