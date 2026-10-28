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
@Table(name="alert_rules")
@Getter @Setter @NoArgsConstructor
public class AlertRule {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="alert_rule_id")
	private Integer alertRuleId;
    
  	@Column(name="name")
	private String name;
    
  	@Column(name="rule_type")
	private String ruleType;
    
  	@Column(name="threshold_value")
	private Double thresholdValue;
    
  	@Column(name="active")
	private Boolean active;
    

}
