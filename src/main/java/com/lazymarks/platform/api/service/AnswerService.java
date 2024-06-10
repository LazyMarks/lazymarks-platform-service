package com.lazymarks.platform.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lazymarks.platform.api.entity.Answer;
import com.lazymarks.platform.api.model.GenericResponse;
import com.lazymarks.platform.api.repository.AnswerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class AnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;

	public GenericResponse<Answer> createAnswer(Answer answer) {
		return GenericResponse.success(this.answerRepository.saveAndFlush(answer));
	}

	public GenericResponse<List<Answer>> getAnswersByQuestionId(Long questionId) {
		return GenericResponse.success(answerRepository.findByQuestionId(questionId));
	}

}
