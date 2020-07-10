package com.meriga.mfinance.utils;

import com.meriga.mfinance.domain.User;
import com.meriga.mfinance.repository.UserRepository;
import com.meriga.mfinance.security.JwtAuthentication;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class CurrentSession {

    @Autowired
    UserRepository userRepository;

    public Authentication getCurrentSession() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getCurrentUser() {
        Object test =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((org.springframework.security.core.userdetails.User) test).getUsername();
    }

    public User getCurrentUserEntity() {
        Object test =  SecurityContextHolder.getContext().getAuthentication().getCredentials();
        JWTClaimsSet jwtClaimsSet = (JWTClaimsSet)((JwtAuthentication)SecurityContextHolder.getContext().getAuthentication()).getJwtClaimsSet();
        User user = new User();
        String userId = jwtClaimsSet.getClaims().get("cognito:username").toString();
        Optional<User> optionalUser = userRepository.findById(userId);

        user.setId(userId);
        user.setEmail(jwtClaimsSet.getClaims().get("email").toString());
        user.setName(jwtClaimsSet.getClaims().get("name").toString());

        if(!optionalUser.isPresent()) {
            userRepository.save(user);
        }
        return user;
    }

}
