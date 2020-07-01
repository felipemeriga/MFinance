package com.meriga.mfinance.security;

import io.github.jhipster.config.JHipsterConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.env.Profiles;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements Ordered {

	private int order = 4;

	@Autowired
    private AwsCognitoJwtAuthenticationFilter awsCognitoJwtAuthenticationFilter;

	@Override
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

        http.headers().cacheControl();
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/health").permitAll()
            .antMatchers("/v2/**").permitAll()
            .antMatchers("/docs/**").permitAll()
            .antMatchers("/api/**").authenticated()
            .antMatchers("/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(awsCognitoJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	}

}
