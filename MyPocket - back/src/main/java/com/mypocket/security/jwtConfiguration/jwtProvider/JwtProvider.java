package com.mypocket.security.jwtConfiguration.jwtProvider;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.UUID;
=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59

public interface JwtProvider {

    String generateToken(Authentication authentication);

    String getUsername(String token);

    Collection<? extends SimpleGrantedAuthority> getRoles(String token);

    boolean validateToken(String token);
<<<<<<< HEAD
<<<<<<< HEAD

    Authentication getAuthenticationByRefreshToken(UUID refreshToken);
=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
}
