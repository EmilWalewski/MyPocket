package com.mypocket.security.jwtConfiguration.jwtProvider;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.UUID;

public interface JwtProvider {

    String generateToken(Authentication authentication);

    String getUsername(String token);

    Collection<? extends SimpleGrantedAuthority> getRoles(String token);

    boolean validateToken(String token);

    Authentication getAuthenticationByRefreshToken(UUID refreshToken);
}
