package com.lazymarks.platform.api.entity;

import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "body", columnDefinition = "text", length = 65536, nullable = false)
	@NotBlank(message = "Question body can't be empty")
	private String body;

	@Column(name = "star_rating")
	private Double starRating;

	@Column(name = "created_by")
	private Long createdBy;

	@CreationTimestamp
	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "updated_by")
	private Long updatedBy;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Timestamp updatedAt;

}
