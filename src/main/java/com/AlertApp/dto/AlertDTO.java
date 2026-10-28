package com.AlertApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class AlertDTO {

	private Integer alertId;

	private String alertType;

	private String severity;

	private String title;

	private String message;

	private String status;

	private Date createdAt;






}
