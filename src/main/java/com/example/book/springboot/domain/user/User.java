package com.example.book.springboot.domain.user;

import com.example.book.springboot.common.Auditable;
import com.example.book.springboot.domain.user.dto.UserResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
@DynamicUpdate
public class User extends Auditable<String> implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 30)
    private String uid;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 255)
    private String passwd;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = true, length = 20)
    @Pattern(regexp = "([0-9]{3})-([0-9]{4})-([0-9]{4})")
    private String phone;


    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isAccountNonExpired(){
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isCredentialsNonExpired(){
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isEnabled(){
        return true;
    }

    @Builder
    public User(String uid,String passwd, String name,List<String> roles,String phone){
        this.uid = uid;
        this.passwd = passwd;
        this.name = name;
        this.roles = roles;
        this.phone = phone;
    }

    public UserResponseDto toResponse(){
        return UserResponseDto.builder().id(id).uid(uid).name(name).phone(phone).build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.passwd;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.uid;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    public void changePhoneNumber(String phone){
        this.phone = phone;
    }
}
