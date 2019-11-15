package ut.microservices.reconcileMicroService.Models;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
// import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "RCNMS_RekapReconcileDisbursement")
// @NamedQuery(name="RCNMS_RekapReconcileDisbursement.findAll", query="SELECT b FROM RCNMS_RekapReconcileDisbursement b")
@JsonIgnoreProperties(value = {"CreatedAt","UpdatedAt"}, allowGetters = true)
@Data
public class RekapReconcileDisbursement{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private 	Integer	ID;

    @Column(name="Description",nullable = true)
    private 	String	Description;

    @Column(name="TotalCredit",nullable = true)
    private 	Double	TotalCredit;

    @Column(name = "TotalFee",nullable = true)
    private 	Double	TotalFee;

    @Column(name = "ReceiptDate",nullable = true)
    @DateTimeFormat
    private 	Date	ReceiptDate;  

    @Column(name="DisbursementReconID",nullable = true)
    private 	String	DisbursementReconID;     

    @Column(name="IsReconcile",nullable = true)
    private 	EnumType	IsReconcile;

    @Column(name="LookupBankNumberID",nullable = true)
    private 	Integer	LookupBankNumberID;

    @Column(name = "Note",nullable = true)
    private 	String	Note;

    @Column(name="CreatedAt")
    @DateTimeFormat
    private 	Date	CreatedAt; 
    
    @Column(name="UpdatedAt")
    @DateTimeFormat
    private 	Date	UpdatedAt; 

     
}