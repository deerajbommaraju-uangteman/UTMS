// package ut.microservices.reconcilems.models;

// import lombok.Data;

// import javax.persistence.*;

// import com.fasterxml.jackson.annotation.JsonProperty;

// import java.io.Serializable;
// import java.util.Date;

// @Entity
// @Table(name = "RCNMS_CustomerLoanInstallmentRepayment")
// // @NamedQuery(name="CustomerLoanRepayment.findAll", query="SELECT b FROM CustomerLoanRepayment b")
// @Data
// public class CustomerLoanInstallmentRepayment implements Serializable{

//     /**
//      *
//      */
//     private static final long serialVersionUID = 1L;

//     @Id
//     // @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "CustomerLoanInstallmentRepaymentID")
//     private Integer	CustomerLoanInstallmentRepaymentID;

//     @Column(name="CustomerLoanRepaymentID")
//     @JsonProperty(value="CustomerLoanRepaymentID")
//     private Integer	CustomerLoanRepaymentID;

//     @Column(name="IndexOfInstallment")
//     @JsonProperty(value="IndexOfInstallment")
//     private Integer	IndexOfInstallment;

//     @Column(name="DueDate", nullable = true)
//     @JsonProperty(value="DueDate")
//     private Date DueDate;

//     @Column(name="RepaymentDate", nullable = true)
//     @JsonProperty(value="RepaymentDate")
//     private Date RepaymentDate;

//     @Column(name="RepaymentAmount", nullable = true)
//     @JsonProperty(value="RepaymentAmount")
//     private Double RepaymentAmount;

//     @Column(name="SmsRepaymentConfirmation", nullable = true)
//     @JsonProperty(value="SmsRepaymentConfirmation")
//     private String	SmsRepaymentConfirmation;

//     @Column(name="RepaymentType", nullable = true)
//     @JsonProperty(value="RepaymentType")
//     private String	RepaymentType;

//     @Column(name="RepaymentFromBankName", nullable = true)
//     @JsonProperty(value="RepaymentFromBankName")
//     private String	RepaymentFromBankName;

//     @Column(name="RepaymentFromBankAccount", nullable = true)
//     @JsonProperty(value="RepaymentFromBankAccount")
//     private String	RepaymentFromBankAccount;

//     @Column(name="RepaymentToBankID", nullable = true)
//     @JsonProperty(value="RepaymentToBankID")
//     private Integer	RepaymentToBankID;

//     @Column(name="PartialStatus", nullable = true)
//     @JsonProperty(value="PartialStatus")
//     private String	PartialStatus;

//     @Column(name="VtransStatus", nullable = true)
//     @JsonProperty(value="VtransStatus")
//     private String	VtransStatus;

//     @Column(name="Status", nullable = true)
//     @JsonProperty(value="Status")
//     private String	Status;

//     @Column(name="JariBulkstatuspaID", nullable = true)
//     private Integer	JariBulkstatuspaID;
// }