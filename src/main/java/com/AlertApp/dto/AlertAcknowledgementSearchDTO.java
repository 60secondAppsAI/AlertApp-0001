package com.AlertApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AlertAcknowledgementSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer alertAcknowledgementId;
	
	private Integer alertId;
	
	private String acknowledgedBy;
	
	private Date acknowledgedAt;
	
}
