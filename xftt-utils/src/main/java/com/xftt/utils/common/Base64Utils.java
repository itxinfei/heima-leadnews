package com.xftt.utils.common;

import java.util.Base64;

/**
 * 心飞头条 - Base64 编解码工具（JDK 17+ 适配：sun.misc.BASE64Encoder/Decoder 在 JDK 9+ 已移除，改用 java.util.Base64）
 */
public class Base64Utils {

    /**
     * 解码
     * @param base64 Base64 字符串
     * @return 原始字节数组
     */
    public static byte[] decode(String base64) {
        if (base64 == null) {
            return null;
        }
        try {
            return Base64.getDecoder().decode(base64);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 编码
     * @param data 原始字节数组
     * @return Base64 字符串
     */
    public static String encode(byte[] data) {
        if (data == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(data);
    }
}
