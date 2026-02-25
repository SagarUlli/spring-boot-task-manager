package com.example.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskmanager.enums.TaskStatus;
import com.example.taskmanager.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByProjectId(Long projectId);

	List<Task> findByStatus(TaskStatus status);

	List<Task> findByProjectIdAndStatus(Long projectId, TaskStatus status);
}