package com.AlertApp.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.AlertApp.dao.GenericDAO;
import com.AlertApp.service.GenericService;
import com.AlertApp.service.impl.GenericServiceImpl;
import com.AlertApp.dao.AlertNotificationDAO;
import com.AlertApp.domain.AlertNotification;
import com.AlertApp.dto.AlertNotificationDTO;
import com.AlertApp.dto.AlertNotificationSearchDTO;
import com.AlertApp.dto.AlertNotificationPageDTO;
import com.AlertApp.dto.AlertNotificationConvertCriteriaDTO;
import com.AlertApp.dto.common.RequestDTO;
import com.AlertApp.dto.common.ResultDTO;
import com.AlertApp.service.AlertNotificationService;
import com.AlertApp.util.ControllerUtils;


@Service
public class AlertNotificationServiceImpl extends GenericServiceImpl<AlertNotification, Integer> implements AlertNotificationService {

    private final static Logger logger = LoggerFactory.getLogger(AlertNotificationServiceImpl.class);

	@Autowired
	AlertNotificationDAO alertNotificationDao;

	

	@Override
	public GenericDAO<AlertNotification, Integer> getDAO() {
		return (GenericDAO<AlertNotification, Integer>) alertNotificationDao;
	}
	
	public List<AlertNotification> findAll () {
		List<AlertNotification> alertNotifications = alertNotificationDao.findAll();
		
		return alertNotifications;	
		
	}

	public ResultDTO addAlertNotification(AlertNotificationDTO alertNotificationDTO, RequestDTO requestDTO) {

		AlertNotification alertNotification = new AlertNotification();

		alertNotification.setAlertNotificationId(alertNotificationDTO.getAlertNotificationId());

		alertNotification.setAlertId(alertNotificationDTO.getAlertId());

		alertNotification.setRecipient(alertNotificationDTO.getRecipient());

		alertNotification.setChannel(alertNotificationDTO.getChannel());

		alertNotification.setSentAt(alertNotificationDTO.getSentAt());

		alertNotification.setStatus(alertNotificationDTO.getStatus());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		alertNotification = alertNotificationDao.save(alertNotification);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<AlertNotification> getAllAlertNotifications(Pageable pageable) {
		return alertNotificationDao.findAll(pageable);
	}

	public Page<AlertNotification> getAllAlertNotifications(Specification<AlertNotification> spec, Pageable pageable) {
		return alertNotificationDao.findAll(spec, pageable);
	}

	public ResponseEntity<AlertNotificationPageDTO> getAlertNotifications(AlertNotificationSearchDTO alertNotificationSearchDTO) {
	
			Integer alertNotificationId = alertNotificationSearchDTO.getAlertNotificationId(); 
 			Integer alertId = alertNotificationSearchDTO.getAlertId(); 
 			String recipient = alertNotificationSearchDTO.getRecipient(); 
 			String channel = alertNotificationSearchDTO.getChannel(); 
 			Date sentAt = alertNotificationSearchDTO.getSentAt();
 			String status = alertNotificationSearchDTO.getStatus(); 
 			String sortBy = alertNotificationSearchDTO.getSortBy();
			String sortOrder = alertNotificationSearchDTO.getSortOrder();
			String searchQuery = alertNotificationSearchDTO.getSearchQuery();
			Integer page = alertNotificationSearchDTO.getPage();
			Integer size = alertNotificationSearchDTO.getSize();

	        Specification<AlertNotification> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, alertNotificationId, "alertNotificationId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, alertId, "alertId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, recipient, "recipient"); 
			
			spec = ControllerUtils.andIfNecessary(spec, channel, "channel"); 
			
			spec = ControllerUtils.andIfNecessary(spec, sentAt, "sentAt");
			
			spec = ControllerUtils.andIfNecessary(spec, status, "status"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("recipient")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("channel")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("status")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<AlertNotification> alertNotifications = this.getAllAlertNotifications(spec, pageable);
		
		//System.out.println(String.valueOf(alertNotifications.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(alertNotifications.getTotalPages()));
		
		List<AlertNotification> alertNotificationsList = alertNotifications.getContent();
		
		AlertNotificationConvertCriteriaDTO convertCriteria = new AlertNotificationConvertCriteriaDTO();
		List<AlertNotificationDTO> alertNotificationDTOs = this.convertAlertNotificationsToAlertNotificationDTOs(alertNotificationsList,convertCriteria);
		
		AlertNotificationPageDTO alertNotificationPageDTO = new AlertNotificationPageDTO();
		alertNotificationPageDTO.setAlertNotifications(alertNotificationDTOs);
		alertNotificationPageDTO.setTotalElements(alertNotifications.getTotalElements());
		return ResponseEntity.ok(alertNotificationPageDTO);
	}

	public List<AlertNotificationDTO> convertAlertNotificationsToAlertNotificationDTOs(List<AlertNotification> alertNotifications, AlertNotificationConvertCriteriaDTO convertCriteria) {
		
		List<AlertNotificationDTO> alertNotificationDTOs = new ArrayList<AlertNotificationDTO>();
		
		for (AlertNotification alertNotification : alertNotifications) {
			alertNotificationDTOs.add(convertAlertNotificationToAlertNotificationDTO(alertNotification,convertCriteria));
		}
		
		return alertNotificationDTOs;

	}
	
	public AlertNotificationDTO convertAlertNotificationToAlertNotificationDTO(AlertNotification alertNotification, AlertNotificationConvertCriteriaDTO convertCriteria) {
		
		AlertNotificationDTO alertNotificationDTO = new AlertNotificationDTO();

		alertNotificationDTO.setAlertNotificationId(alertNotification.getAlertNotificationId());

		alertNotificationDTO.setAlertId(alertNotification.getAlertId());

		alertNotificationDTO.setRecipient(alertNotification.getRecipient());

		alertNotificationDTO.setChannel(alertNotification.getChannel());

		alertNotificationDTO.setSentAt(alertNotification.getSentAt());

		alertNotificationDTO.setStatus(alertNotification.getStatus());
		
		return alertNotificationDTO;
	}

	public ResultDTO updateAlertNotification(AlertNotificationDTO alertNotificationDTO, RequestDTO requestDTO) {
		
		AlertNotification alertNotification = alertNotificationDao.getById(alertNotificationDTO.getAlertNotificationId());
		
		alertNotification.setAlertNotificationId(ControllerUtils.setValue(alertNotification.getAlertNotificationId(), alertNotificationDTO.getAlertNotificationId()));
		
		alertNotification.setAlertId(ControllerUtils.setValue(alertNotification.getAlertId(), alertNotificationDTO.getAlertId()));
		
		alertNotification.setRecipient(ControllerUtils.setValue(alertNotification.getRecipient(), alertNotificationDTO.getRecipient()));
		
		alertNotification.setChannel(ControllerUtils.setValue(alertNotification.getChannel(), alertNotificationDTO.getChannel()));
		
		alertNotification.setSentAt(ControllerUtils.setValue(alertNotification.getSentAt(), alertNotificationDTO.getSentAt()));
		
		alertNotification.setStatus(ControllerUtils.setValue(alertNotification.getStatus(), alertNotificationDTO.getStatus()));

        alertNotification = alertNotificationDao.save(alertNotification);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AlertNotificationDTO getAlertNotificationDTOById(Integer alertNotificationId) {
	
		AlertNotification alertNotification = alertNotificationDao.getById(alertNotificationId);
		
		AlertNotificationConvertCriteriaDTO convertCriteria = new AlertNotificationConvertCriteriaDTO();
		return(this.convertAlertNotificationToAlertNotificationDTO(alertNotification,convertCriteria));
	}

}
