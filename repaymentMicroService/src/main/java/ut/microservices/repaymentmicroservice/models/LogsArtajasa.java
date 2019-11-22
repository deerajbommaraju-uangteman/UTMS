package ut.microservices.repaymentmicroservice.models;

import lombok.Data;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RPYMS_LogsArtajasa")
@JsonIgnoreProperties(value = {"CreatedAt"},allowGetters = true) 
@Data
public class LogsArtajasa implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ArtajasaLogID")
    private 	Integer	ArtajasaLogID;

    @Column(name="VaNumber", nullable=true)
    @JsonProperty(value="VaNumber")
    private 	String	VaNumber;

    @Column(name="LogAppID", nullable=true)
    @JsonProperty(value="LogAppID")
    private 	String	LogAppID;

    @Column(name="VaRequest", nullable=true)
    @JsonProperty(value="VaRequest")
    private 	String	VaRequest;

    @Column(name="VaResponse", nullable=true)
    @JsonProperty(value="VaResponse")
    private 	String	VaResponse;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="VaReqDatetime", nullable=true)
    @JsonProperty(value="VaReqDatetime")
    private 	Date	VaReqDatetime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="VaRespDatetime", nullable=true)
    @JsonProperty(value="VaRespDatetime")
    private 	Date	VaRespDatetime;

    @Column(name="NotifyRequest", nullable=true)
    @JsonProperty(value="NotifyRequest")
    private 	String	NotifyRequest;

    @Column(name="NotifyResponse", nullable=true)
    @JsonProperty(value="NotifyResponse")
    private 	String	NotifyResponse;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="NotifyReqDatetime", nullable=true)
    @JsonProperty(value="NotifyReqDatetime")
    private 	Date	NotifyReqDatetime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="NotifyRespDatetime", nullable=true)
    @JsonProperty(value="NotifyRespDatetime")
    private 	Date	NotifyRespDatetime;

    @Column(name="AttemptNumber", nullable=true)
    @JsonProperty(value="AttemptNumber")
    private 	Integer	AttemptNumber;

    @Column(name="CreatedAt",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private 	Date	CreatedAt;

}    
    