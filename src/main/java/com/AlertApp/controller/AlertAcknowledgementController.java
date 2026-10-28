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

import com.AlertApp.domain.AlertAcknowledgement;
import com.AlertApp.dto.AlertAcknowledgementDTO;
import com.AlertApp.dto.AlertAcknowledgementSearchDTO;
import com.AlertApp.dto.AlertAcknowledgementPageDTO;
import com.AlertApp.service.AlertAcknowledgementService;
import com.AlertApp.dto.common.RequestDTO;
import com.AlertApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/alertAcknowledgement")
@RestController
public class AlertAcknowledgementController {

	private final static Logger logger = LoggerFactory.getLogger(AlertAcknowledgementController.class);

	@Autowired
	AlertAcknowledgementService alertAcknowledgementService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<AlertAcknowledgement> getAll() {

		List<AlertAcknowledgement> alertAcknowledgements = alertAcknowledgementService.findAll();
		
		return alertAcknowledgements;	
	}

	@GetMapping(value = "/{alertAcknowledgementId}")
	@ResponseBody
	public AlertAcknowledgementDTO getAlertAcknowledgement(@PathVariable Integer alertAcknowledgementId) {
		
		return (alertAcknowledgementService.getAlertAcknowledgementDTOById(alertAcknowledgementId));
	}

 	@RequestMapping(value = "/addAlertAcknowledgement", method = RequestMethod.POST)
	public ResponseEntity<?> addAlertAcknowledgement(@RequestBody AlertAcknowledgementDTO alertAcknowledgementDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = alertAcknowledgementService.addAlertAcknowledgement(alertAcknowledgementDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/alertAcknowledgements")
	public ResponseEntity<AlertAcknowledgementPageDTO> getAlertAcknowledgements(AlertAcknowledgementSearchDTO alertAcknowledgementSearchDTO) {
 
		return alertAcknowledgementService.getAlertAcknowledgements(alertAcknowledgementSearchDTO);
	}	

	@RequestMapping(value = "/updateAlertAcknowledgement", method = RequestMethod.POST)
	public ResponseEntity<?> updateAlertAcknowledgement(@RequestBody AlertAcknowledgementDTO alertAcknowledgementDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = alertAcknowledgementService.updateAlertAcknowledgement(alertAcknowledgementDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
