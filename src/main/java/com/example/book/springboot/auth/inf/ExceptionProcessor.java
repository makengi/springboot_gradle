package com.example.book.springboot.auth.inf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExceptionProcessor {
    public void makeExceptionRespoinse(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException;
}
