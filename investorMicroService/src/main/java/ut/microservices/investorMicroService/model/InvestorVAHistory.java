package ut.microservices.investormicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "INVMS_InvestorVaHistory")
@JsonIgnoreProperties(value = {"CreatedAt","UpdatedAt"}, allowGetters = true)
@Data
public class InvestorVAHistory implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long ID;

    
    @Column(name="InvestorID")
    private Integer investorID;

    @NotBlank
    @Column(name="LoanID")
    private String loanAppID;

    
    @Column(name="ApplicationID")
    private Integer applicationID;

    
    @Column(name="LoanAmount")
    private Double loanAmount;
    
    
    @Column(name="VaNumber")
    private Integer vaNumber;

    
    @Column(name="Status")
    private Integer status;

    
    @Column(name="PaymentTime")
    private Date paymentTime;

    
    @Column(name="BankID")
    private Integer bankID;

    @Column(name="CreatedDateTime",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;


    
    @Column(name="CreatedBy")
    private Integer createdBy;


    @Column(name="UpdatedDatetime",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;

    
    @Column(name="UpdatedBy")
    private Integer updatedBy;


    
    @Column(name="NotifiedTime")
    private Date notifiedTime;


    @PrePersist
    protected void onCreated() {
        createdDateTime = new Date() ;
        updatedDatetime = new Date() ;
    }

    @PreUpdate  
    protected void onUpdated() {
        updatedDatetime = new Date() ;
    }
}