package ut.microservices.reconcileMicroService.Models;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name="RCNMS_VW_ReconcileDetailData")
@Data
public class ReconcileDetailDataviewModel implements Serializable {

	private static  final long serialVersionUID=1L;
	
	@Id
    @Column(name = "TotalRepayment")
    private 	Double	TotalRepayment;

    @Column(name = "ReceiptDate")
    @DateTimeFormat
    private 	Date ReceiptDate;
  
	@Column(name="PaymentFee",nullable = true)
	public 	Double	PaymentFee;

    @Column(name="AdminFee",nullable = true)
	public 	Double	AdminFee;

	@Column(name = "TotalAmountForMerchant",nullable = true)
    public 	Double TotalAmountForMerchant;

    @Column(name="Credit",nullable = true)
    public 	Double	Credit;
    
    @Column(name="PaymentID",nullable = true)
	public 	BigInteger	PaymentID;

    @Column(name = "SettlementID",nullable = true)
    public 	String	SettlementID;  
    
    
    @Column(name = "TransferFrom",nullable = true)
	public 	String	TransferFrom;

    @Column(name = "SettlementDate",nullable = true)
    @DateTimeFormat
    public 	Date	SettlementDate;
    
    @Column(name="IsReconcile",nullable = true)
    private 	String	IsReconcile; 
    
    @Column(name="TotalAmountTransferred",nullable = true)
    private 	Double	TotalAmountTransferred;    

    @Column(name="VendorFeeID",nullable = true)
    private 	Integer	VendorFeeID;

    @Column(name="TotalAmount",nullable = true)
    private 	Double	TotalAmount;
 
  
}