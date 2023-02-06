package com.guo.tienchin.framework.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guo.tienchin.common.core.domain.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.guo.tienchin.common.utils.SecurityUtils;
import com.guo.tienchin.common.utils.StringUtils;
import com.guo.tienchin.framework.web.service.TokenService;

/**
 * token过滤器 验证token有效性
 *
 * @author tienchin
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // 去 Redis 中查询用户信息并存入 SecurityContextHolder 中
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
            // 每次请求来的时候，假如它携带的token剩余时间小于20min则会刷新持续时间
            tokenService.verifyToken(loginUser);
            // 拿到用户的token
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            // 根据token设置用户信息
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 将用户信息的token存入上下文，每次请求处理完成之后会自动删除（请求还得经过一系列filter和controller等）
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
