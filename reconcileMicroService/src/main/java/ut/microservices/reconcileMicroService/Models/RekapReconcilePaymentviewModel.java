package ut.microservices.reconcileMicroService.Models;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="RCNMS_VW_RekapReconcilePayment")
@Data
public class RekapReconcilePaymentviewModel implements Serializable{

	private static  final long serialVersionUID=1L;
	
	@Id
    @Column(name = "ReconcileID")
    private 	Integer	ReconcileID;

    @Column(name = "PaymentID")
    private 	Integer	PaymentID;
  
	@Column(name="Description",nullable = true)
	public 	String	Description;

    @Column(name="Credit",nullable = true)
	public 	Double	Credit;

	@Column(name = "ReceiptDate",nullable = true)
    @DateTimeFormat
	public 	Date ReceiptDate;

    @Column(name = "ReconcileSettlementID",nullable = true)
	public 	String	ReconcileSettlementID;  

    @Column(name="IsReconcile",nullable = true)
    private 	String	IsReconcile;     
  
    @Column(name="CreatedAt",nullable = false)
    @DateTimeFormat
	private Date CreatedAt;
  
}