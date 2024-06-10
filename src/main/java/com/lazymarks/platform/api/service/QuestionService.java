package com.lazymarks.platform.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lazymarks.platform.api.entity.Question;
import com.lazymarks.platform.api.repository.QuestionRepository;
import com.lazymarks.platform.api.search.criteria.QuestionCriteria;

//@Slf4j
@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question getQuestionById(Long questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }

    public List<Question> getQuestions(QuestionCriteria criteria) {
        return questionRepository.findAll(); 
    }


}
