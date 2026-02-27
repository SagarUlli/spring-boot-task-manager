package com.example.taskmanager.dto;

import lombok.Data;

//Response DTO
@Data
public class UserResponse {
	private Long id;
	private String name;
	private String email;

	public UserResponse(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
}