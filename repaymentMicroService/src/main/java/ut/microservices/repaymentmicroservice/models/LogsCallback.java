package ut.microservices.repaymentmicroservice.models;

import lombok.Data;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RPYMS_LogsCallback")
@Data
public class LogsCallback implements Serializable{

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer ID;

    @Column(name="Vendor")
    @JsonProperty(value="Vendor")
    private String Vendor;

    @Column(name="FromIP")
    @JsonProperty(value="FromIP")
    private String FromIP;

    @Column(name="OnDate", nullable=true)
    private Date OnDate;

    @Column(name="Content")
    @JsonProperty(value="Content")
    private String Content;

    @Column(name="IsValid", nullable=true)
    @JsonProperty(value="IsValid")
    private Integer IsValid;

    @Column(name="Description", nullable=true)
    @JsonProperty(value="Description")
    private String Description;
}
