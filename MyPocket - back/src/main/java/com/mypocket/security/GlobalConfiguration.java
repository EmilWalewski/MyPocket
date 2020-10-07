package com.mypocket.security;


import com.mypocket.security.jwtConfiguration.jwtFilter.JwtFilter;
import com.mypocket.security.jwtConfiguration.jwtProvider.JwtProvider;
import com.mypocket.security.userConfiguration.PrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class GlobalConfiguration extends WebSecurityConfigurerAdapter {

    /*
    *
    *
    *                   nested mysqlEntityManager - solution: create new repo? , extends new class? - try it
    *
    *
    * */

    private PrincipalDetailsService principalDetailsService;
    private JwtProvider tokenProvider;



    @Autowired
    public GlobalConfiguration(PrincipalDetailsService principalDetailsService, JwtProvider tokenProvider) {
        this.principalDetailsService = principalDetailsService;
        this.tokenProvider = tokenProvider;
    }


    @Bean
    public JwtFilter jwtFilter(){
        return new JwtFilter(tokenProvider, principalDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //cors policy
        http.cors().configurationSource(corsConfigurationSource());

        //csrf settings
        http.csrf().disable();

        //session policy
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //jwt filter
        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        //login / logout options
        http
            .formLogin()
            .permitAll()
            .and()
            .logout()
            .clearAuthentication(true)
            .permitAll();

        http
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/authenticate/**")
            .permitAll();

        http
            .authorizeRequests()
            .antMatchers("/receipt/**")
            .permitAll();

        //requests management
        http
            .authorizeRequests()
            .anyRequest()
            .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public DaoAuthenticationProvider daoAuthenticationProvider(){

        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(principalDetailsService);
        daoProvider.setPasswordEncoder(passwordEncoder());

        return daoProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PATCH", "OPTIONS"));
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
//        configuration.setExposedHeaders(Arrays.asList("X-US-CRED", "X-XSS-Protection"));
        configuration.setMaxAge(300L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
