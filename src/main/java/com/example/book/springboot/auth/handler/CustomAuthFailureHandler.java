package com.example.book.springboot.auth.handler;

import com.example.book.springboot.auth.inf.ExceptionProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler implements ExceptionProcessor {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.debug("@ CustomAuthenticationFailureHandler.onAuthenticationFailure..");
        super.onAuthenticationFailure(request,response,exception);
    }

    @Override
    public void makeExceptionRespoinse(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {

    }
}
