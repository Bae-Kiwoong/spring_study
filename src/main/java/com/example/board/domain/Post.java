package com.example.board.domain;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터 저장시 사용. 라지 오브젝트(Large Object)로 저장
	@Column(nullable = false)
	private String content;
	
	@CreationTimestamp // 엔티티가 생성될 때 자동으로 현재 시간을 저장
	private Timestamp createDate;
	
	private int cnt; // 조회수
	
	@ManyToOne(fetch = FetchType.EAGER) // 다대일 관계
	@JoinColumn(name = "userId") // 외래키 설정
	private User user; // 회원
	
	
	
	
	
	
}