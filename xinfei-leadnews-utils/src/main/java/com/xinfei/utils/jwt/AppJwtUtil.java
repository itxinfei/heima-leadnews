package com.xinfei.utils.jwt;

import com.xinfei.model.admin.pojos.AdUser;
import com.xinfei.model.user.pojos.ApUser;
import com.xinfei.model.media.pojos.WmUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * 心飞头条 - JWT 工具类
 * 基于 jjwt 0.12.x 新 API（替代 0.9.x 旧 API）
 */
public class AppJwtUtil {

    // TOKEN 的有效期一天（S）
    private static final int TOKEN_TIME_OUT = 3_600;
    // 加密 KEY（Base64 编码后的 AES key）
    private static final String TOKEN_ENCRY_KEY = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY";
    // 最小刷新间隔(S)
    private static final int REFRESH_TIME = 300;

    /**
     * 获取 token（客户端用户）
     */
    public static String getToken(ApUser user) {
        return getToken(user.getId());
    }

    /**
     * 获取 token（管理端）
     */
    public static String getToken(AdUser user) {
        return getToken(Long.valueOf(user.getId()));
    }

    /**
     * 获取 token（自媒体用户）
     */
    public static String getToken(WmUser user) {
        return getToken(user.getId());
    }

    /**
     * 生成 token（核心逻辑）
     * @param id 用户 ID
     */
    private static String getToken(Long id) {
        Map<String, Object> claimMaps = new HashMap<>();
        claimMaps.put("id", id);
        long currentTime = System.currentTimeMillis();
        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .issuedAt(new Date(currentTime))      // 签发时间
                .subject("system")                    // 说明
                .issuer("xinfei")                     // 签发者
                .audience().add("app").and()          // 接收用户
                .compressWith(CompressionCodecs.GZIP) // 数据压缩
                .signWith(generalKey(), Jwts.SIG.HS512) // 签名
                .expiration(new Date(currentTime + TOKEN_TIME_OUT * 1000L)) // 过期时间
                .claims(claimMaps)                    // 自定义 claims
                .compact();
    }

    /**
     * 解析 token 中的 claims
     */
    private static Jws<Claims> getJws(String token) {
        return Jwts.parser()
                .verifyWith(generalKey())
                .build()
                .parseSignedClaims(token);
    }

    /**
     * 获取 payload body 信息
     */
    public static Claims getClaimsBody(String token) {
        try {
            return getJws(token).getPayload();
        } catch (ExpiredJwtException e) {
            return null;
        }
    }

    /**
     * 获取 header body 信息
     */
    public static JwsHeader getHeaderBody(String token) {
        return getJws(token).getHeader();
    }

    /**
     * 是否过期
     * @return -1：有效且无需刷新，0：有效但需刷新，1：过期，2：异常
     */
    public static int verifyToken(Claims claims) {
        if (claims == null) {
            return 1;
        }
        try {
            if (claims.getExpiration().before(new Date())) {
                return 1;
            }
            if ((claims.getExpiration().getTime() - System.currentTimeMillis()) > REFRESH_TIME * 1000L) {
                return -1;
            } else {
                return 0;
            }
        } catch (ExpiredJwtException ex) {
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    /**
     * 由字符串生成加密 key
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Decoders.BASE64.decode(TOKEN_ENCRY_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    public static void main(String[] args) {
        ApUser user = new ApUser();
        user.setId(1L);
        System.out.println(AppJwtUtil.getToken(user));
    }
}
