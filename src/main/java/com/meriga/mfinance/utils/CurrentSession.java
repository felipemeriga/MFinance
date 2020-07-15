package com.meriga.mfinance.utils;

import com.meriga.mfinance.domain.User;
import com.meriga.mfinance.repository.UserRepository;
import com.meriga.mfinance.security.JwtAuthentication;
import com.nimbusds.jwt.JWTClaimsSet;
import io.github.jhipster.config.JHipsterConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class CurrentSession {

    @Autowired
    Environment env;

    @Autowired
    UserRepository userRepository;

    public Authentication getCurrentSession() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getCurrentUser() {
        Object test =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((org.springframework.security.core.userdetails.User) test).getUsername();
    }

    public void persistIfUserDoesntExist(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(!optionalUser.isPresent()) {
            userRepository.save(user);
        }
    }

    public User getCurrentUserEntity() {
        Object authentication =  SecurityContextHolder.getContext().getAuthentication().getCredentials();
        User user = new User();

        if (env.acceptsProfiles(Profiles.of(JHipsterConstants.SPRING_PROFILE_PRODUCTION, JHipsterConstants.SPRING_PROFILE_TEST))) {
            JWTClaimsSet jwtClaimsSet = (JWTClaimsSet)((JwtAuthentication)SecurityContextHolder.getContext().getAuthentication()).getJwtClaimsSet();
            String userId = jwtClaimsSet.getClaims().get("cognito:username").toString();

            user.setId(userId);
            user.setEmail(jwtClaimsSet.getClaims().get("email").toString());

            Object name = jwtClaimsSet.getClaims().get("name");
            if(name == null) {
                user.setName(userId);
            } else {
                user.setName(name.toString());
            }

        } else {
            user.setName(authentication.toString());
            user.setId(authentication.toString());
            user.setName(authentication.toString());
        }

        this.persistIfUserDoesntExist(user);
        return user;
    }

}
