package ut.microservices.reconcileMicroService.Models;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "RCNMS_RekapDisbursementDetail")
// @NamedQuery(name="RekapDisbursementDetail.findAll", query="SELECT b FROM RekapDisbursementDetail b")
@JsonIgnoreProperties(value = {"CreatedDate","UpdatedDate"}, allowGetters = true)
@Data
public class RekapDisbursementDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private 	Integer	ID;

    @Column(name="RdID")
    private 	Integer	RdID;

    @Column(name="RequestuestID",nullable = true)
    private 	String	RequestuestID;

    @Column(name = "AccountSourceID",nullable = true)
    private 	Integer	AccountSourceID;

    @Column(name = "AccountSourceNumber",nullable = true)
    private 	String	AccountSourceNumber;  

    @Column(name="LoanID",nullable = true)
    private 	String	LoanID;     

    @Column(name="EffDate",nullable = true)
    @JsonProperty(value="Description")
    @DateTimeFormat
    private 	Date	EffDate;
    
    @Column(name="Description",nullable = true)
    @JsonProperty(value="Description")
    private 	String	Description;

    @Column(name = "Amount",nullable = true)
    private 	Double	Amount;

    @Column(name = "ReffNo",nullable = true)
    private 	String	ReffNo;  

    @Column(name="DisbursementType",nullable = true)
    private 	EnumType	DisbursementType;     

    @Column(name="DisbursementCode",nullable = true)
    private 	String	DisbursementCode; 
     
    @Column(name="IsDeleted",nullable = true)
    private 	Integer	IsDeleted;     

    @Column(name="CreatedDate")
    @DateTimeFormat
    private 	Date	CreatedDate; 
    
    @Column(name="UpdatedDate")
    @DateTimeFormat
    private 	Date	UpdatedDate; 

     
}