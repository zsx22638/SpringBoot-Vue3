package com.zsx.interceptor;

import com.zsx.pojo.Result;
import com.zsx.utils.JwtUtil;
import com.zsx.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class TokenInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取 token
        String token = request.getHeader("Authorization");

        // 如果 token 为空，拒绝请求
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 未授权
            response.getWriter().write("Token为空！");
            return false; // 中断请求
        }

        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            if (claims == null || !claims.containsKey("id")) {
               response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 未授权
                return false;
            }
            // 将用户信息存储到 ThreadLocal 中
            ThreadLocalUtil.set(claims);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token 验证失效！");
            return false;
        }

        // Token 验证通过，继续处理请求
        return true;
    }


}
