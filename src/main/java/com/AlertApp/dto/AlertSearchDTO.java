package com.AlertApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AlertSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer alertId;
	
	private String alertType;
	
	private String severity;
	
	private String title;
	
	private String message;
	
	private String status;
	
	private Date createdAt;
	
}
