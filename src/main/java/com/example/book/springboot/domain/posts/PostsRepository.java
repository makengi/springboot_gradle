package com.example.book.springboot.domain.posts;

import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts,Long> {
}
