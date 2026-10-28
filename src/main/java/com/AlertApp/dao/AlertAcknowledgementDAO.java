package com.AlertApp.dao;

import java.util.List;
import java.util.Date;

import com.AlertApp.dao.GenericDAO;
import com.AlertApp.domain.AlertAcknowledgement;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface AlertAcknowledgementDAO extends GenericDAO<AlertAcknowledgement, Integer> {
  
	List<AlertAcknowledgement> findAll();
	


}

