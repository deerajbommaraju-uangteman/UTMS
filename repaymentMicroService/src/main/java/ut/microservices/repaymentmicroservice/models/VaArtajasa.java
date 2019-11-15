package ut.microservices.repaymentmicroservice.models;

import lombok.Data;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RPYMS_VaArtajasa")
@JsonIgnoreProperties(value = {"CreatedAt"},allowGetters = true) 
@Data
public class VaArtajasa implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer	ID;

    @Column(name="CvhID")
    @JsonProperty(value="CvhID")
    private 	Integer	CvhID;

    @Column(name="BackupBookingID", nullable=true)
    @JsonProperty(value="BackupBookingID")
    private 	String	BackupBookingID;

    @Column(name="BookingID", nullable=true)
    @JsonProperty(value="BookingID")
    private 	String	BookingID;

    @Column(name="Signature", nullable=true)
    @JsonProperty(value="Signature")
    private 	String	Signature;

    @Column(name="CreatedAt")
    @CreationTimestamp
    private Date CreatedAt;
    
}