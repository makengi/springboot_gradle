package com.example.book.springboot.config;

import com.example.book.springboot.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // 이 기능이 RestApi를 사용하니 기본설정 사용안하는거
                .csrf().disable() // csrf 사용할거면 풀어주시고
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //jwt token으로 인증하니 불필요
                .and()
                .authorizeRequests() // 여기 아래로 오는 곳은 사용권한 체크가 들어감
                .antMatchers("/h2-console/**").permitAll() // h2 콘솔 사용하니 허가
//                    .antMatchers("/api/v1/users/signin", "/api/v1/users/signup").permitAll() // 회원가입 로그인은 다 풀어줌
                .antMatchers("/*/*/signin", "/*/*/signup").permitAll() // 회원가입 로그인은 다 풀어줌
                .anyRequest().hasRole("USER") // 나머지는 모두 인증된 회원만 접근 가능하게
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)//jwt 토큰 필터를 id 패스워드 인증 필터전에 넣는다\
                .headers().frameOptions().disable();
    }

//    @Override
//    public void configure(WebSecurity webSecurity){
//        webSecurity.ignoring().antMatchers().("/스웨거 같은거 집어 넣자")
//    }
}
