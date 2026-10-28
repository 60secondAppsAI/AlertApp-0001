package com.AlertApp.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;


import com.AlertApp.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.AlertApp.domain.AlertNotification;
import com.AlertApp.dto.AlertNotificationDTO;
import com.AlertApp.dto.AlertNotificationSearchDTO;
import com.AlertApp.dto.AlertNotificationPageDTO;
import com.AlertApp.service.AlertNotificationService;
import com.AlertApp.dto.common.RequestDTO;
import com.AlertApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/alertNotification")
@RestController
public class AlertNotificationController {

	private final static Logger logger = LoggerFactory.getLogger(AlertNotificationController.class);

	@Autowired
	AlertNotificationService alertNotificationService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<AlertNotification> getAll() {

		List<AlertNotification> alertNotifications = alertNotificationService.findAll();
		
		return alertNotifications;	
	}

	@GetMapping(value = "/{alertNotificationId}")
	@ResponseBody
	public AlertNotificationDTO getAlertNotification(@PathVariable Integer alertNotificationId) {
		
		return (alertNotificationService.getAlertNotificationDTOById(alertNotificationId));
	}

 	@RequestMapping(value = "/addAlertNotification", method = RequestMethod.POST)
	public ResponseEntity<?> addAlertNotification(@RequestBody AlertNotificationDTO alertNotificationDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = alertNotificationService.addAlertNotification(alertNotificationDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/alertNotifications")
	public ResponseEntity<AlertNotificationPageDTO> getAlertNotifications(AlertNotificationSearchDTO alertNotificationSearchDTO) {
 
		return alertNotificationService.getAlertNotifications(alertNotificationSearchDTO);
	}	

	@RequestMapping(value = "/updateAlertNotification", method = RequestMethod.POST)
	public ResponseEntity<?> updateAlertNotification(@RequestBody AlertNotificationDTO alertNotificationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = alertNotificationService.updateAlertNotification(alertNotificationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
