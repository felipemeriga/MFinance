package com.meriga.mfinance.security;

import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 *
 * Value object holding the principal, the JWT clailmset and the granted authorities.
 * This is the authentication object that will be made available in the security context.
 *
 */
public class JwtAuthentication extends AbstractAuthenticationToken {

    private final Object principal;
    private JWTClaimsSet jwtClaimsSet;

    public JwtAuthentication(Object principal, JWTClaimsSet jwtClaimsSet, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.jwtClaimsSet = jwtClaimsSet;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public JWTClaimsSet getJwtClaimsSet() {
        return jwtClaimsSet;
    }
}
