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
@Table(name = "LAMS_ApplicationScheduleProcess")
@JsonIgnoreProperties(value = {"CreateAt"}, allowGetters = true)
@Data
public class ApplicationScheduleProcessModel implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ApplicationScheduleProcessID")
    private Integer ApplicationScheduleProcessID;

    @Column(name = "ApplicantID")
    private Integer ApplicantID;
    
    @Column(name = "LoanApplicationID")
    private String LoanApplicationID;
    
    @Column(name = "BuID")
    private String BuID;
    
    @Column(name = "ScheduleDate")
    private Date ScheduleDate;
    
    @Column(name = "ScheduleTimeStart")
    private String ScheduleTimeStart;
    
    @Column(name = "ScheduleTimeEnd")
    private String ScheduleTimeEnd;
    
    @Column(name = "Note")
    private String Note;
    
    @Column(name = "ApplicantChangeData")
    private String ApplicantChangeData;
    
    @Column(name = "ApplicantJsonData")
    private String ApplicantJsonData;
    
    @Column(name = "ChangeDataApproved")
    private String ChangeDataApproved;
    
    @Column(name = "ApplicationScheduleProcessStatus")
    private String ApplicationScheduleProcessStatus;
    
    @Column(name = "ClientID")
    private Integer ClientID;
    
    @Column(name = "Status")
    private String Status;
    
    @Column(name = "ReceiveScheduleDate")
    private Date ReceiveScheduleDate;
}