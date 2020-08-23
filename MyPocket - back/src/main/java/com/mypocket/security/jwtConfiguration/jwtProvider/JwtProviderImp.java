package com.mypocket.security.jwtConfiguration.jwtProvider;

import com.mypocket.security.jwtConfiguration.jwtUtilities.JwtProperties;
import com.mypocket.security.userConfiguration.PrincipalDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Service
public class JwtProviderImp implements JwtProvider{

    @Override
    public String generateToken(Authentication authentication){

        Claims claims = new DefaultClaims();
        claims.put("roles", authentication.getAuthorities());

        return Jwts
                .builder()
                .setSubject(((PrincipalDetails)authentication.getPrincipal()).getUsername())
                .setExpiration(new Timestamp(System.currentTimeMillis() + 10000))
                .signWith(SignatureAlgorithm.HS256, JwtProperties.SIGNATURE.getBytes())
                .compact();
    }

    @Override
    public String getUsername(String token){

        return Jwts.parser()
                .setSigningKey(JwtProperties.SIGNATURE.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public Collection<? extends SimpleGrantedAuthority> getRoles(String token){

        return (List<SimpleGrantedAuthority>)Jwts.parser()
                .setSigningKey(JwtProperties.SIGNATURE.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .get("roles");

    }

    @Override
    public boolean validateToken(String token){

        if (token.startsWith(JwtProperties.TOKEN_PREFIX) && token != null){

            try {

                Jwts.parser().setSigningKey(JwtProperties.SIGNATURE.getBytes()).parseClaimsJws(token);
                return true;

            }catch (JwtException e){
               //logs
            }
        }

        return false;
    }


}
