package ut.microservices.loanapplicationmicroservice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "LAMS_ApplicantEditRequest")
@JsonIgnoreProperties(value = {"CreateDate"}, allowGetters = true)
@Data
public class ApplicantDataEditRequestModel implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer ID;
    
    @Column(name = "ApplicantID")
    private Integer ApplicantID;
    
    @Column(name = "LoanApplicationID")
    private Integer LoanApplicationID;
    
    @Column(name = "BuID")
    private Integer BuID;
    
    @Column(name = "ApplicantJsonData")
    private String ApplicantJsonData;
    
    @Column(name = "ApprovalBuID")
    private Integer ApprovalBuID;
    
    @Column(name = "ChangeDataApproved")
    private String ChangeDataApproved;
    
    @Column(name = "CreatedDate")
    private Date CreatedDate;
    
    @Column(name = "UpdatedAt")
    private Date UpdatedAt;
    
}