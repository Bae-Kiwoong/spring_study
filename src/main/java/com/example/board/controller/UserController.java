package com.example.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.domain.RoleType;
import com.example.board.domain.User;
import com.example.board.exception.BoardException;
import com.example.board.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	//회원가입
	@PostMapping("/user")
	public String insertUser(@RequestBody User user) {
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		return user.getUsername() + "회원가입 성공";
	}
	//회원 조회 ( 회원의 id를 받아서 정보를 보여주는 기능)
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable int id) {
	
	User findUser =	userRepository.findById(id).orElseThrow(() -> {
		
			return new BoardException(id + "번 아이디는 존재하지 않습니다.");
	});
		return findUser;
	}
	
	
	//회원정보 수정
	//정보를 수정할 회원번호, 아이디 , 비밀번호 , 이메일을 포함해서 요청
	//요청받은 정보를 이용해서 update작업이 처리되도록 구현
	@PutMapping("/user")
	public String updateUser(@RequestBody User user) {
		
		User findUser = userRepository.findById(user.getId()).orElseThrow(() -> {
		
			return new BoardException(user.getId() + "번 아이디는 존재하지 않습니다.");
		});
		
		findUser.setUsername(user.getUsername());
		findUser.setPassword(user.getPassword());
		findUser.setEmail(user.getEmail());
		
		userRepository.save(findUser);
		
		return findUser.getUsername() + "회원정보 수정 성공";
	
	}
	//회원 탈퇴
	//회원번호를 받아서 해당 회원을 삭제하는 기능
	@DeleteMapping("/user/{id}")
	public User deleteUser(@PathVariable int id) {
		
		//기본키를 기준으로 검색문 처리해주는 메서드 : findById
		User findUser = userRepository.findById(id).orElseThrow(() -> {
			return new BoardException(id + "번 아이디는 존재하지 않습니다.");
		});
		
		//기본키를 기준으로 검색해서 삭제처리해주는 메서드 : deleteById
		userRepository.deleteById(id);
		
		return findUser; 
	}
	//회원 전체 조회 - findAll 메서드 (select * from _ )
	@GetMapping("/user/list")
	public List<User> getUserList() {
		
		List<User> users = userRepository.findAll();
		
		return users; 
		
	}
		
		
		
		
		
		
		
	
	
	
	
	
}
