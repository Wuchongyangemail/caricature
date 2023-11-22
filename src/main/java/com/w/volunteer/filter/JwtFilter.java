package com.w.volunteer.filter;

import cn.hutool.core.collection.CollectionUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class JwtFilter implements Filter {

    private static Set<String> notFilterRouter = new HashSet<>(Arrays.asList(new String[]{"/controller/all_area", "/controller/login", "/controller/register"}));

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("Authorization");// 从请求头中获取JWT，你也可以从其他位置获取，例如请求参数或Cookie中获取
        String requestURI = request.getRequestURI();
        if (shouldIntercept(requestURI, request)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        Optional<Jws<Claims>> claims = Optional.ofNullable(Jwts.parser().setSigningKey("caricature").parseClaimsJws(token)); // 使用你的密钥进行解析和验证签名，这里使用硬编码的密钥，你也可以将其存储在配置文件中或使用更安全的方式存储密钥
        if (claims != null && claims.isPresent()) { // 如果解析成功并存在有效的JWT，则继续处理请求，否则返回未授权的错误响应或重定向到登录页面等处理方式，具体取决于你的业务需求和设计逻辑。
            String username = claims.get().getBody().getSubject(); // 获取JWT中的用户名
            if (username != null) { // 如果存在用户名，则继续处理请求，否则返回错误响应或重定向到登录页面等处理方式
                request.setAttribute("username", username); // 将用户名添加到请求属性中，以便在后续处理中使用
                filterChain.doFilter(servletRequest, servletResponse); // 继续处理请求
            } else {
                // 处理未授权的情况，例如返回错误响应或重定向到登录页面等处理方式
            }
        } else {
            // 处理JWT无效的情况，例如返回错误响应或重定向到登录页面等处理方式
        }
    }

    private boolean shouldIntercept(String requestURI, HttpServletRequest request) {
        // 根据您的需求判断是否需要拦截该请求URI
        // 返回true表示需要拦截，返回false表示不需要拦截
        // 这里只是一个示例，拦截所有的GET请求：
        return notFilterRouter.contains(requestURI);
    }
}