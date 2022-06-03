package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@Slf4j
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http security builder
        http.cors().and().csrf().disable()
                .httpBasic().disable()      // token 인증으로 대체
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션기반 아님을 설정
                .and()
                .authorizeRequests()
                .antMatchers("/","/auth/**").permitAll()
                .anyRequest().authenticated();

        // 매 요청마다 corsfilter 실행 이후 jwtAuthenticationFilter 실행한다.
        http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);
    }
}
