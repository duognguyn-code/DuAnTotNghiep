package com.poly.be_duan.config;

import com.poly.be_duan.jwt.JwtFilter;
import com.poly.be_duan.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userService;

    @Bean
    public JwtFilter jwtFilter(){
        return new JwtFilter();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity httpSecurity) throws Exception {
        // Get AuthenticationManager Bean
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests().antMatchers("/signup", "/api/auth/signin","/apiUser/**","/cron-jobs/*","/api/time-keep/*","/admin/**","/apiProject/*").permitAll();

        http.authorizeRequests().antMatchers( "/company/*","/role/*" )
                .hasAnyAuthority("ROLE_ADMIN");

        http.authorizeRequests().antMatchers("/open-talk/*","/api1/project/**")
                .hasAnyAuthority("ROLE_ADMIN", "ROLE_USER");

//        http.formLogin()
//                .loginPage("/user/index.html#!/login")
//                .loginProcessingUrl("/api/auth/main")
//                .defaultSuccessUrl("/api/auth/main", false)
//
//                .failureUrl("/security/login/error");

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/error");

        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

