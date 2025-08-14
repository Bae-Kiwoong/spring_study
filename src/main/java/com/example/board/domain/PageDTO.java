package com.example.board.domain;
import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	
	private final int BLOCK_SIZE = 10; // 한 블록에 표시할 페이지 수
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int totalPages;
	private long totalElements;
	private Page<Post> page;
	private int nextPage;
	
	public PageDTO(Page<Post> page) {
		// 기본 생성자
		this.page = page;
		this.totalPages = page.getTotalPages();
		this.totalElements = page.getTotalElements();
		
		//현재페이지
		int currentPage = page.getNumber() + 1; // 페이지 번호는 0부터 시작하므로 +1
		this.endPage = (int)(Math.ceil(currentPage / (double)BLOCK_SIZE) * BLOCK_SIZE);
		this.startPage = this.endPage - BLOCK_SIZE + 1;
		if(this.totalPages < this.endPage) {
			this.endPage = this.totalPages;
			this.prev = this.startPage > 1;
		}
		// next 버튼 활성화 조건을 현재 페이지가 마지막이 아닐 때로 변경
		this.next = page.hasNext();
	}
}