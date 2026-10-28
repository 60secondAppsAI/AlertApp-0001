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
@Table(name="alert_notifications")
@Getter @Setter @NoArgsConstructor
public class AlertNotification {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="alert_notification_id")
	private Integer alertNotificationId;
    
  	@Column(name="alert_id")
	private Integer alertId;
    
  	@Column(name="recipient")
	private String recipient;
    
  	@Column(name="channel")
	private String channel;
    
  	@Column(name="sent_at")
	private Date sentAt;
    
  	@Column(name="status")
	private String status;
    

}
