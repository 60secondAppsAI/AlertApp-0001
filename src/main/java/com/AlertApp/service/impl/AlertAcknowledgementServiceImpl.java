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
import com.AlertApp.dao.AlertAcknowledgementDAO;
import com.AlertApp.domain.AlertAcknowledgement;
import com.AlertApp.dto.AlertAcknowledgementDTO;
import com.AlertApp.dto.AlertAcknowledgementSearchDTO;
import com.AlertApp.dto.AlertAcknowledgementPageDTO;
import com.AlertApp.dto.AlertAcknowledgementConvertCriteriaDTO;
import com.AlertApp.dto.common.RequestDTO;
import com.AlertApp.dto.common.ResultDTO;
import com.AlertApp.service.AlertAcknowledgementService;
import com.AlertApp.util.ControllerUtils;


@Service
public class AlertAcknowledgementServiceImpl extends GenericServiceImpl<AlertAcknowledgement, Integer> implements AlertAcknowledgementService {

    private final static Logger logger = LoggerFactory.getLogger(AlertAcknowledgementServiceImpl.class);

	@Autowired
	AlertAcknowledgementDAO alertAcknowledgementDao;

	

	@Override
	public GenericDAO<AlertAcknowledgement, Integer> getDAO() {
		return (GenericDAO<AlertAcknowledgement, Integer>) alertAcknowledgementDao;
	}
	
	public List<AlertAcknowledgement> findAll () {
		List<AlertAcknowledgement> alertAcknowledgements = alertAcknowledgementDao.findAll();
		
		return alertAcknowledgements;	
		
	}

	public ResultDTO addAlertAcknowledgement(AlertAcknowledgementDTO alertAcknowledgementDTO, RequestDTO requestDTO) {

		AlertAcknowledgement alertAcknowledgement = new AlertAcknowledgement();

		alertAcknowledgement.setAlertAcknowledgementId(alertAcknowledgementDTO.getAlertAcknowledgementId());

		alertAcknowledgement.setAlertId(alertAcknowledgementDTO.getAlertId());

		alertAcknowledgement.setAcknowledgedBy(alertAcknowledgementDTO.getAcknowledgedBy());

		alertAcknowledgement.setAcknowledgedAt(alertAcknowledgementDTO.getAcknowledgedAt());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		alertAcknowledgement = alertAcknowledgementDao.save(alertAcknowledgement);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<AlertAcknowledgement> getAllAlertAcknowledgements(Pageable pageable) {
		return alertAcknowledgementDao.findAll(pageable);
	}

	public Page<AlertAcknowledgement> getAllAlertAcknowledgements(Specification<AlertAcknowledgement> spec, Pageable pageable) {
		return alertAcknowledgementDao.findAll(spec, pageable);
	}

	public ResponseEntity<AlertAcknowledgementPageDTO> getAlertAcknowledgements(AlertAcknowledgementSearchDTO alertAcknowledgementSearchDTO) {
	
			Integer alertAcknowledgementId = alertAcknowledgementSearchDTO.getAlertAcknowledgementId(); 
 			Integer alertId = alertAcknowledgementSearchDTO.getAlertId(); 
 			String acknowledgedBy = alertAcknowledgementSearchDTO.getAcknowledgedBy(); 
 			Date acknowledgedAt = alertAcknowledgementSearchDTO.getAcknowledgedAt();
 			String sortBy = alertAcknowledgementSearchDTO.getSortBy();
			String sortOrder = alertAcknowledgementSearchDTO.getSortOrder();
			String searchQuery = alertAcknowledgementSearchDTO.getSearchQuery();
			Integer page = alertAcknowledgementSearchDTO.getPage();
			Integer size = alertAcknowledgementSearchDTO.getSize();

	        Specification<AlertAcknowledgement> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, alertAcknowledgementId, "alertAcknowledgementId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, alertId, "alertId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, acknowledgedBy, "acknowledgedBy"); 
			
			spec = ControllerUtils.andIfNecessary(spec, acknowledgedAt, "acknowledgedAt");
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("acknowledgedBy")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<AlertAcknowledgement> alertAcknowledgements = this.getAllAlertAcknowledgements(spec, pageable);
		
		//System.out.println(String.valueOf(alertAcknowledgements.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(alertAcknowledgements.getTotalPages()));
		
		List<AlertAcknowledgement> alertAcknowledgementsList = alertAcknowledgements.getContent();
		
		AlertAcknowledgementConvertCriteriaDTO convertCriteria = new AlertAcknowledgementConvertCriteriaDTO();
		List<AlertAcknowledgementDTO> alertAcknowledgementDTOs = this.convertAlertAcknowledgementsToAlertAcknowledgementDTOs(alertAcknowledgementsList,convertCriteria);
		
		AlertAcknowledgementPageDTO alertAcknowledgementPageDTO = new AlertAcknowledgementPageDTO();
		alertAcknowledgementPageDTO.setAlertAcknowledgements(alertAcknowledgementDTOs);
		alertAcknowledgementPageDTO.setTotalElements(alertAcknowledgements.getTotalElements());
		return ResponseEntity.ok(alertAcknowledgementPageDTO);
	}

	public List<AlertAcknowledgementDTO> convertAlertAcknowledgementsToAlertAcknowledgementDTOs(List<AlertAcknowledgement> alertAcknowledgements, AlertAcknowledgementConvertCriteriaDTO convertCriteria) {
		
		List<AlertAcknowledgementDTO> alertAcknowledgementDTOs = new ArrayList<AlertAcknowledgementDTO>();
		
		for (AlertAcknowledgement alertAcknowledgement : alertAcknowledgements) {
			alertAcknowledgementDTOs.add(convertAlertAcknowledgementToAlertAcknowledgementDTO(alertAcknowledgement,convertCriteria));
		}
		
		return alertAcknowledgementDTOs;

	}
	
	public AlertAcknowledgementDTO convertAlertAcknowledgementToAlertAcknowledgementDTO(AlertAcknowledgement alertAcknowledgement, AlertAcknowledgementConvertCriteriaDTO convertCriteria) {
		
		AlertAcknowledgementDTO alertAcknowledgementDTO = new AlertAcknowledgementDTO();

		alertAcknowledgementDTO.setAlertAcknowledgementId(alertAcknowledgement.getAlertAcknowledgementId());

		alertAcknowledgementDTO.setAlertId(alertAcknowledgement.getAlertId());

		alertAcknowledgementDTO.setAcknowledgedBy(alertAcknowledgement.getAcknowledgedBy());

		alertAcknowledgementDTO.setAcknowledgedAt(alertAcknowledgement.getAcknowledgedAt());
		
		return alertAcknowledgementDTO;
	}

	public ResultDTO updateAlertAcknowledgement(AlertAcknowledgementDTO alertAcknowledgementDTO, RequestDTO requestDTO) {
		
		AlertAcknowledgement alertAcknowledgement = alertAcknowledgementDao.getById(alertAcknowledgementDTO.getAlertAcknowledgementId());
		
		alertAcknowledgement.setAlertAcknowledgementId(ControllerUtils.setValue(alertAcknowledgement.getAlertAcknowledgementId(), alertAcknowledgementDTO.getAlertAcknowledgementId()));
		
		alertAcknowledgement.setAlertId(ControllerUtils.setValue(alertAcknowledgement.getAlertId(), alertAcknowledgementDTO.getAlertId()));
		
		alertAcknowledgement.setAcknowledgedBy(ControllerUtils.setValue(alertAcknowledgement.getAcknowledgedBy(), alertAcknowledgementDTO.getAcknowledgedBy()));
		
		alertAcknowledgement.setAcknowledgedAt(ControllerUtils.setValue(alertAcknowledgement.getAcknowledgedAt(), alertAcknowledgementDTO.getAcknowledgedAt()));

        alertAcknowledgement = alertAcknowledgementDao.save(alertAcknowledgement);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AlertAcknowledgementDTO getAlertAcknowledgementDTOById(Integer alertAcknowledgementId) {
	
		AlertAcknowledgement alertAcknowledgement = alertAcknowledgementDao.getById(alertAcknowledgementId);
		
		AlertAcknowledgementConvertCriteriaDTO convertCriteria = new AlertAcknowledgementConvertCriteriaDTO();
		return(this.convertAlertAcknowledgementToAlertAcknowledgementDTO(alertAcknowledgement,convertCriteria));
	}

}
