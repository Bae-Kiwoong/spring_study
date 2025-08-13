package com.example.board.repository; // 인터페이스

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.board.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> { // 엔티티 클래스명, 기본키 필드의 자료형
	
		
	Optional<User> findByUsername(String username);

}

// JpaRepository를 상속받으면 기본적인 CRUD 메소드가 자동으로 생성됨