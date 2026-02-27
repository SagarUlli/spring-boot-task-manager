package com.example.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

//Request
@Data
public class ProjectRequest {
	@NotBlank
	private String name;

	@Size(max = 500)
	private String description;
}
