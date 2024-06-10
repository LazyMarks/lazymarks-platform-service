package com.lazymarks.platform.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lazymarks.platform.api.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

	List<Answer> findByQuestionId(Long questionId);

}
