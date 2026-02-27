package com.example.taskmanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

//Request DTO
@Data
public class UserRequest {
	@NotBlank
	private String name;

	@Email
	@NotBlank
	private String email;
}
