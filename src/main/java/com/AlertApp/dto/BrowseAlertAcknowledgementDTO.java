package com.AlertApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseAlertAcknowledgementDTO {

	private Integer ownerId;

	private Integer alertAcknowledgementId;

	private Integer alertAcknowledgementStatus;
	
	private Integer nextOrPrevious;
}

