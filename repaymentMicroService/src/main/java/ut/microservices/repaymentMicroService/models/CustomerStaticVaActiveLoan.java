package ut.microservices.repaymentmicroservice.models;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RPYMS_CustomerStaticVaActiveLoan")
@JsonIgnoreProperties(value = {"CreatedAt"},allowGetters = true) 
// @NamedQuery(name="CustomerLoanRepayment.findAll", query="SELECT b FROM CustomerLoanRepayment b")
@Data
public class CustomerStaticVaActiveLoan implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private 	Integer	ID;

    @Column(name="CldID")
    @JsonProperty(value="CldID")
    private 	Integer	CldID;

    @Column(name="ApplicantID")
    @JsonProperty(value="ApplicantID")
    private 	Integer	ApplicantID;

    @Column(name="LoanApplicationID", nullable = true)
    @JsonProperty(value="LoanApplicationID")
    private 	String	LoanApplicationID;

    @Column(name="CldMobileNumber")
    @JsonProperty(value="CldMobileNumber")
    private 	String	CldMobileNumber;

    @Column(name="MvbBinNumber")
    @JsonProperty(value="MvbBinNumber")
    private 	String	MvbBinNumber;

    @Column(name="MvbBankName", nullable = true)
    @JsonProperty(value="MvbBankName")
    private 	String	MvbBankName;

    @Column(name="IsVaActive", nullable = true)
    @JsonProperty(value="IsVaActive")
    private 	String	IsVaActive;

    @Column(name="Status", nullable = true)
    @JsonProperty(value="Status")
    private 	String	Status;

    @Column(name="CreatedAt", nullable = true)
    private 	Date	CreatedAt;

}