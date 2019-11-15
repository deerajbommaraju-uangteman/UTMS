package ut.microservices.reconcileMicroService.Models;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "RCNMS_Detail")
// @NamedQuery(name="RCNMS_RekapReconcile.findAll", query="SELECT b FROM RCNMS_RekapReconcile b")
@JsonIgnoreProperties(value = {"CreatedAt","UpdatedAt"}, allowGetters = true)
@Data
public class DetailModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private 	Integer	ID;

    @Column(name = "LoanID",nullable = true)
    public 	Integer	LoanID; 
    
    @Column(name = "ap_FullName",nullable = true)
    public 	Integer	ap_FullName;
    
    @Column(name = "LoanAmount",nullable = true)
	public 	Integer	LoanAmount;

    @Column(name = "Amount_Repayment",nullable = true)
	public 	Integer	Amount_Repayment;

    @Column(name = "SettlementID",nullable = true)
	public 	String	SettlementID;  

    @Column(name="Amount",nullable = true)
    private 	String	Amount;  

    @Column(name="IDPayment",nullable = true)
    private 	Integer	IDPayment;

    

    @Column(name = "TotalAmountTransferred",nullable = true)
    private 	Double	TotalAmountTransferred;

    @Column(name="VendorFeeID",nullable = true)
    private 	Integer	VendorFeeID;     


}