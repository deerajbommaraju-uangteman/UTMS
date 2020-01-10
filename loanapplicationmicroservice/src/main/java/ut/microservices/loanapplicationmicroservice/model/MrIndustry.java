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
@Table(name = "Master_Industry")
@JsonIgnoreProperties(value = {""}, allowGetters = true)
@Data
public class MrIndustry implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer	ID;
    
    @Column(name = "MrIndustrycode", nullable = true)
    private	String	MrIndustrycode;
    
    @Column(name = "MrIndustry_EN", nullable = true)
    private	String	MrIndustry_EN;
    
    @Column(name = "MrIndustryID", nullable = true)
    private	String	MrIndustryID;
    
    @Column(name = "MrIndustrystate", nullable = true)
    private	String	MrIndustrystate;
    
    @Column(name = "CreatedAt", nullable = true)
    private	Date	CreatedAt;
    
    @Column(name = "UpdatedAt", nullable = true)
    private	Date	UpdatedAt;
    
    @Column(name = "MrRTRWID", nullable = true)
    private	String	MrRTRWID;
    
    @Column(name = "MrIndustrySort", nullable = true)
    private	Integer	MrIndustrySort;
}