package com.example.book.springboot.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenProvider {
    private final long tokenValidMillSecond = 1000L * 60 * 60;

    private String secretKey = "jiyoung";

    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String userPk, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userPk);
        log.info("@ claims: {}", claims);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // data
                .setIssuedAt(now) // token 발행 일자
                .setExpiration(new Date(now.getTime() + tokenValidMillSecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //Jwt 토큰으로 인증 정보를 조회
    public Authentication getAuthentication(String token) {
        log.info("@ getAuth: {}", token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        log.info("@ userDetails.getUsername() :{}", userDetails.getUsername());
        log.info("@ userDetails.getPassword() :{}", userDetails.getPassword());
        log.info("@ userDetails: {}", userDetails.toString());
        log.info("@ userDetails.authorities:{}", userDetails.getAuthorities().toString());
        return new UsernamePasswordAuthenticationToken(userDetails, " ", userDetails.getAuthorities());
    }

    // jWT 토큰에서 회원 유일 식별를 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    //Request의 헤더에서 token을 파싱: "X-auth-Token: jwt토큰
    public String resolveToken(HttpServletRequest req) {
        return req.getHeader("X-AUTH-TOKEN");
    }

    //Jwt 토큰의 유효성 만료일자 확인
    public boolean validateToken(String token) {
        try {
            log.info("@ validate Token: {}", token);
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
