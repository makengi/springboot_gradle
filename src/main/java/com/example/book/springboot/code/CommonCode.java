package com.example.book.springboot.code;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "code")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "code_id")
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "value")
    private Long value;


}
