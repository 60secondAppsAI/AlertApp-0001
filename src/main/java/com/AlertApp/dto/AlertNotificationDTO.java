package com.AlertApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class AlertNotificationDTO {

	private Integer alertNotificationId;

	private Integer alertId;

	private String recipient;

	private String channel;

	private Date sentAt;

	private String status;






}
