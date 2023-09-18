package com.lazymarks.platform.api.model;

import lombok.Data;

@Data
public class AddressModel {
	
	private Long id;
	private String addressLine1;
	private String addressLine2;
	private String area;
	private String city;
	private String state;
	private Integer pincode;
	private String country;
}
