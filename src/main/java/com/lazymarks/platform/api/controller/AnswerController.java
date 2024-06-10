package com.lazymarks.platform.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lazymarks.platform.api.entity.Answer;
import com.lazymarks.platform.api.model.LazymarksUserDetails;
import com.lazymarks.platform.api.service.AnswerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/answer-service/v1/answers")
public class AnswerController {

	@Autowired
	private AnswerService answerService;

	@PostMapping
	public ResponseEntity<Object> createAnswer(@RequestBody @Valid Answer answer,
			@AuthenticationPrincipal UserDetails userDetails) {
		final LazymarksUserDetails lmUserDetails = (LazymarksUserDetails) userDetails;
		answer.setCreatedBy(lmUserDetails.getUser().getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.answerService.createAnswer(answer));
	}

}
