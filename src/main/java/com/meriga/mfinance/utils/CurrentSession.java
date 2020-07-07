package com.meriga.mfinance.utils;

import com.meriga.mfinance.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class CurrentSession {


    public Authentication getCurrentSession() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
    }

    public User getCurrentUserEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = new User();
        user.setId(authentication.getCredentials().toString());
        return user;
    }

}
