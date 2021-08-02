package com.example.book.springboot.config;

import com.example.book.springboot.domain.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.isNull;

@Component
public class LoginUserAuditorAware implements AuditorAware<String> {

    private static final String ANONYMOUS = "anonymousUser";
    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(isNull(authentication) || !authentication.isAuthenticated()){
            return null;
        }

        String loginId = (String)authentication.getName();
//        User u = (User)authentication.getPrincipal();
        if(loginId.equals(ANONYMOUS)){
            loginId = "ADMIN";
        }
        return Optional.of(loginId);
    }
}
