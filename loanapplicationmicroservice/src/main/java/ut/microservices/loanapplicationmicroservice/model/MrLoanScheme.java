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
@Table(name = "Master_LoanScheme")
@JsonIgnoreProperties(value = {""}, allowGetters = true)
@Data
public class MrLoanScheme implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer	ID;
    

    @Column(name = "LSName", nullable = true)
    private	String	LSName;

    @Column(name = "LSDesc", nullable = true)
    private	String	LSDesc;
    
    @Column(name = "LoanIncrement", nullable = true)
    private	Integer	LoanIncrement;
    
    @Column(name = "MinValue", nullable = true)
    private	Double	MinValue;
    
    @Column(name = "MaximumValue", nullable = true)
    private	Double	MaximumValue;
    
    @Column(name = "DefaultValue", nullable = true)
    private	Double	DefaultValue;
    
    @Column(name = "InterestRate", nullable = true)
    private	Double	InterestRate;
    
    @Column(name = "MinTenor", nullable = true)
    private	Integer	MinTenor;
    
    @Column(name = "MaxTenor", nullable = true)
    private	Integer	MaxTenor;
    
    @Column(name = "DefaultTenor", nullable = true)
    private	Integer	DefaultTenor;
    
    @Column(name = "StartDate", nullable = true)
    private	Date	StartDate;
    
    @Column(name = "EndDate", nullable = true)
    private	Date	EndDate;
    
    @Column(name = "LoanGracePeriod", nullable = true)
    private	Integer	LoanGracePeriod;
    
    @Column(name = "CreatedBy", nullable = true)
    private	Integer	CreatedBy;
    
    @Column(name = "CreatedTime", nullable = true)
    private	Date	CreatedTime;
    
    @Column(name = "ActiveStatus", nullable = true)
    private	String	ActiveStatus;
    
    @Column(name = "NewInterestrate", nullable = true)
    private	Double	NewInterestrate;
}