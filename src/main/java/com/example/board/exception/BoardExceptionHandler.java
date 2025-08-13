package com.example.board.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController	 // 예외를 처리하는 클래스
@ControllerAdvice // 모든 컨트롤러에서 발생하는 예외를 처리
public class BoardExceptionHandler {
	
	@ExceptionHandler(value = Exception.class) // BoardException 예외를 처리하는 메소드
	public String handLeException(BoardException e) {
		
		// 예외 발생 시 클라이언트에게 전달할 메시지
		
		return "<h1>" + e.getMessage() + "</h1>";
	}

}
