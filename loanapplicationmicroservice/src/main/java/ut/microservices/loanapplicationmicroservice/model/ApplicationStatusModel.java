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

/**
 * ApplicationStatusModel
 */
@Entity
@Table(name = "LAMS_ApplicationStatus")
@JsonIgnoreProperties(value = {"CreateAt"}, allowGetters = true)
@Data
public class ApplicationStatusModel implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "StatusID")
    private Integer StatusID;

    @Column(name = "BoID")
    private Integer BoID;

    @Column(name = "ApplicationID")
    private Integer ApplicationID;
    
    @Column(name = "LoanApplicationID")
    private String LoanApplicationID;
    
    @Column(name = "PreviousCodeID")
    private Integer PreviousCodeID;
    
    @Column(name = "PreviousApplicationStatus")
    private String PreviousApplicationStatus;
    
    @Column(name = "CodeID")
    private Integer CodeID;
    
    @Column(name = "ApplicationStatus")
    private String ApplicationStatus;
    
    @Column(name = "CreateAt")
    private Date CreateAt;

    public void setApplicationStatusData(Integer ApplicationID, String LoanApplicationID, Integer BoID, Integer PreviousCodeID,  
    String PreviousApplicationStatus, Integer CodeID, String ApplicationStatus){
        this.ApplicationID=ApplicationID;
        this.ApplicationStatus=ApplicationStatus;
        this.BoID=BoID;
        this.CodeID=CodeID;
        this.LoanApplicationID=LoanApplicationID;
        this.PreviousApplicationStatus=PreviousApplicationStatus;
        this.PreviousCodeID=PreviousCodeID;
    }
}