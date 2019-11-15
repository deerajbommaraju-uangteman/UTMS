package ut.microservices.repaymentmicroservice.models;

import lombok.Data;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@Entity
@Table(name = "RPYMS_DebugResult")
@JsonIgnoreProperties(value = {"CreatedAt"},allowGetters = true) 
@Data
public class DebugResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer ID;

    @Column(name="MethodName", nullable=true)
    @JsonProperty(value="MethodName")
    private String MethodName;
    
    @Column(name="LogDescription", nullable=true)
    @JsonProperty(value="LogDescription")
    private String LogDescription;

    @Column(name="CreatedAt")
    private Date CreatedAt;
    
}