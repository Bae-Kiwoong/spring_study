package com.example.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.board.domain.Post;

@Repository
public interface postRepository extends JpaRepository<Post, Integer> {

	
	
	
	
}
