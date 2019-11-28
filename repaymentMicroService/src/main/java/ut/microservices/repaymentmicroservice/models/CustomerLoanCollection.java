package ut.microservices.repaymentmicroservice.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "RPYMS_CustomerLoanCollection")
@Data
public class CustomerLoanCollection implements Serializable{
   
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private 	Integer	ID;

    @Column(name="ApplicantID", nullable = true)
    @JsonProperty(value="ApplicantID")
    private 	Integer	ApplicantID;

    @Column(name="LoanApplicationID", nullable = true)
    @JsonProperty(value="LoanApplicationID")
    private 	String	LoanApplicationID;
    @Column(name="Remind1Date", nullable = true)
    @JsonProperty(value="Remind1Date")
    private 	Date	Remind1Date;

    @Column(name="Remind1Status", nullable = true)
    @JsonProperty(value="Remind1Status")
    private 	String	Remind1Status;

    @Column(name="Remind2Date", nullable = true)
    @JsonProperty(value="Remind2Date")
    private 	Date	Remind2Date;

    @Column(name="Remind2Status", nullable = true)
    @JsonProperty(value="Remind2Status")
    private 	String	Remind2Status;

    @Column(name="Remind3Date", nullable = true)
    @JsonProperty(value="Remind3Date")
    private 	Date	Remind3Date;

    @Column(name="Remind3Status", nullable = true)
    @JsonProperty(value="Remind3Status")
    private 	String	Remind3Status;

    @Column(name="RepaymentDueAmount", nullable = true)
    @JsonProperty(value="RepaymentDueAmount")
    private 	Double	RepaymentDueAmount;

    @Column(name="RepaymentDueDate", nullable = true)
    @JsonProperty(value="RepaymentDueDate")
    private 	Date	RepaymentDueDate;

    @Column(name="RepaymentStatus", nullable = true)
    @JsonProperty(value="RepaymentStatus")
    private 	String	RepaymentStatus;

    @Column(name="RepaymentExtentionStatus", nullable = true)
    @JsonProperty(value="RepaymentExtentionStatus")
    private 	String	RepaymentExtentionStatus;

    @Column(name="RepaymentExtentionDueDate", nullable = true)
    @JsonProperty(value="RepaymentExtentionDueDate")
    private 	Date	RepaymentExtentionDueDate;

    @Column(name="RecalculateRepaymentAmount", nullable = true)
    @JsonProperty(value="RecalculateRepaymentAmount")
    private 	Double	RecalculateRepaymentAmount;

    @Column(name="CountdownToDue", nullable = true)
    @JsonProperty(value="CountdownToDue")
    private 	Integer	CountdownToDue;

    @Column(name="SendToDc", nullable = true)
    @JsonProperty(value="SendToDc")
    private 	Date	SendToDc;

    @Column(name="McsID", nullable = true)
    @JsonProperty(value="McsID")
    private 	Integer	McsID;
}
