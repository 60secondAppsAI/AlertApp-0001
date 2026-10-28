package com.AlertApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseAlertNotificationDTO {

	private Integer ownerId;

	private Integer alertNotificationId;

	private Integer alertNotificationStatus;
	
	private Integer nextOrPrevious;
}

