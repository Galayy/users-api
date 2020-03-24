package com.itechart.container.spring.users.security;

import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] SWAGGER_WHITE_LIST = {
            "/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**", "/"
    };

    private final UserDetailsService userDetailsService;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) ->
                response.sendError(SC_UNAUTHORIZED, "Unauthorized");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.httpBasic().disable().csrf().disable().authorizeRequests()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers(SWAGGER_WHITE_LIST).permitAll()
                .antMatchers("/swagger-ui.html/**").permitAll()
                .anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(STATELESS).and()
                .apply(new JwtConfigurer(jwtTokenProvider));
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
        http.logout().clearAuthentication(true).invalidateHttpSession(true);
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(jwtTokenProvider.passwordEncoder());
    }

    @SneakyThrows
    @PostConstruct
    public void init() {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(jwtTokenProvider
                .passwordEncoder());
    }

}
