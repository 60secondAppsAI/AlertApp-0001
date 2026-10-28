package com.AlertApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class AlertRuleDTO {

	private Integer alertRuleId;

	private String name;

	private String ruleType;

	private Double thresholdValue;

	private Boolean active;






}
