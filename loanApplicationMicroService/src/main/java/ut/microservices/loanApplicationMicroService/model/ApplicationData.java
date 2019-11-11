package ut.microservices.loanApplicationMicroService.model;

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
@Table(name = "LAMS_ApplicationData")
@JsonIgnoreProperties(value = {"CreatedAt","UpdatedAt"}, allowGetters = true)
@Data
public class ApplicationData implements Serializable{
   
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ApplicationID")
    private Integer ApplicationID;
    
    @Column(name = "ApplicationApplicantID", nullable = true)
    private Integer ApplicationApplicantID;

    @Column(name = "ApplictionAmID", nullable = true)
    private Integer ApplictionAmID;
    
    @Column(name = "PromoCode", nullable = true)
    private String PromoCode;
    
    @Column(name = "LoanApplicationID", nullable = true)
    @JsonProperty(value="LoanApplicationID",required=true)
    private String LoanApplicationID;
    
    @Column(name = "LoanAmount", nullable = true)
    @JsonProperty(value="LoanAmount",required=true)
    private Double LoanAmount;
    
    @Column(name = "LoanDaysLength", nullable = true)
    @JsonProperty(value="LoanDaysLength",required=true)
    private Integer LoanDaysLength;
    
    @Column(name = "LoanStartDateTime")
    @JsonProperty(value="LoanStartDateTime",required=true)
    private Date LoanStartDateTime;
    

    @Column(name = "LoanDueDateTime")
    @JsonProperty(value="LoanDueDateTime",required=true)
    private Date LoanDueDateTime;
    
    @Column(name = "LoanInterestRate", nullable = true)
    @JsonProperty(value="LoanInterestRate",required=true)
    private Double LoanInterestRate;
    
    @Column(name = "LoanInterestFee", nullable = true)
    @JsonProperty(value="LoanInterestFee",required=true)
    private Double LoanInterestFee;
    
    @Column(name = "LoanRepayAmount", nullable = true)
    @JsonProperty(value="LoanRepayAmount",required=true)
    private Double LoanRepayAmount;
    
    @Column(name = "LoanPurpose", nullable = true)
    @JsonProperty(value="LoanPurpose",required=true)
    private String LoanPurpose;
    
    @Column(name = "ModalUsaha", nullable = true)
    @JsonProperty(value="ModalUsaha",required=true)
    private Integer ModalUsaha;
    
    @Column(name = "UtfcApiResponseDate", nullable = true)
    private Date UtfcApiResponseDate;
    
    @Column(name = "UtfcApiReturnValue", nullable = true)
    private String UtfcApiReturnValue;
    
    @Column(name = "ParetoApiResponseDate", nullable = true)
    private Date ParetoApiResponseDate;
    
    @Column(name = "ParetoApiResponseScore", nullable = true)
    @JsonProperty(value="ParetoApiResponseScore",required=true)
    private Integer ParetoApiResponseScore;
    
    @Column(name = "ParetoApiReturnValue", nullable = true)
    private String ParetoApiReturnValue;
    
    
    @Column(name = "ParetoApiResponseVersion", nullable = true)
    private String ParetoApiResponseVersion;

    @Column(name = "CreReturnValue", nullable = true)
    @JsonProperty(value="CreReturnValue",required=true)
    private String CreReturnValue;
    
    @Column(name = "CreDatetime", nullable = true)
    @JsonProperty(value="CreDatetime",required=true)
    private Date CreDatetime;
    

    @Column(name = "CreManual", nullable = true)
    @JsonProperty(value="CreManual",required=true)
    private String CreManual;
    
    @Column(name = "OcrReturnValue", nullable = true)
    @JsonProperty(value="OcrReturnValue",required=true)
    private String OcrReturnValue;
    
    @Column(name = "OcrDatetime", nullable = true)
    @JsonProperty(value="OcrDatetime",required=true)
    private Date OcrDatetime;
    
    @Column(name = "CoverageArea", nullable = true)
    @JsonProperty(value="CoverageArea",required=true)
    private String CoverageArea;
    
    @Column(name = "SmsStatus", nullable = true)
    @JsonProperty(value="SmsStatus",required=true)
    private String SmsStatus;
    
    @Column(name = "SmsDatetime", nullable = true)
    @JsonProperty(value="SmsDatetime",required=true)
    private Date SmsDatetime;
    
    @Column(name = "ApplicationAgree", nullable = true)
    private String ApplicationAgree;
    
    @Column(name = "AgreeSignature", nullable = true)
    private String AgreeSignature;
    
    @Column(name = "ManualAssignment", nullable = true)
    private String ManualAssignment;
    
    @Column(name = "ApplicationAdrStatus", nullable = true)
    @JsonProperty(value="ApplicationAdrStatus",required=true)
    private String ApplicationAdrStatus;
    
    @Column(name = "Status", nullable = true)
    private String Status;
    
    @Column(name = "ProgramHeartAttackStatus", nullable = true)
    private String ProgramHeartAttackStatus;

    @Column(name = "RejectReason", nullable = true)
    @JsonProperty(value="RejectReason",required=true)
    private Integer RejectReason;
    
    @Column(name = "RejectExpired", nullable = true)
    @JsonProperty(value="RejectExpired",required=true)
    private Date RejectExpired;
    
    @Column(name = "SmsAttemp", nullable = true)
    @JsonProperty(value="SmsAttemp",required=true)
    private Integer SmsAttemp;
    
    @Column(name = "AttempDateTime", nullable = true)
    @JsonProperty(value="AttempDateTime",required=true)
    private Date AttempDateTime;
    
    @Column(name = "CompletedDateTime", nullable = true)
    @JsonProperty(value="CompletedDateTime",required=true)
    private Date CompletedDateTime;
    
    @Column(name = "ApplicationRepeated", nullable = true)
    @JsonProperty(value="ApplicationRepeated",required=true)
    private String ApplicationRepeated;
    
    @Column(name = "VisitToday", nullable = true)
    private String VisitToday;
    
    @Column(name = "SurveyStatus", nullable = true)
    private String SurveyStatus;
    
    @Column(name = "ApprovalStatus", nullable = true)
    private String ApprovalStatus;
    
    // @Column(name = "ApplicationOverride")
    // @JsonProperty(value="ApplicationOverride",required=true)
    // private String ApplicationOverride;
    
    @Column(name = "OfficePhoned", nullable = true)
    private String OfficePhoned;
    
    @Column(name = "Channel", nullable = true)
    private String Channel;    

    @Column(name = "IsInstallment", nullable = true)
    private String IsInstallment;

    @Column(name = "DropoffUserID", nullable = true)
    private Integer DropoffUserID;
    
    @Column(name = "CreditInfo", nullable = true)
    private String CreditInfo;
    
    @Column(name = "WarrantyRequest", nullable = true)
    @JsonProperty(value="WarrantyRequest",required=true)
    private String WarrantyRequest;
    
    @Column(name = "TransactionID", nullable = true)
    @JsonProperty(value="TransactionID",required=true)
    private String TransactionID;
    
    @Column(name = "ApplicantID")
    @JsonProperty(value="ApplicantID",required=true)
    private Integer ApplicantID;
    
    @Column(name = "Age", nullable = true)
    @JsonProperty(value="Age",required=true)
    private Integer Age;
    
    @Column(name = "ApplicantIDTemp", nullable = true)
    @JsonProperty(value="ApplicantIDTemp",required=true)
    private Integer ApplicantIDTemp;
    
    @Column(name = "BorrowerReference", nullable = true)
    @JsonProperty(value="BorrowerReference",required=true)
    private String BorrowerReference;
    
    @Column(name = "DateOfBirth", nullable = true)
    @JsonProperty(value="DateOfBirth",required=true)
    private Date DateOfBirth;
    
    @Column(name = "Education", nullable = true)
    @JsonProperty(value="Education",required=true)
    private Integer Education;
    
    @Column(name = "EmailAddress", nullable = true)
    @JsonProperty(value="EmailAddress",required=true)
    private String EmailAddress;
    
    @Column(name = "FacebookID", nullable = true)
    @JsonProperty(value="FacebookID",required=true)
    private String FacebookID;

    @Column(name = "FamilyIDNumber", nullable = true)
    private String FamilyIDNumber;
    
    @Column(name = "FullName", nullable = true)
    @JsonProperty(value="FullName",required=true)
    private String FullName;
    
    @Column(name = "Gender", nullable = true)
    @JsonProperty(value="Gender",required=true)
    private String Gender;
    
    @Column(name = "GooglePlusID", nullable = true)
    @JsonProperty(value="GooglePlusID",required=true)
    private String GooglePlusID;
    
    @Column(name = "LinkedInID", nullable = true)
    @JsonProperty(value="LinkedInID",required=true)
    private String LinkedInID;
    
    @Column(name = "MobilePrefix", nullable = true)
    @JsonProperty(value="MobilePrefix",required=true)
    private Integer MobilePrefix;
    
    @Column(name = "NationalStatus", nullable = true)
    @JsonProperty(value="NationalStatus",required=true)
    private String NationalStatus;
    
    @Column(name = "PersonalIDNumber", nullable = true)
    @JsonProperty(value="PersonalIDNumber",required=true)
    private String PersonalIDNumber;
    
    @Column(name = "PlaceOfBirth", nullable = true)
    @JsonProperty(value="PlaceOfBirth",required=true)
    private String PlaceOfBirth;
    
    @Column(name = "RaceID", nullable = true)
    @JsonProperty(value="RaceID",required=true)
    private Integer RaceID;
    
    @Column(name = "Religion", nullable = true)
    @JsonProperty(value="Religion",required=true)
    private String Religion;    

    @Column(name = "SocialTypeID", nullable = true)
    @JsonProperty(value="SocialTypeID",required=true)
    private Integer SocialTypeID;
    
    @Column(name = "TaxIDNumber", nullable = true)
    @JsonProperty(value="TaxIDNumber",required=true)
    private String TaxIDNumber;     
    
    @Column(name="CreatedAt",nullable = false)
    @CreationTimestamp
    private Date CreatedAt;

    @Column(name="UpdatedAt",nullable = false)
    @CreationTimestamp
    private Date UpdatedAt;    
    
}