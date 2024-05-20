package com.lazymarks.platform.api.repository;

import com.lazymarks.platform.api.entity.Question;
import com.lazymarks.platform.api.search.criteria.QuestionCriteria;

import java.util.List;

public interface QuestionCriteriaRepository {

    List<Question> getQuestionsByCriteria(QuestionCriteria questionCriteria);

}
