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

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager Bean
        return super.authenticationManagerBean();
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

        http.authorizeRequests().antMatchers( "/api/auth/**","/user/**","/rest/user/getAccount","/rest/guest/**").permitAll();

        http.authorizeRequests().antMatchers( "/api/account/**","/api/category/**","/api/chart/**","/api/product/**" )
                .hasAnyAuthority("ROLE_ADMIN");

        http.authorizeRequests().antMatchers("/rest/uácasser/**","/api/cart/**")
                .hasAnyAuthority("ROLE_ADMIN", "ROLE_USER");

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/security/login/success")
                .defaultSuccessUrl("/security/login/success", false)

                .failureUrl("/security/login/error");

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/error");
        http.rememberMe()
                .tokenValiditySeconds(86400);
        http.logout()
                .logoutUrl("/security/logoff")// địa chỉ hệ thống xử lý
                .logoutSuccessUrl("/security/logoff/success");
        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

