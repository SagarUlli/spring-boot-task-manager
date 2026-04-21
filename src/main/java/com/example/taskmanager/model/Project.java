package com.example.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;

@Entity
@Data
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@Size(max = 500)
	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnoreProperties("projects")
	@Exclude
	@lombok.ToString.Exclude
	private User owner;

	public Project() {
	}

	public Project(String name, String description, User owner) {
		this.name = name;
		this.description = description;
		this.owner = owner;
	}

}