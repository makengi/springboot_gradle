package com.example.book.springboot.web;

import com.example.book.springboot.auth.JwtTokenProvider;
import com.example.book.springboot.domain.user.User;
import com.example.book.springboot.domain.user.dto.UserLoginDto;
import com.example.book.springboot.domain.user.dto.UserResponseDto;
import com.example.book.springboot.domain.user.dto.UserSignUpDto;
import com.example.book.springboot.domain.user.repo.UserRepository;
import com.example.book.springboot.domain.user.service.UserService;
import com.example.book.springboot.domain.user.service.UserServiceInf;
import com.example.book.springboot.global.exception.EmailSigninFailedException;
import com.example.book.springboot.utils.PasswordController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class SignController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserServiceInf service;

    @PostMapping(value = "/signup")
    public User save(@RequestBody  UserSignUpDto signupdto){
            log.info("@ password:{} ",signupdto.getPassword());
            return service.save(signupdto);
    }

    @PostMapping(value="/signin")
    public String login(@RequestBody UserLoginDto loginDto){
        User u = service.findByUid(loginDto.getId());
        if(!PasswordController.match(loginDto.getPassword(), u.getPassword())){
            throw new EmailSigninFailedException(loginDto.getId());
        }
        return jwtTokenProvider.createToken(u.getUid(),u.getRoles());
    }

    @GetMapping(value = "/user")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    public UserResponseDto getOne(@RequestParam Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String n = authentication.getName();
        log.info("@ authentication.getName(){}", n);
        return service.getOne(id);
    }

    @GetMapping(value = "/user/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value="access_token", required = true, dataType = "String", paramType = "header")
    })
    public List<UserResponseDto> getAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("@ authentication.getAuth() {}",authentication.getAuthorities().toString());
        return service.getAll();
    }
}
