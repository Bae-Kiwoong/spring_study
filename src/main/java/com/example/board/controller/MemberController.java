package com.example.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.ResponseDTO;
import com.example.board.domain.User;
import com.example.board.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	// 요청받으면 회원가입페이지로 
	@GetMapping("/auth/join")
	public String join() {
		return "user/join";
	}
	
	
	@PostMapping("/auth/join")
	@ResponseBody
	public ResponseDTO<?> insertUser(@RequestBody User user) {
		User findUser = memberService.getUser(user.getUsername());
		
		if(findUser.getUsername() == null) {
			memberService.insertUser(user);
			
			return new ResponseDTO<>( HttpStatus.OK.value(), user.getUsername() + "님 회원가입 성공!" );
		} else {
			return new ResponseDTO<>( HttpStatus.BAD_REQUEST.value(), user.getUsername() + "님은 이미 가입한 회원입니다.");
		}
		
	}
	
	@PostMapping("/auth/join2")
	public String insertUser2(User user, Model model) {
		memberService.insertUser(user);
		
		model.addAttribute("msg", user.getUsername() + "님 회원가입 성공");
		
		return "index";
	}
	
	@GetMapping("/auth/login")
	public String login() {
		
		return "user/login";
	}
	
	@PostMapping("/auth/login")
	@ResponseBody
	public ResponseDTO<?> login(@RequestBody User user, HttpSession session) {
		
		User findUser = memberService.getUser(user.getUsername());
		
		if(findUser.getUsername() == null) {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "잘못된 아이디");
		} else {
			if(user.getPassword().equals(findUser.getPassword())) {
				// 로그인 성공
				session.setAttribute("principal", findUser);
				
				return new ResponseDTO<>(HttpStatus.OK.value(), findUser.getUsername() + "님 로그인 성공");
			} else {
				return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "비밀번호 오류");
			}
		}
		
	}
	
	@GetMapping("/auth/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	@GetMapping("/auth/info")
	public String info(HttpSession session, Model model) {
		// 나중에 작업할 때 세션에 담긴 정보는 제한적이라고 가정 ( username만 있다고 칩시다 )
		User principal = (User)session.getAttribute("principal");
		
		User userInfo = memberService.getUser(principal.getUsername());
		
		model.addAttribute("info", userInfo);		
		
		return "user/info";
	}
	
	
	@PostMapping("/auth/info")
	@ResponseBody
	public ResponseDTO<?> info(@RequestBody User user, HttpSession session) {
		
		User findUser = memberService.updateUser(user);
		
		session.setAttribute("principal", findUser);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "회원정보 수정완료");
	}
	
	//회원삭제
	//탈퇴 버튼을 클릭하면 DB에서 삭제 완료 후 인덱스 페이지로 이동.
	//요청 메서드 delete , 요청 주소 :/auth/user 완료후 인덱스 페이지
	@DeleteMapping("/auth/user")
	@ResponseBody
		public ResponseDTO<?> delete(@RequestParam int id, HttpSession session) {
		
		memberService.deleteUser(id);
		// 세션에 담긴 정보도 삭제
		// 세션에 담긴 정보는 삭제하지 않아도 되지만,
		// 세션에 담긴 정보가 남아있으면,
		// 회원탈퇴 후에도 세션에 남아있는 정보로 로그인 상태가 유지되기 때문에
		// 세션에 담긴 정보도 삭제해주는 것이 좋다.
		session.invalidate();
		
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "회원탈퇴 완료");
	}
		
	
	
	
}