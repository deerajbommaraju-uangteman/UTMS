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
@Table(name = "Master_City")
@JsonIgnoreProperties(value = {"CreatedTime","ModifiedTime"}, allowGetters = true)
@Data
public class MrCity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer	ID;

    @Column(name = "ProvinceID", nullable = true)
    private	Integer	ProvinceID;
    
    @Column(name = "CityType", nullable = true)
    private	String	CityType;
    
    @Column(name = "CityName", nullable = true)
    private	String	CityName;
    
    @Column(name = "ActiveStatus", nullable = true)
    private	String	ActiveStatus;
    
    @Column(name = "CreatedBy", nullable = true)
    private	Integer	CreatedBy;
    
    @Column(name = "CreatedTime", nullable = true)
    private	Date	CreatedTime;
    
    @Column(name = "ModifiedBy", nullable = true)
    private	Integer	ModifiedBy;
    
    @Column(name = "ModifiedTime", nullable = true)
    private	Date	ModifiedTime;
}