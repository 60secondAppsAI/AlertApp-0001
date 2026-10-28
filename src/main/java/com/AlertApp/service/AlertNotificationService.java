package com.AlertApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.AlertApp.domain.AlertNotification;
import com.AlertApp.dto.AlertNotificationDTO;
import com.AlertApp.dto.AlertNotificationSearchDTO;
import com.AlertApp.dto.AlertNotificationPageDTO;
import com.AlertApp.dto.AlertNotificationConvertCriteriaDTO;
import com.AlertApp.service.GenericService;
import com.AlertApp.dto.common.RequestDTO;
import com.AlertApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AlertNotificationService extends GenericService<AlertNotification, Integer> {

	List<AlertNotification> findAll();

	ResultDTO addAlertNotification(AlertNotificationDTO alertNotificationDTO, RequestDTO requestDTO);

	ResultDTO updateAlertNotification(AlertNotificationDTO alertNotificationDTO, RequestDTO requestDTO);

    Page<AlertNotification> getAllAlertNotifications(Pageable pageable);

    Page<AlertNotification> getAllAlertNotifications(Specification<AlertNotification> spec, Pageable pageable);

	ResponseEntity<AlertNotificationPageDTO> getAlertNotifications(AlertNotificationSearchDTO alertNotificationSearchDTO);
	
	List<AlertNotificationDTO> convertAlertNotificationsToAlertNotificationDTOs(List<AlertNotification> alertNotifications, AlertNotificationConvertCriteriaDTO convertCriteria);

	AlertNotificationDTO getAlertNotificationDTOById(Integer alertNotificationId);



	
}
