package com.meriga.mfinance.security;

import io.github.jhipster.config.JHipsterConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AwsCognitoJwtAuthenticationFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(AwsCognitoJwtAuthenticationFilter.class);

    private AwsCognitoIdTokenProcessor awsCognitoIdTokenProcessor;

    @Autowired
    Environment env;

    public AwsCognitoJwtAuthenticationFilter(AwsCognitoIdTokenProcessor awsCognitoIdTokenProcessor) {
        this.awsCognitoIdTokenProcessor = awsCognitoIdTokenProcessor;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        Authentication authentication = null;
        if (env.acceptsProfiles(Profiles.of(JHipsterConstants.SPRING_PROFILE_PRODUCTION, JHipsterConstants.SPRING_PROFILE_TEST))) {
            try {
                authentication = awsCognitoIdTokenProcessor.getAuthentication((HttpServletRequest) request);

                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            } catch (Exception e) {
                logger.error("Error occured while processing Cognito ID Token", e);
                SecurityContextHolder.clearContext();
                //return;
                //throw new ServletException("Error occured while processing Cognito ID Token",e);
            }
        } else {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            User tempUser = new User("dev-user",
                "dev",
                true, true, true, true, // logging them in...
                authorities // type is List<GrantedAuthority>
            );
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            authentication = new UsernamePasswordAuthenticationToken(tempUser, "dev", authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Handler for CORS filter, for Development Phase, remove this to prod environment
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");

        filterChain.doFilter(request,response);

    }
}
