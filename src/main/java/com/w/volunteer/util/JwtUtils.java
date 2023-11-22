package com.w.volunteer.util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtils {
    private static final String SECRET_KEY = "caricature"; // 替换为你的密钥

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 设置有效期为1小时，你可以根据需要进行调整
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 使用HS256算法和你的密钥进行签名
                .compact();
    }

    public static String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token) // 解析JWT并获取 claims（包含用户名）
                .getBody()
                .getSubject(); // 获取用户名
    }
}
