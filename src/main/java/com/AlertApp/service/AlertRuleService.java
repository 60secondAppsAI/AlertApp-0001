package com.AlertApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.AlertApp.domain.AlertRule;
import com.AlertApp.dto.AlertRuleDTO;
import com.AlertApp.dto.AlertRuleSearchDTO;
import com.AlertApp.dto.AlertRulePageDTO;
import com.AlertApp.dto.AlertRuleConvertCriteriaDTO;
import com.AlertApp.service.GenericService;
import com.AlertApp.dto.common.RequestDTO;
import com.AlertApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AlertRuleService extends GenericService<AlertRule, Integer> {

	List<AlertRule> findAll();

	ResultDTO addAlertRule(AlertRuleDTO alertRuleDTO, RequestDTO requestDTO);

	ResultDTO updateAlertRule(AlertRuleDTO alertRuleDTO, RequestDTO requestDTO);

    Page<AlertRule> getAllAlertRules(Pageable pageable);

    Page<AlertRule> getAllAlertRules(Specification<AlertRule> spec, Pageable pageable);

	ResponseEntity<AlertRulePageDTO> getAlertRules(AlertRuleSearchDTO alertRuleSearchDTO);
	
	List<AlertRuleDTO> convertAlertRulesToAlertRuleDTOs(List<AlertRule> alertRules, AlertRuleConvertCriteriaDTO convertCriteria);

	AlertRuleDTO getAlertRuleDTOById(Integer alertRuleId);



	
}
