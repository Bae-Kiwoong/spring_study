package com.example.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.domain.Post;
import com.example.board.domain.User;
import com.example.board.repository.postRepository;


@Service
public class PostService {
	
	@Autowired
	private postRepository postRepository;
	
	public void insertPost(Post post, User writer) {
		
		post.setUser(writer);
		post.setCnt(0);
		
		postRepository.save(post);
	}
	@Transactional(readOnly = true)
	public Page<Post> getPostList(Pageable pageable) {
		return postRepository.findAll(pageable);
	}
		 
	
	public Post getPost(int id) {
		
		Optional<Post> op = postRepository.findById(id);
		
		return op.get();
		
		
	}
	@Transactional
	public Post updatePost(Post post) {
		
		Post findPost = postRepository.findById(post.getId()).get();
		
		findPost.setTitle(post.getTitle());
		findPost.setContent(post.getContent());
		
		return findPost;
	}
	public void deletePost(int id) {
		
		postRepository.deleteById(id);
		
	}
	
		

}