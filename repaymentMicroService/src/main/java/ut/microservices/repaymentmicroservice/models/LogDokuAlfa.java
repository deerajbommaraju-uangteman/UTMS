package ut.microservices.repaymentmicroservice.models;

import lombok.Data;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RPYMS_LogDokualfa")
@JsonIgnoreProperties(value = {"CreatedAt"},allowGetters = true) 
@Data
public class LogDokuAlfa implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="DokualfaLogID")
    private 	Integer	DokualfaLogID;

    @Column(name="VaNumber", nullable=true)
    @JsonProperty(value="VaNumber")
    private 	String	VaNumber;

    @Column(name="LogAppID", nullable=true)
    @JsonProperty(value="LogAppID")
    private String	LogAppID;

    @Column(name="InquiryRequest", nullable=true)
    @JsonProperty(value="InquiryRequest")
    private String	InquiryRequest;

    @Column(name="InquiryResponse", nullable=true)
    @JsonProperty(value="InquiryResponse")
    private String	InquiryResponse;

    @Column(name="InquiryReqDatetime", nullable=true)
    @JsonProperty(value="InquiryReqDatetime")
    private Date InquiryReqDatetime;

    @Column(name="InquiryRespDatetime", nullable=true)
    @JsonProperty(value="InquiryRespDatetime")
    private Date InquiryRespDatetime;

    @Column(name="NotifyRequest", nullable=true)
    @JsonProperty(value="NotifyRequest")
    private String	NotifyRequest;

    @Column(name="NotifyResponse", nullable=true)
    @JsonProperty(value="NotifyResponse")
    private String	NotifyResponse;

    @Column(name="NotifyReqDatetime", nullable=true)
    @JsonProperty(value="NotifyReqDatetime")
    private Date	NotifyReqDatetime;

    @Column(name="NotifyRespDatetime", nullable=true)
    @JsonProperty(value="NotifyRespDatetime")
    private Date	NotifyRespDatetime;

    @Column(name="AttemptNo", nullable=true)
    @JsonProperty(value="AttemptNo")
    private Integer	AttemptNo;

    @Column(name="CreatedAt")
    @CreationTimestamp
    private Date CreatedAt;
    
}   
 