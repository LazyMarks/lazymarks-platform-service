package com.lazymarks.platform.api.service;

import com.lazymarks.platform.api.search.criteria.QuestionCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lazymarks.platform.api.entity.Question;
import com.lazymarks.platform.api.model.GenericResponse;
import com.lazymarks.platform.api.repository.QuestionRepository;

import java.util.List;

@Slf4j
@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	public GenericResponse<Question> createQuestion(Question question) {
		return GenericResponse.success(this.questionRepository.saveAndFlush(question));
	}

	public GenericResponse<List<Question>> getQuestions(QuestionCriteria questionCriteria) {
		log.info("Question search params: {}", questionCriteria);
		return null;
	}
}
