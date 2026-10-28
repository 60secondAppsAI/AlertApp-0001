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
import com.AlertApp.dao.AlertRuleDAO;
import com.AlertApp.domain.AlertRule;
import com.AlertApp.dto.AlertRuleDTO;
import com.AlertApp.dto.AlertRuleSearchDTO;
import com.AlertApp.dto.AlertRulePageDTO;
import com.AlertApp.dto.AlertRuleConvertCriteriaDTO;
import com.AlertApp.dto.common.RequestDTO;
import com.AlertApp.dto.common.ResultDTO;
import com.AlertApp.service.AlertRuleService;
import com.AlertApp.util.ControllerUtils;


@Service
public class AlertRuleServiceImpl extends GenericServiceImpl<AlertRule, Integer> implements AlertRuleService {

    private final static Logger logger = LoggerFactory.getLogger(AlertRuleServiceImpl.class);

	@Autowired
	AlertRuleDAO alertRuleDao;

	

	@Override
	public GenericDAO<AlertRule, Integer> getDAO() {
		return (GenericDAO<AlertRule, Integer>) alertRuleDao;
	}
	
	public List<AlertRule> findAll () {
		List<AlertRule> alertRules = alertRuleDao.findAll();
		
		return alertRules;	
		
	}

	public ResultDTO addAlertRule(AlertRuleDTO alertRuleDTO, RequestDTO requestDTO) {

		AlertRule alertRule = new AlertRule();

		alertRule.setAlertRuleId(alertRuleDTO.getAlertRuleId());

		alertRule.setName(alertRuleDTO.getName());

		alertRule.setRuleType(alertRuleDTO.getRuleType());

		alertRule.setThresholdValue(alertRuleDTO.getThresholdValue());

		alertRule.setActive(alertRuleDTO.getActive());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		alertRule = alertRuleDao.save(alertRule);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<AlertRule> getAllAlertRules(Pageable pageable) {
		return alertRuleDao.findAll(pageable);
	}

	public Page<AlertRule> getAllAlertRules(Specification<AlertRule> spec, Pageable pageable) {
		return alertRuleDao.findAll(spec, pageable);
	}

	public ResponseEntity<AlertRulePageDTO> getAlertRules(AlertRuleSearchDTO alertRuleSearchDTO) {
	
			Integer alertRuleId = alertRuleSearchDTO.getAlertRuleId(); 
 			String name = alertRuleSearchDTO.getName(); 
 			String ruleType = alertRuleSearchDTO.getRuleType(); 
   			String sortBy = alertRuleSearchDTO.getSortBy();
			String sortOrder = alertRuleSearchDTO.getSortOrder();
			String searchQuery = alertRuleSearchDTO.getSearchQuery();
			Integer page = alertRuleSearchDTO.getPage();
			Integer size = alertRuleSearchDTO.getSize();

	        Specification<AlertRule> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, alertRuleId, "alertRuleId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, ruleType, "ruleType"); 
			
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("ruleType")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<AlertRule> alertRules = this.getAllAlertRules(spec, pageable);
		
		//System.out.println(String.valueOf(alertRules.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(alertRules.getTotalPages()));
		
		List<AlertRule> alertRulesList = alertRules.getContent();
		
		AlertRuleConvertCriteriaDTO convertCriteria = new AlertRuleConvertCriteriaDTO();
		List<AlertRuleDTO> alertRuleDTOs = this.convertAlertRulesToAlertRuleDTOs(alertRulesList,convertCriteria);
		
		AlertRulePageDTO alertRulePageDTO = new AlertRulePageDTO();
		alertRulePageDTO.setAlertRules(alertRuleDTOs);
		alertRulePageDTO.setTotalElements(alertRules.getTotalElements());
		return ResponseEntity.ok(alertRulePageDTO);
	}

	public List<AlertRuleDTO> convertAlertRulesToAlertRuleDTOs(List<AlertRule> alertRules, AlertRuleConvertCriteriaDTO convertCriteria) {
		
		List<AlertRuleDTO> alertRuleDTOs = new ArrayList<AlertRuleDTO>();
		
		for (AlertRule alertRule : alertRules) {
			alertRuleDTOs.add(convertAlertRuleToAlertRuleDTO(alertRule,convertCriteria));
		}
		
		return alertRuleDTOs;

	}
	
	public AlertRuleDTO convertAlertRuleToAlertRuleDTO(AlertRule alertRule, AlertRuleConvertCriteriaDTO convertCriteria) {
		
		AlertRuleDTO alertRuleDTO = new AlertRuleDTO();

		alertRuleDTO.setAlertRuleId(alertRule.getAlertRuleId());

		alertRuleDTO.setName(alertRule.getName());

		alertRuleDTO.setRuleType(alertRule.getRuleType());

		alertRuleDTO.setThresholdValue(alertRule.getThresholdValue());

		alertRuleDTO.setActive(alertRule.getActive());
		
		return alertRuleDTO;
	}

	public ResultDTO updateAlertRule(AlertRuleDTO alertRuleDTO, RequestDTO requestDTO) {
		
		AlertRule alertRule = alertRuleDao.getById(alertRuleDTO.getAlertRuleId());
		
		alertRule.setAlertRuleId(ControllerUtils.setValue(alertRule.getAlertRuleId(), alertRuleDTO.getAlertRuleId()));
		
		alertRule.setName(ControllerUtils.setValue(alertRule.getName(), alertRuleDTO.getName()));
		
		alertRule.setRuleType(ControllerUtils.setValue(alertRule.getRuleType(), alertRuleDTO.getRuleType()));
		
		alertRule.setThresholdValue(ControllerUtils.setValue(alertRule.getThresholdValue(), alertRuleDTO.getThresholdValue()));
		
		alertRule.setActive(ControllerUtils.setValue(alertRule.getActive(), alertRuleDTO.getActive()));

        alertRule = alertRuleDao.save(alertRule);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AlertRuleDTO getAlertRuleDTOById(Integer alertRuleId) {
	
		AlertRule alertRule = alertRuleDao.getById(alertRuleId);
		
		AlertRuleConvertCriteriaDTO convertCriteria = new AlertRuleConvertCriteriaDTO();
		return(this.convertAlertRuleToAlertRuleDTO(alertRule,convertCriteria));
	}

}
