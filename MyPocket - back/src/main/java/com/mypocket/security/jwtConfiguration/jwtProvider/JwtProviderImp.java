package com.mypocket.security.jwtConfiguration.jwtProvider;

//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
import com.mypocket.security.jwtConfiguration.jwtUtilities.JwtProperties;
import com.mypocket.security.userConfiguration.PrincipalDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtProviderImp implements JwtProvider{

    private static Map<UUID, Authentication> refreshTokens = new HashMap<>();

    @Override
    public String generateToken(Authentication authentication){

        Claims claims = new DefaultClaims();
        claims.put("roles", authentication.getAuthorities());

        refreshTokens.put(UUID.randomUUID(), authentication);

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(((PrincipalDetails)authentication.getPrincipal()).getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 100000))
                .signWith(SignatureAlgorithm.HS512, JwtProperties.SIGNATURE.getBytes())
                .compact();
    }

    @Override
    public Authentication getAuthenticationByRefreshToken(UUID refreshToken) {

        if (refreshTokens.containsKey(refreshToken))
        return refreshTokens.get(refreshToken);

        return null;
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

    }

    @Override
    public boolean validateToken(String token){

        if (token.startsWith(JwtProperties.TOKEN_PREFIX)){

            try {

                String t = token.substring(7);
                Jwts
                    .parser()
                    .setSigningKey(JwtProperties.SIGNATURE.getBytes())
                    .parseClaimsJws(t);
                return true;

            }catch (Exception e){
               //logs
            }
        }

        return false;
    }


}
