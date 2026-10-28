package com.AlertApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.AlertApp.domain.AlertAcknowledgement;
import com.AlertApp.dto.AlertAcknowledgementDTO;
import com.AlertApp.dto.AlertAcknowledgementSearchDTO;
import com.AlertApp.dto.AlertAcknowledgementPageDTO;
import com.AlertApp.dto.AlertAcknowledgementConvertCriteriaDTO;
import com.AlertApp.service.GenericService;
import com.AlertApp.dto.common.RequestDTO;
import com.AlertApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AlertAcknowledgementService extends GenericService<AlertAcknowledgement, Integer> {

	List<AlertAcknowledgement> findAll();

	ResultDTO addAlertAcknowledgement(AlertAcknowledgementDTO alertAcknowledgementDTO, RequestDTO requestDTO);

	ResultDTO updateAlertAcknowledgement(AlertAcknowledgementDTO alertAcknowledgementDTO, RequestDTO requestDTO);

    Page<AlertAcknowledgement> getAllAlertAcknowledgements(Pageable pageable);

    Page<AlertAcknowledgement> getAllAlertAcknowledgements(Specification<AlertAcknowledgement> spec, Pageable pageable);

	ResponseEntity<AlertAcknowledgementPageDTO> getAlertAcknowledgements(AlertAcknowledgementSearchDTO alertAcknowledgementSearchDTO);
	
	List<AlertAcknowledgementDTO> convertAlertAcknowledgementsToAlertAcknowledgementDTOs(List<AlertAcknowledgement> alertAcknowledgements, AlertAcknowledgementConvertCriteriaDTO convertCriteria);

	AlertAcknowledgementDTO getAlertAcknowledgementDTOById(Integer alertAcknowledgementId);



	
}
