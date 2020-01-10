package ut.microservices.loanapplicationmicroservice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * MrBanksList
 */
@Entity
@Table(name = "Master_Notification")
@JsonIgnoreProperties(value = {""}, allowGetters = true)
@Data
public class MrNotification implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer	ID;
    
    @Column(name = "NotificationTypeID", nullable = true)
    private	Integer	NotificationTypeID;
    
    @Column(name = "NotificationTitle", nullable = true)
    private	String	NotificationTitle;
    
    @Column(name = "NotificationMessage", nullable = true)
    private	String	NotificationMessage;
    
    @Column(name = "NotificationImage", nullable = true)
    private	String	NotificationImage;
    
    @Column(name = "NotificationStatus", nullable = true)
    private	Integer	NotificationStatus;
    
    @Column(name = "CreatedAt", nullable = true)
    private	Date	CreatedAt;
    
    @Column(name = "ModifiedAt", nullable = true)
    private	Date	ModifiedAt;
}