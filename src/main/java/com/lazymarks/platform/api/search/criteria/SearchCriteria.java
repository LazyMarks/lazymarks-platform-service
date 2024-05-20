package com.lazymarks.platform.api.search.criteria;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Sort;

@Data
@ToString
public class SearchCriteria {
    private int pageNumber = 0;
    private int pageSize = 50;
    private String sortBy = "id";
    private String sortOrder = Sort.Direction.ASC.name();
}
