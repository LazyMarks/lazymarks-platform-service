package com.lazymarks.platform.api.search.criteria;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionCriteria extends SearchCriteria {
    private Double starRating;
    private String startRatingOperator;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updatedAt;

    private String dateOperator;
    private Integer createdBy;
}
