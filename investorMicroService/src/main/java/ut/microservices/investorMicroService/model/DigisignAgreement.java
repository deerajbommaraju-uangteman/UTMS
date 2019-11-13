package ut.microservices.investormicroservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "INVMS_DigisignAgreement")
@NamedQuery(name="DigisignAgreement.findAll", query="SELECT b FROM DigisignAgreement b")
@Data
public class DigisignAgreement implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long ID ;

    @Column(name="DocumentID")
    private String documentID ;

    @Column(name="ApplicantID")
    private Long applicantID;

    @Column(name="ApplicationID")
    private Integer applicationID;

    @Column(name="StatusAgreement")
    private String statusAgreement;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UserSignedAt", nullable = true)
    private Date userSignedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ExpiredAt")
    private Date expiredAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CreatedAt")
    private Date createdAt;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UpdatedAt")
    private Date updatedAt;


    @Column(name="ServerFilePath")
    private String serverFilePath;

    @Column(name="DocumentLenderID")
    @JsonProperty(value = "DocumentLenderID" )
    private String documentLenderID ;


    @Column(name="ServerLenderFilePath")
    private String serverLenderFilePath;


    @Column(name="InvestorID")
    private Integer investorID;

    @Column(name="DuLenderEmailUser")
    private String duLenderEmailUser;


    @Column(name="StatusLenderAgreement")
    private String statusLenderAgreement;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LenderSignedAt", nullable = true)
    private Date lenderSignedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UtSignedAt", nullable = true)
    private Date utSignedAt;

    @Column(name="LenderFailureReason")
    private String lenderFailureReason;

    @Column(name="FailureReason")
    private String failureReason;

    @PrePersist
    protected void onCreated() {
        createdAt = new Date() ;
        updatedAt = new Date() ;
    }

    @PreUpdate
    protected void onUpdated() {
        updatedAt = new Date() ;
    }
}
