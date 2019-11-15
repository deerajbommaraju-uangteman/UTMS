package ut.microservices.reconcileMicroService.Models;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RCNMS_RekapPayment")
// @NamedQuery(name="RCNMS_RekapPayment.findAll", query="SELECT b FROM RCNMS_RekapPayment b")
@JsonIgnoreProperties(value = {"CreatedAt","UpdatedAt"}, allowGetters = true)
@Data
public class RekapPayment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private 	Integer	ID;

    @Column(name = "SettlementID",nullable = true)
    @JsonProperty(value="IDOrder")
    private 	String	SettlementID; 


    @Column(name="SettlementDate",nullable = true)
    @JsonProperty(value="IDOrder")
    @DateTimeFormat
    private 	Date	SettlementDate;


    @Column(name="TransferFrom",nullable = true)
    private 	String	TransferFrom;

    @Column(name = "TotalAmount",nullable = true)
    @JsonProperty(value="IDOrder")
   private 	Double	TotalAmount;

    @Column(name="PaymentFee",nullable = true)
    @JsonProperty(value="IDOrder")
    private 	Double	PaymentFee;     

    @Column(name = "AdminFee",nullable = true)
    @JsonProperty(value="IDOrder")
    public Double	AdminFee;

    
    @Column(name="TotalAmountForMerchant",nullable = true)
    @JsonProperty(value="IDOrder")
    private 	Double	TotalAmountForMerchant;


    @Column(name = "TotalAmountTransferred",nullable = true)
    @JsonProperty(value="IDOrder")
    private 	Double	TotalAmountTransferred;

    @Column(name="VendorFeeID",nullable = true)
    @JsonProperty(value="IDOrder")
    private 	Integer	VendorFeeID;     

   

    @Column(name="CreatedAt",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedAt;

    @Column(name="UpdatedAt",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date UpdatedAt;

    @PrePersist
    protected void onCreated() {
        CreatedAt = new Date() ;
        UpdatedAt = new Date() ;
    }
}