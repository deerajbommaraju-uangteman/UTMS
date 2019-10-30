package ut.microservices.investorMicroService.model;

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

    @NotBlank
    @Column(name="InvestorID")
    private Integer investorID;

    @NotBlank
    @Column(name="LoanID")
    private String loanID;

    @NotBlank
    @Column(name="ApplicationID")
    private Integer applicationID;

    @NotBlank
    @Column(name="LoanAmount")
    private Double loanAmount;
    
    @NotBlank
    @Column(name="VaNumber")
    private Integer vaNumber;

    @NotBlank
    @Column(name="Status")
    private Integer status;

    @NotBlank
    @Column(name="PaymentTime")
    private Date paymentTime;

    @NotBlank
    @Column(name="BankID")
    private Integer bankID;

    @Column(name="CreatedDateTime",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;


    @NotBlank
    @Column(name="CreatedBy")
    private Integer createdBy;


    @Column(name="UpdatedDatetime",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;

    @NotBlank
    @Column(name="UpdatedBy")
    private Integer updatedBy;


    @NotBlank
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