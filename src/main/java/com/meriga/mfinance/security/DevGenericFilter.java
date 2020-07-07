package com.meriga.mfinance.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DevGenericFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = null;

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
            = new UsernamePasswordAuthenticationToken("dev", "");

        usernamePasswordAuthenticationToken.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        // Handler for CORS filter, for Development Phase, remove this to prod environment
        HttpServletResponse resp = (HttpServletResponse) response;

        filterChain.doFilter(request,response);
    }
}
