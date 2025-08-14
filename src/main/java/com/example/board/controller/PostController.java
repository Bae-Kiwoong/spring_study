package com.example.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.PageDTO;
import com.example.board.domain.Post;
import com.example.board.domain.ResponseDTO;
import com.example.board.domain.User;
import com.example.board.service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/post/insert")
	public String insert() {
		return "post/insertPost";
	}
	
	@PostMapping("/post")
	@ResponseBody
	public ResponseDTO<?> insertPost(@RequestBody Post post, HttpSession session) {
		
		
		// 세션에서 로그인한 회원의 정보를 가져옴
		User writer = (User)session.getAttribute("principal");
			
		postService.insertPost(post, writer);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "게시글 작성 성공");
	}
	@GetMapping("/")
	public String index(Model model,@PageableDefault(size=5, sort="id",direction=Direction.DESC, page=0) Pageable pageable) {
		Page<Post> list = postService.getPostList(pageable);
		
		model.addAttribute("pageDTO", new PageDTO(list));
		model.addAttribute("postList", list);
		
		//Page 객체가 가지고 있는 정보들
//		List<Post> L = list.getContent();// 실제 데이터는 content에 있음
//		System.out.println("전체 페이지 수 : " + list.getTotalPages());
//		System.out.println("전체 레코드 수 : " + list.getTotalElements());
//		System.out.println("전체 페이지 번호 수 : " + list.getNumber());
//		System.out.println("페이지당 표시할 개수 : " + list.getSize());
//		System.out.println("페이지에 데이터가 있는지 : " + list.hasContent());
//		System.out.println("이전 페이지가 있는지 : " + list.hasPrevious());
//		System.out.println("다음 페이지가 있는지 : " + list.hasNext());
//		System.out.println("첫번째 페이지인지? : " + list.isFirst());
//		System.out.println("마지막 페이지인지? : " + list.isLast());
		
		return "index";
	}
	@GetMapping("/post/{id}")
	public String post(@PathVariable int id, Model model) {
		
		Post post = postService.getPost(id);
		
		model.addAttribute("post", post);
		
		return "post/getPost";
	}
	@GetMapping("/post/updatePost/{id}")
	public String updatePost(@PathVariable int id, Model model) {
		Post post = postService.getPost(id);
		model.addAttribute("post", post);
		
		
		return "post/updatePost";
		
	}
	@PutMapping("/post")
	@ResponseBody
	public ResponseDTO<?> updatePost(@RequestBody Post post) {
		postService.updatePost(post);
		return new ResponseDTO<>(HttpStatus.OK.value(), "게시글 수정 완료");
	}
	@DeleteMapping("/post/{id}")
	@ResponseBody
	public ResponseDTO<?> deletePost(@PathVariable int id) {
		postService.deletePost(id);
		return new ResponseDTO<>(HttpStatus.OK.value(), "게시글 삭제 완료");
		
	}
		
}	
		