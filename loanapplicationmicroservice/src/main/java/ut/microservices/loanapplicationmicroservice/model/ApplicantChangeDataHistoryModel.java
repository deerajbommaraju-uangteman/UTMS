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
@Table(name = "LAMS_ApplicantChangeDataHistory")
@JsonIgnoreProperties(value = {"CreateDate"}, allowGetters = true)
@Data
public class ApplicantChangeDataHistoryModel implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ApplicantChangeDataHistoryID")
    private Integer ApplicantChangeDataHistoryID;

    @Column(name = "ApplicantID")
    private Integer ApplicantID;

    @Column(name = "LoanApplicationID")
    private String LoanApplicationID;

    @Column(name = "BuID")
    private Integer BuID;

    @Column(name = "ChangeApplicantDataJson")
    private String ChangeApplicantDataJson;
    
    @Column(name = "CreateDate")
    private Date CreateDate;
}