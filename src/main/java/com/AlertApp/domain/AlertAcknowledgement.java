package com.AlertApp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;

@Entity
@Table(name="alert_acknowledgements")
@Getter @Setter @NoArgsConstructor
public class AlertAcknowledgement {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="alert_acknowledgement_id")
	private Integer alertAcknowledgementId;
    
  	@Column(name="alert_id")
	private Integer alertId;
    
  	@Column(name="acknowledged_by")
	private String acknowledgedBy;
    
  	@Column(name="acknowledged_at")
	private Date acknowledgedAt;
    

}
