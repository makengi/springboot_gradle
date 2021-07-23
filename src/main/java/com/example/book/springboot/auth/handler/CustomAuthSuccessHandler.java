package com.example.book.springboot.auth.handler;

import com.example.book.springboot.auth.inf.ExceptionProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
     로그인 폼에서 인증이 성공했을때 수행되는 핸들러
     성공시 인증토큰을 쿠키에 넣어주거나 index 페이지로 리다이렉트 하는 역할을 수행
 */
@Slf4j
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements ExceptionProcessor {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.debug("CustomAuthenticationSuccessHandler.onAuth Success");
        /*
             쿠키에 토큰 삽입
         */
            super.onAuthenticationSuccess(request,response, authentication);
    }

    @Override
    public void makeExceptionRespoinse(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {

    }
}
