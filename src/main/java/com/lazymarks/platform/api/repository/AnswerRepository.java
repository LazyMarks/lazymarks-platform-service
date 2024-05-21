package com.lazymarks.platform.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lazymarks.platform.api.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>  {

}
