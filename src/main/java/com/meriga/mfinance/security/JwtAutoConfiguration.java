package com.meriga.mfinance.security;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.ResourceRetriever;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;

import static com.nimbusds.jose.JWSAlgorithm.RS256;

/**
 *
 * Our auto configuration class that exposes
 *
 * - CredentialHolder
 * - JWTProcessor
 * - AuthenticationProvider
 * - JWTAuthenticationFilter
 * - AwsCognitoJtwConfiguration
 *
 */
@Configuration
@ConditionalOnClass({AwsCognitoJwtAuthenticationFilter.class, AwsCognitoIdTokenProcessor.class})
@EnableConfigurationProperties({JwtConfiguration.class})
public class JwtAutoConfiguration {

    @Bean
    @Scope(value="request", proxyMode= ScopedProxyMode.TARGET_CLASS)
    public JwtIdTokenCredentialsHolder awsCognitoCredentialsHolder() {
        return new JwtIdTokenCredentialsHolder();
    }

    @Bean
    public AwsCognitoIdTokenProcessor awsCognitoIdTokenProcessor() { return new AwsCognitoIdTokenProcessor(); }

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider() { return new JwtAuthenticationProvider(); }


    @Bean
    public AwsCognitoJwtAuthenticationFilter awsCognitoJwtAuthenticationFilter() {
        return new AwsCognitoJwtAuthenticationFilter(awsCognitoIdTokenProcessor());
    }

    @Autowired
    private JwtConfiguration jwtConfiguration;

    @Bean
    public ConfigurableJWTProcessor configurableJWTProcessor() throws MalformedURLException {
        ResourceRetriever resourceRetriever = new DefaultResourceRetriever(jwtConfiguration.getConnectionTimeout(), jwtConfiguration.getReadTimeout());
        URL jwkSetURL = new URL(jwtConfiguration.getJwkUrl());
        JWKSource keySource = new RemoteJWKSet(jwkSetURL, resourceRetriever);
        ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor();
        JWSKeySelector keySelector = new JWSVerificationKeySelector(RS256, keySource);
        jwtProcessor.setJWSKeySelector(keySelector);
        return jwtProcessor;
    }

}
