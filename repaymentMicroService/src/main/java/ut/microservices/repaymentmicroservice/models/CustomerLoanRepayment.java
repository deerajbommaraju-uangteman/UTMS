package ut.microservices.repaymentmicroservice.models;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RPYMS_CustomerLoanRepayment")
// @NamedQuery(name="CustomerLoanRepayment.findAll", query="SELECT b FROM CustomerLoanRepayment b")
@Data
public class CustomerLoanRepayment implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer Id;

    @Column(name="ApplicantID", nullable = true)
    @JsonProperty(value="ApplicantID",required=true)
    private String ApplicantID;

    @Column(name="LoanApplicationID", nullable = true)
    @JsonProperty(value="LoanApplicationID",required=true)
    private String LoanApplicationID;

    @Column(name = "DueDate", nullable = true)
    @JsonProperty(value="DueDate")
    private Date DueDate;

    @Column(name = "RepaymentDate", nullable = true)
    @JsonProperty(value="RepaymentDate")
    private Date RepaymentDate;  

    @Column(name="RepaymentAmount", nullable = true)
    @JsonProperty(value="RepaymentAmount")
    private Double RepaymentAmount;     

    @Column(name="RepaymentRemind", nullable = true)
    @JsonProperty(value="RepaymentRemind")    
    private Double RepaymentRemind;     

    @Column(name = "SmsRepaymentConfirmation", columnDefinition = "ENUM('S','R','D')", nullable = true)
    @JsonProperty(value="SmsRepaymentConfirmation")
    // @Enumerated(EnumType.STRING)
    private String SmsRepaymentConfirmation;  

    @Column(name="RepaymentType", nullable = true)
    @JsonProperty(value="RepaymentType")
    private String RepaymentType;  
    
    @Column(name="McsId", nullable = true)
    @JsonProperty(value="McsId")
    private Integer McsId;   
    
    @Column(name="MctsId", nullable = true)
    @JsonProperty(value="MctsId")
    private Integer MctsId;  
    
    @Column(name="2c2pFrom", nullable = true) 
    private String Clr2c2pFrom;      

    @Column(name="ReffCode", nullable = true)
    private String ReffCode;   
    
    @Column(name="RepaymentFromBankName", nullable = true)
    private String RepaymentFromBankName;
    
    @Column(name="RepaymentFromBankAccount", nullable = true)
    private String RepaymentFromBankAccount;    
    
    @Column(name="RepaymentToBankId", nullable = true)
    private Integer RepaymentToBankId;       

    @Column(name="2c2pStatus", nullable = true)
    private String Clr2c2pStatus;      
    
    @Column(name = "PartialStatus", columnDefinition = "ENUM('Y','N')", nullable = true)
    // @Enumerated(EnumType.STRING)
    private String PartialStatus;      
 
    @Column(name = "VtransactionStatus", columnDefinition = "ENUM('Y','P','D')", nullable = true)
    // @Enumerated(EnumType.STRING)
    private String VtransactionStatus;    
    
    @Column(name = "Status", columnDefinition = "ENUM('D','Y','N')", nullable = true)
    @JsonProperty(value="Status")
    // @Enumerated(EnumType.STRING)
    private String ClrStatus;
    
    @Column(name="JariBulkStatusPaid", nullable = true)
    private Integer JariBulkStatusPaid;      
        
}

