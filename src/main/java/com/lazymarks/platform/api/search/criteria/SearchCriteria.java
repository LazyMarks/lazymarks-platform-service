package com.lazymarks.platform.api.search.criteria;

import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {
	private int pageNumber = 0;
	private int pageSize = 50;
	private String sortBy = "id";
	private String sortOrder = Sort.Direction.ASC.name();
}
