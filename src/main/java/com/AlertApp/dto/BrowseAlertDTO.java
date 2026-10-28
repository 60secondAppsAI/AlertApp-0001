package com.AlertApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseAlertDTO {

	private Integer ownerId;

	private Integer alertId;

	private Integer alertStatus;
	
	private Integer nextOrPrevious;
}

