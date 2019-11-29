package ut.microservices.loanapplicationmicroservice.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * CreFeatureSetModel
 */
@Entity
@Table(name = "LAMS_CreFeatureSet")
@JsonIgnoreProperties(value = {"CreateAt"}, allowGetters = true)
@Data
public class CreFeatureSetModel implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CreFeatureSetID")
    private 	Integer	CreFeatureSetID;
    
    @Column(name = "ApplicantID")
    private 	Integer	ApplicantID;
    
    @Column(name = "LoanType")
    private 	Integer	LoanType;
    
    @Column(name = "ApplicantType")
    private 	Integer	ApplicantType;
    
    @Column(name = "IsSpecial")
    private 	Integer	IsSpecial;
    
    @Column(name = "Age")
    private 	Integer	Age;
    
    @Column(name = "Sex")
    private 	Integer	Sex;
    
    @Column(name = "MaritalStatus")
    private 	Integer	MaritalStatus;
    
    @Column(name = "Education")
    private 	Integer	Education;
    
    @Column(name = "Dependents")
    private 	Integer	Dependents;
    
    @Column(name = "NetIncome")
    private 	Double	NetIncome;
    
    @Column(name = "MainExpenses")
    private 	Double	MainExpenses;
    
    @Column(name = "HouseLoan")
    private 	Double	HouseLoan;
    
    @Column(name = "MaxBalance")
    private 	Double	MaxBalance;
}