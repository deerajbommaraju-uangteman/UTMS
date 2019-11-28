package ut.microservices.reconcilems.models;

import java.io.Serializable;
// import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "RCNMS_RekapPaymentDetail")
// @NamedQuery(name="RCNMS_RekapPaymentDetail.findAll", query="SELECT b FROM RCNMS_RekapPaymentDetail b")
@JsonIgnoreProperties(value = {"CreatedAt","UpdatedAt"}, allowGetters = true)
@Data
public class RekapPaymentDetail implements Serializable {
    

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private 	Integer	ID;

    @Column(name = "IDOrder",nullable = true)
    @JsonProperty(value="IDOrder")
    private 	String	IDOrder; 

    @Column(name="Amount",nullable = true)
    @JsonProperty(value="Amount")
    private 	Double	Amount;

    @Column(name="IDPayment",nullable = true)
    @JsonProperty(value="IDPayment")
    private 	Integer	IDPayment;

    @Column(name = "PaymentCode",nullable = true)
    @JsonProperty(value="PaymentCode")
    private 	String	PaymentCode;

    @Column(name="IssuerBank",nullable = true)
    private 	String	IssuerBank;     

    @Column(name = "IssuerName",nullable = true)
    private 	String	IssuerName;

    @Column(name="NotifDate",nullable = true)
    @JsonProperty(value="NotifDate")
    @DateTimeFormat
    private 	Date	NotifDate;

    @Column(name = "InstallmentIndex",nullable = true)
    @JsonProperty(value="InstallmentIndex")
    private 	Integer	InstallmentIndex;

    @Column(name="RekCustID",nullable = true)
    @JsonProperty(value="RekCustID")
    private 	Integer	RekCustID;     

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

    @Column(name="ClprPartialIndex",nullable = true)
    @JsonProperty(value="ClprPartialIndex")
    private 	Integer	ClprPartialIndex;

	

     
}