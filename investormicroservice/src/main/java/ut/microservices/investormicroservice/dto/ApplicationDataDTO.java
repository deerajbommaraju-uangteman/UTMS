package ut.microservices.investormicroservice.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;

@Data
public class ApplicationDataDTO implements Serializable{

    @JsonProperty(value = "ApplicationID", required = false)
    private Integer ApplicationID;
    
    @JsonProperty(value = "ApplicationApplicantID", required = false)
    private Integer ApplicationApplicantID;

    @JsonProperty(value = "ApplictionAmID", required = false)
    private Integer ApplictionAmID;
    
    
    @JsonProperty(value="LoanApplicationID",required=false)
    private String LoanApplicationID;
    
    @JsonProperty(value="LoanAmount",required=false)
    private Double LoanAmount;
    
    @JsonProperty(value="LoanDaysLength",required=false)
    private Integer LoanDaysLength;
    
    @JsonProperty(value="LoanStartDateTime",required=false)
    private Date LoanStartDateTime;

    @JsonProperty(value="LoanDueDateTime",required=false)
    private Date LoanDueDateTime;
    
    @JsonProperty(value="LoanInterestRate",required=false)
    private Double LoanInterestRate;
    
    @JsonProperty(value="LoanInterestFee",required=false)
    private Double LoanInterestFee;
    
    @JsonProperty(value="LoanRepayAmount",required=false)
    private Double LoanRepayAmount;
    
    @JsonProperty(value="LoanPurpose",required=false)
    private String LoanPurpose;
    
    @JsonProperty(value="ApplicationAdrStatus",required=false)
    private String ApplicationAdrStatus;
    
    @JsonProperty(value = "Status", required = false)
    private String Status;
        
    
    @JsonProperty(value="ApplicationRepeated",required=false)
    private String ApplicationRepeated;
    
    
    @JsonProperty(value = "ApprovalStatus", required = false)
    private String ApprovalStatus;

    @JsonProperty(value = "IsInstallment", required = false)
    private String IsInstallment;

    @JsonProperty(value="TransactionID",required=false)
    private String TransactionID;
    
    @Column(name = "ApplicantID")
    @JsonProperty(value="ApplicantID",required=false)
    private Integer ApplicantID;
    
    @Column(name = "Age", nullable = false)
    @JsonProperty(value="Age",required=false)
    private Integer Age;
    
    @JsonProperty(value="DateOfBirth",required=false)
    private Date DateOfBirth;
    
    @JsonProperty(value="EmailAddress",required=false)
    private String EmailAddress;
    

    @JsonProperty(value="FamilyIDNumber",required=false)
    private String FamilyIDNumber;
    
    @JsonProperty(value="FullName",required=false)
    private String FullName;
    
    @JsonProperty(value="Gender",required=false)
    private String Gender;
    
    @JsonProperty(value="GooglePlusID",required=false)
    private String GooglePlusID;

    
    @JsonProperty(value="MobilePrefix",required=false)
    private Integer MobilePrefix;
    
    @JsonProperty(value="NationalStatus",required=false)
    private String NationalStatus;
    
    @JsonProperty(value="PersonalIDNumber",required=false)
    private String PersonalIDNumber;
    
    @JsonProperty(value="PlaceOfBirth",required=false)
    private String PlaceOfBirth;
    
    @JsonProperty(value="RaceID",required=false)
    private Integer RaceID;
    
    @JsonProperty(value="Religion",required=false)
    private String Religion;            
    
}