package com.mypocket.security.jwtConfiguration.jwtProvider;

<<<<<<< HEAD
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
import com.mypocket.security.jwtConfiguration.jwtUtilities.JwtProperties;
import com.mypocket.security.userConfiguration.PrincipalDetails;
import io.jsonwebtoken.*;
=======
import com.mypocket.security.jwtConfiguration.jwtUtilities.JwtProperties;
import com.mypocket.security.userConfiguration.PrincipalDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
<<<<<<< HEAD
import java.util.*;
import java.util.stream.Collectors;
=======
import java.util.Collection;
import java.util.List;
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59

@Service
public class JwtProviderImp implements JwtProvider{

<<<<<<< HEAD
    private static Map<UUID, Authentication> refreshTokens = new HashMap<>();

=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
    @Override
    public String generateToken(Authentication authentication){

        Claims claims = new DefaultClaims();
        claims.put("roles", authentication.getAuthorities());

<<<<<<< HEAD
        refreshTokens.put(UUID.randomUUID(), authentication);

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(((PrincipalDetails)authentication.getPrincipal()).getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 10000))
                .signWith(SignatureAlgorithm.HS512, JwtProperties.SIGNATURE.getBytes())
=======
        return Jwts
                .builder()
                .setSubject(((PrincipalDetails)authentication.getPrincipal()).getUsername())
                .setExpiration(new Timestamp(System.currentTimeMillis() + 10000))
                .signWith(SignatureAlgorithm.HS256, JwtProperties.SIGNATURE.getBytes())
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
                .compact();
    }

    @Override
<<<<<<< HEAD
    public Authentication getAuthenticationByRefreshToken(UUID refreshToken) {

        if (refreshTokens.containsKey(refreshToken))
        return refreshTokens.get(refreshToken);

        return null;
    }

    @Override
    public String getUsername(String token){


=======
    public String getUsername(String token){

>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
        return Jwts.parser()
                .setSigningKey(JwtProperties.SIGNATURE.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
<<<<<<< HEAD
    @SuppressWarnings("unchecked")
    public Collection<? extends SimpleGrantedAuthority> getRoles(String token){


        List<String> authorities = new ArrayList<String>();

        for (Map<String, String> roles : (List<Map<String, String>>) Jwts.parser()
                .setSigningKey(JwtProperties.SIGNATURE.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .get("roles")){
            for ( Map.Entry rolesMapper : roles.entrySet()){
                authorities.add((String)rolesMapper.getValue());
            }
        }

        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
=======
    public Collection<? extends SimpleGrantedAuthority> getRoles(String token){

        return (List<SimpleGrantedAuthority>)Jwts.parser()
                .setSigningKey(JwtProperties.SIGNATURE.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .get("roles");
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59

    }

    @Override
    public boolean validateToken(String token){

<<<<<<< HEAD
        if (token.startsWith(JwtProperties.TOKEN_PREFIX)){

            try {

                String t = token.substring(7);
                Jwts
                    .parser()
                    .setSigningKey(JwtProperties.SIGNATURE.getBytes())
                    .parseClaimsJws(t);
                return true;

            }catch (Exception e){
=======
        if (token.startsWith(JwtProperties.TOKEN_PREFIX) && token != null){

            try {

                Jwts.parser().setSigningKey(JwtProperties.SIGNATURE.getBytes()).parseClaimsJws(token);
                return true;

            }catch (JwtException e){
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
               //logs
            }
        }

        return false;
    }


}
