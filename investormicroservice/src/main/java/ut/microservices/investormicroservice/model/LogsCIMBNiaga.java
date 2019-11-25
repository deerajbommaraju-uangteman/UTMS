package ut.microservices.investormicroservice.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "INVMS_LogsCimbniaga")
@Data
public class LogsCIMBNiaga implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer ID;

    @Column(name="RequestID", nullable = true)
    private String requestID;

    @Column(name="LoanAppID")
    private String loanAppID;

    @Column(name="UtBankID")
    private Integer utBankID;

    @Column(name="BankAccountNumber")
    private String bankAccountNumber;

    @Column(name="BankAccountName")
    private String bankAccountName;

    @Column(name="BankCode")
    private String bankCode;

    @Column(name="LoanAmount")
    private Double loanAmount;

    @Column(name="AuthToken", nullable = true)
    private String authToken;

    @Column(name="Status", nullable = true)
    private String status;

    @Column(name="ExceptionMessage", nullable = true)
    private String exceptionMessage;

    @Column(name="Message", nullable = true)
    private String message;

    @Column(name="keyChecking")
    private String KeyChecking;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CreatedAt", nullable = true)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UpdatedAt", nullable = true)
    private Date updatedAt;

    @PrePersist
    protected void onCreated() {
        createdAt = new Date() ;
        updatedAt = new Date() ;
    }

    @PreUpdate
    protected void onUpdated() {
        updatedAt = new Date() ;
    }
}
