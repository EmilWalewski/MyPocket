package com.mypocket.security.jwtConfiguration.jwtFilter;

<<<<<<< HEAD
import com.mypocket.security.jwtConfiguration.jwtProvider.JwtProvider;
=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
import com.mypocket.security.jwtConfiguration.jwtProvider.JwtProviderImp;
import com.mypocket.security.jwtConfiguration.jwtUtilities.JwtProperties;
import com.mypocket.security.userConfiguration.PrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
<<<<<<< HEAD
import org.springframework.stereotype.Service;
=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
<<<<<<< HEAD
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
=======

public class JwtFilter extends OncePerRequestFilter {

    private JwtProviderImp tokenProvider;
    private PrincipalDetailsService principalDetailsService;

    public JwtFilter() {
    }

    @Autowired
    public JwtFilter(JwtProviderImp tokenProvider, PrincipalDetailsService principalDetailsService) {
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
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
<<<<<<< HEAD
                principalDetailsService.loadUserByUsername(tokenProvider.getUsername(token.substring(7)))
                ,null
                , tokenProvider.getRoles(token.substring(7))
=======
                principalDetailsService.loadUserByUsername(tokenProvider.getUsername(token))
                ,null
                , tokenProvider.getRoles(token)
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
            );

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }
}
