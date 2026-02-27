package com.example.taskmanager.dto;

import com.example.taskmanager.enums.TaskPriority;
import com.example.taskmanager.enums.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

//Request
@Data
public class TaskRequest {
	@NotBlank
	private String title;

	private String description;

	@NotNull
	private TaskPriority priority;

	private TaskStatus status; // optional, default TODO
}
