package com.ltp.gradesubmission.security;



import com.ltp.gradesubmission.security.filter.AuthenticationFilter;
import com.ltp.gradesubmission.security.filter.ExceptionHandlerFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import lombok.AllArgsConstructor;

import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@AllArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setFilterProcessesUrl("/authenticate");
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,SecurityConstants.REGISTER_PATH).permitAll()// allows every post on the register path
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new ExceptionHandlerFilter(),AuthenticationFilter.class)
                .addFilter(authenticationFilter)

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}