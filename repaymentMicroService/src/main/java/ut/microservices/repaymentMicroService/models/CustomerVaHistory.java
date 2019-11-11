package ut.microservices.repaymentMicroService.models;

import lombok.Data;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RPYMS_CustomerVaHistory")
@Data
public class CustomerVaHistory implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer ID;

    @Column(name="TransactionID")
    @JsonProperty(value="TransactionID")
    private String TransactionID;

    @Column(name="CustomerID")
    @JsonProperty(value="CustomerID")
    private Integer CustomerID;

    @Column(name="ApplicantID")
    @JsonProperty(value="ApplicantID")
    private String ApplicantID;

    @Column(name="VaCreatedTime")
    private Date VaCreatedTime;

    @Column(name="VaNumber")
    private String VaNumber;

    @Column(name="LoanIndex", nullable=true)
    private Integer LoanIndex;

    @Column(name="PartialIndex", nullable=true)
    private Integer PartialIndex;

    @Column(name="AmountToPay")
    @JsonProperty(value="AmountToPay")
    private Double AmountToPay;
    
    @Column(name="Status")
    private Integer Status;

    @Column(name="PaymentTime")
    private Date PaymentTime;

    @Column(name="NotifiedTime")
    private Date NotifiedTime;    

    @Column(name="IsVaActive", columnDefinition = "ENUM('Y','N')", nullable=true)
    // @Enumerated(EnumType.STRING)
    private String IsVaActive;

    @Column(name="PaymentMethod")
    private Integer PaymentMethod;

    @Column(name="VaCreatedOutstandingAmt", nullable=true)
    private Double VaCreatedOutstandingAmt;

    @Column(name="VaTransMerchantID", nullable=true)
    private String VaTransMerchantID;
}
