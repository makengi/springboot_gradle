package com.example.book.springboot.auth.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public boolean postOnly = true;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        super.setAuthenticationManager(authenticationManager);
    }

    /*
         해당 필터에서 인증 프로세스 이전 요청에서 사용자 정보를 가져와
         Authentication 객체를 인증 프로세스 객체에게 전달 하는 역할
     */

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.debug("@ JwtAuthentication.attempAuth");
        /*
            Post로 넘어왔는지 확인
         */
        if(postOnly && !request.getMethod().equals("POST")){
            throw new AuthenticationServiceException(
                    "Authentication method not supported: "+request.getMethod()
            );
        }
        String name = obtainUsername(request);
        String passwd = obtainPassword(request);

        log.info("@ name: {}", name);
        log.info("@ passwd: {}", passwd);

        if(StringUtils.isEmpty(name)){
            name = "";
        }
        if(StringUtils.isEmpty(passwd)){
            passwd = "";
        }
        name = name.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(name,passwd);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}

