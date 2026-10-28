package com.AlertApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AlertNotificationSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer alertNotificationId;
	
	private Integer alertId;
	
	private String recipient;
	
	private String channel;
	
	private Date sentAt;
	
	private String status;
	
}
