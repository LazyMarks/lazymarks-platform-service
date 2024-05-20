package com.lazymarks.platform.api.search.criteria;

import lombok.*;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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
