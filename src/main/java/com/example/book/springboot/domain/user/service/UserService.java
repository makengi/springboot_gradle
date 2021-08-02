package com.example.book.springboot.domain.user.service;

import com.example.book.springboot.domain.user.User;
import com.example.book.springboot.domain.user.dto.UserResponseDto;
import com.example.book.springboot.domain.user.dto.UserSignUpDto;
import com.example.book.springboot.domain.user.dto.UserUpdateDto;
import com.example.book.springboot.domain.user.repo.UserRepository;
import com.example.book.springboot.global.exception.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserServiceInf , UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User save(UserSignUpDto signUpDto) {
        return userRepository.save(signUpDto.toEntity());
    }

    @Override
    public UserDetails loadUserByUsername(String pk) throws UsernameNotFoundException {
        return userRepository.findByUid(pk).orElseThrow(()->new AccountNotFoundException(pk));

    }

    @Override
    public User findByUid(String uid) {
        return userRepository.findByUid(uid).orElseThrow(()->new AccountNotFoundException(uid));
    }

    @Override
    public UserResponseDto getOne(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(()->new AccountNotFoundException(id))
                .toResponse();
    }

    @Override
    public List<UserResponseDto> getAll() {
         return userRepository.findAll()
                        .stream()
                        .map(u->UserResponseDto.builder()
                                .id(u.getId())
                                .name(u.getName())
                                .uid(u.getUid())
                                .build()
                        ).collect(Collectors.toList());
    }

    @Override
    public UserResponseDto update(UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(userUpdateDto.getId()).orElseThrow(()->new AccountNotFoundException(userUpdateDto.getId()));
        user.changePhoneNumber(userUpdateDto.getPhone());
        return userRepository.save(user).toResponse();
    }
}
