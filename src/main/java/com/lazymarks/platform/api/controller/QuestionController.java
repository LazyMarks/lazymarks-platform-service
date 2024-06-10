package com.lazymarks.platform.api.controller;

import com.lazymarks.platform.api.search.criteria.QuestionCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.lazymarks.platform.api.entity.Question;
import com.lazymarks.platform.api.model.LazymarksUserDetails;
import com.lazymarks.platform.api.service.AnswerService;
import com.lazymarks.platform.api.service.QuestionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/question-service/v1/questions")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;

	@PostMapping
	public ResponseEntity<Object> createQuestion(@RequestBody @Valid Question question,
			@AuthenticationPrincipal UserDetails userDetails) {
		final LazymarksUserDetails lmUserDetails = (LazymarksUserDetails) userDetails;
		question.setCreatedBy(lmUserDetails.getUser().getId());
		question.setUpdatedBy(lmUserDetails.getUser().getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.questionService.createQuestion(question));
	}

	@GetMapping
	public ResponseEntity<Object> getQuestions(QuestionCriteria questionCriteria) {
		return ResponseEntity.ok(this.questionService.getQuestions(questionCriteria));
	}

	@GetMapping("/{questionId}/answers")
	public ResponseEntity<Object> getAnswersByQuestionId(@PathVariable Long questionId) {

		Question question = this.questionService.getQuestionById(questionId);
		if (question == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("question not found");
		}

		return ResponseEntity.ok(this.answerService.getAnswersByQuestionId(questionId));
	}
}
