package com.AlertApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseAlertRuleDTO {

	private Integer ownerId;

	private Integer alertRuleId;

	private Integer alertRuleStatus;
	
	private Integer nextOrPrevious;
}

