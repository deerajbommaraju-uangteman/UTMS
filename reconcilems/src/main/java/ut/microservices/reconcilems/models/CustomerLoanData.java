// package ut.microservices.reconcilems.models;

// import java.io.Serializable;
// import java.util.Date;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.PrePersist;
// import javax.persistence.Table;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.fasterxml.jackson.annotation.JsonProperty;

// import org.hibernate.annotations.CreationTimestamp;

// import lombok.Data;

// @Entity
// @Table(name = "RCNMS_CustomerLoanData")
// @JsonIgnoreProperties(value = {"Created","Updated"}, allowGetters = true)
// @Data
// public class CustomerLoanData implements Serializable {
   
//     /**
//      *
//      */
//     private static final long serialVersionUID = 1L;

//     @Id
//     // @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "ID")
//     private 	Integer	ID;

//     @Column(name="ApplicantID", nullable = true)
//     @JsonProperty(value="ApplicantID")
//     private 	Integer	ApplicantID;

//     @Column(name="PromoCode", nullable = true)
//     @JsonProperty(value="PromoCode")
//     private 	String	PromoCode;

//     @Column(name="LoanApplicationID", nullable = true)
//     @JsonProperty(value="LoanApplicationID")
//     private 	String	LoanApplicationID;

//     @Column(name="TrxNo", nullable = true)
//     @JsonProperty(value="TrxNo")
//     private 	Integer	TrxNo;

//     @Column(name="LoanAmount", nullable = true)
//     @JsonProperty(value="LoanAmount")
//     private 	Double	LoanAmount;

//     @Column(name="LoanDaysLength", nullable = true)
//     @JsonProperty(value="LoanDaysLength")
//     private 	Integer	LoanDaysLength;

//     @Column(name="LoanStartDatetime", nullable = true)
//     @JsonProperty(value="LoanStartDatetime")
//     private 	Date	LoanStartDatetime;

//     @Column(name="LoanDueDatetime", nullable = true)
//     @JsonProperty(value="LoanDueDatetime")
//     private 	Date	LoanDueDatetime;

//     @Column(name="LoanCountdownToDue", nullable = true)
//     @JsonProperty(value="LoanCountdownToDue")
//     private 	Integer	LoanCountdownToDue;

//     @Column(name="SendToDc", nullable = true)
//     @JsonProperty(value="SendToDc")
//     private 	Date	SendToDc;

//     @Column(name="LoanInterestRate", nullable = true)
//     @JsonProperty(value="LoanInterestRate")
//     private 	Double	LoanInterestRate;

//     @Column(name="LoanInterestFee", nullable = true)
//     @JsonProperty(value="LoanInterestFee")
//     private 	Double	LoanInterestFee;

//     @Column(name="LoanRepayAmount", nullable = true)
//     @JsonProperty(value="LoanRepayAmount")
//     private 	Double	LoanRepayAmount;

//     @Column(name="RepayManual", nullable = true)
//     @JsonProperty(value="RepayManual")
//     private 	Double	RepayManual;

//     @Column(name="StatusRepayManual", nullable = true)
//     @JsonProperty(value="StatusRepayManual")
//     private 	String	StatusRepayManual;

//     @Column(name="LoanPurpose", nullable = true)
//     @JsonProperty(value="LoanPurpose")
//     private 	String	LoanPurpose;

//     @Column(name="LoanSmsForRepay", nullable = true)
//     @JsonProperty(value="LoanSmsForRepay")
//     private 	Integer	LoanSmsForRepay;

//     @Column(name="ExtensionDay", nullable = true)
//     @JsonProperty(value="ExtensionDay")
//     private 	Integer	ExtensionDay;

//     @Column(name="ExtensionStatus", nullable = true)
//     @JsonProperty(value="ExtensionStatus")
//     private 	String	ExtensionStatus;

//     @Column(name="CusStatusID")
//     @JsonProperty(value="CusStatusID")
//     private 	Integer	CusStatusID;

//     @Column(name="Status", columnDefinition = "ENUM('Y','N','W')")
//     @CreationTimestamp
//     private 	String	Status;

//     @Column(name="Note", nullable = true)
//     @CreationTimestamp
//     private 	String	Note;

//     @Column(name="Created")
//     @JsonProperty(value="Created")
//     private 	Date	Created;
    
//     @Column(name="Updated")
//     @JsonProperty(value="Updated")
//     private 	Date	Updated;
    
//     @PrePersist
//     protected void onCreated() {
//         Created = new Date() ;
//         Updated = new Date() ;
//     }
    
// }
