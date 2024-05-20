package com.lazymarks.platform.api.repository;

import com.lazymarks.platform.api.entity.Question;
import com.lazymarks.platform.api.enums.Operator;
import com.lazymarks.platform.api.search.criteria.QuestionCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class QuestionCriteriaRepositoryImpl implements QuestionCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Question> getQuestionsByCriteria(QuestionCriteria questionCriteria) {
        Pageable pageable = PageRequest.of(questionCriteria.getPageNumber(), questionCriteria.getPageSize());
        return this.getQuestionsByCriteria(questionCriteria, pageable);
    }

    private List<Question> getQuestionsByCriteria(QuestionCriteria params, Pageable pageable) {
        CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> searchQuery = queryBuilder.createQuery(Question.class);
        Root<Question> root = searchQuery.from(Question.class);

        // Where clause
        List<Predicate> predicates = new ArrayList<>();
        if (params.getCreatedBy() != null) {
            predicates.add(queryBuilder.equal(root.get("createdBy"), params.getCreatedBy()));
        }

        if (params.getStarRating() != null) {
            predicates.add(getPredicateBasedOnOperator(queryBuilder, root, "starRating", params.getStartRatingOperator(), params.getStarRating()));
        }

        if (params.getCreatedAt() != null) {
            predicates.add(getPredicateBasedOnOperator(queryBuilder, root, "createdAt", params.getDateOperator(), params.getCreatedAt()));
        }

        if (params.getUpdatedAt() != null) {
            predicates.add(getPredicateBasedOnOperator(queryBuilder, root, "updatedAt", params.getDateOperator(), params.getUpdatedAt()));
        }

        Predicate[] predicateArray = new Predicate[predicates.size()];
        predicates.toArray(predicateArray);
        searchQuery.where(queryBuilder.and(predicateArray));

        // Order by clause
        List<Order> orders = new ArrayList<>();
        String sortBy = params.getSortBy();
        String dir = params.getSortOrder();
        if (Sort.Direction.ASC.name().equals(dir)) {
            orders.add(queryBuilder.asc(root.get(sortBy)));
        } else if (Sort.Direction.DESC.name().equals(dir)) {
            orders.add(queryBuilder.desc(root.get(sortBy)));
        } else {
            throw new IllegalArgumentException("Invalid sort order");
        }

        searchQuery.orderBy(orders);

        // Limit and offset clause
        TypedQuery<Question> typedQuery = entityManager.createQuery(searchQuery);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());
        return typedQuery.getResultList();
    }

    public <Y extends Comparable<? super Y>> Predicate getPredicateBasedOnOperator(CriteriaBuilder queryBuilder, Root<Question> root, String field, String operator, Y value) {
        Operator op = Operator.valueOf(operator);
        return switch (op) {
            case EQ -> queryBuilder.equal(root.get(field), value);
            case GT -> queryBuilder.greaterThan(root.get(field), value);
            case LT -> queryBuilder.lessThan(root.get(field), value);
            case GTEQ -> queryBuilder.greaterThanOrEqualTo(root.get(field), value);
            case LTEQ -> queryBuilder.lessThanOrEqualTo(root.get(field), value);
        };
    }

}
