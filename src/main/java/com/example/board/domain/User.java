package com.example.board.domain;



import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 번호 설정
	private int  id;
	
	@Column(nullable = false, length = 50, unique = true)
	private String username;
	
	@Column(length = 100)
	private String password;
	
	@Column(nullable = false, length = 100)
	private String email;
	
	@Enumerated(EnumType.STRING) // 
	private RoleType role;
	
	@CreationTimestamp
	private Timestamp createDate;

}
//이상태가 기본세팅으로 DB에 저장할수 있게 함. 제약조건 설정가능.	

