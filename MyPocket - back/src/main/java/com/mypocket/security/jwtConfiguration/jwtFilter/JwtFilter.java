package com.mypocket.security.jwtConfiguration.jwtFilter;

import com.mypocket.security.jwtConfiguration.jwtProvider.JwtProvider;
import com.mypocket.security.jwtConfiguration.jwtProvider.JwtProviderImp;
import com.mypocket.security.jwtConfiguration.jwtUtilities.JwtProperties;
import com.mypocket.security.userConfiguration.PrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class JwtFilter extends OncePerRequestFilter {

    private JwtProvider tokenProvider;
    private PrincipalDetailsService principalDetailsService;

//    public JwtFilter() {
//    }

//    @Autowired
    public JwtFilter(JwtProvider tokenProvider, PrincipalDetailsService principalDetailsService) {
        this.tokenProvider = tokenProvider;
        this.principalDetailsService = principalDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request
            , HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(JwtProperties.AUTHORIZATION_HEADER);

        if (token != null && tokenProvider.validateToken(token)){

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                principalDetailsService.loadUserByUsername(tokenProvider.getUsername(token.substring(7)))
                ,null
                , tokenProvider.getRoles(token.substring(7))
            );

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }
}
