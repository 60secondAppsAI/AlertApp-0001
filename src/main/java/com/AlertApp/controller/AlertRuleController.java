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

import com.AlertApp.domain.AlertRule;
import com.AlertApp.dto.AlertRuleDTO;
import com.AlertApp.dto.AlertRuleSearchDTO;
import com.AlertApp.dto.AlertRulePageDTO;
import com.AlertApp.service.AlertRuleService;
import com.AlertApp.dto.common.RequestDTO;
import com.AlertApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/alertRule")
@RestController
public class AlertRuleController {

	private final static Logger logger = LoggerFactory.getLogger(AlertRuleController.class);

	@Autowired
	AlertRuleService alertRuleService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<AlertRule> getAll() {

		List<AlertRule> alertRules = alertRuleService.findAll();
		
		return alertRules;	
	}

	@GetMapping(value = "/{alertRuleId}")
	@ResponseBody
	public AlertRuleDTO getAlertRule(@PathVariable Integer alertRuleId) {
		
		return (alertRuleService.getAlertRuleDTOById(alertRuleId));
	}

 	@RequestMapping(value = "/addAlertRule", method = RequestMethod.POST)
	public ResponseEntity<?> addAlertRule(@RequestBody AlertRuleDTO alertRuleDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = alertRuleService.addAlertRule(alertRuleDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/alertRules")
	public ResponseEntity<AlertRulePageDTO> getAlertRules(AlertRuleSearchDTO alertRuleSearchDTO) {
 
		return alertRuleService.getAlertRules(alertRuleSearchDTO);
	}	

	@RequestMapping(value = "/updateAlertRule", method = RequestMethod.POST)
	public ResponseEntity<?> updateAlertRule(@RequestBody AlertRuleDTO alertRuleDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = alertRuleService.updateAlertRule(alertRuleDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
