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
@Table(name = "Master_LoanPurpose")
@JsonIgnoreProperties(value = {""}, allowGetters = true)
@Data
public class MrLoanPurpose implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer	ID;
    
    @Column(name = "LPName", nullable = true)
    private	String	LPName;
    
    @Column(name = "LPName_EN", nullable = true)
    private	String	LPName_EN;
    
    @Column(name = "ActiveStatus", nullable = true)
    private	String	ActiveStatus;
    
    @Column(name = "CreatedBy", nullable = true)
    private	Integer	CreatedBy;
    
    @Column(name = "createdTime", nullable = true)
    private	Date	createdTime;
    
    @Column(name = "ModifiedBy", nullable = true)
    private	Integer	ModifiedBy;
    
    @Column(name = "ModifiedTime", nullable = true)
    private	Date	ModifiedTime;
}