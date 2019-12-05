package ut.microservices.repaymentmicroservice.models;

import lombok.Data;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RPYMS_MrLoanScheme")
@Data
public class MrLoanScheme implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MlsID")
    private Integer	MlsID;

    @Column(name="MlsName", nullable=true)
    @JsonProperty(value="MlsName")
    private String	MlsName;
    
    @Column(name="MlsDesc", nullable=true)
    @JsonProperty(value="MlsDesc")
    private 	String	MlsDesc;
    
    @Column(name="MlsLoanIncrement", nullable=true)
    @JsonProperty(value="MlsLoanIncrement")
    private 	Integer	MlsLoanIncrement;
    
    @Column(name="MlsMinValue", nullable=true)
    @JsonProperty(value="MlsMinValue")
    private 	Double	MlsMinValue;
    
    @Column(name="MlsMaxValue", nullable=true)
    @JsonProperty(value="MlsMaxValue")
    private 	Double	MlsMaxValue;
    
    @Column(name="MlsDefaultValue", nullable=true)
    @JsonProperty(value="MlsDefaultValue")
    private 	Double	MlsDefaultValue;
    
    @Column(name="MlsInterestRate", nullable=true)
    @JsonProperty(value="MlsInterestRate")
    private 	Double	MlsInterestRate;
    
    @Column(name="MlsMinTenor", nullable=true)
    @JsonProperty(value="MlsMinTenor")
    private 	Integer	MlsMinTenor;
    
    @Column(name="MlsMaxTenor", nullable=true)
    @JsonProperty(value="MlsMaxTenor")
    private 	Integer	MlsMaxTenor;
    
    @Column(name="MlsDefaultTenor", nullable=true)
    @JsonProperty(value="MlsDefaultTenor")
    private 	Integer	MlsDefaultTenor;
    
    @Column(name="MlsStartDate", nullable=true)
    @JsonProperty(value="MlsStartDate")
    private 	Date	MlsStartDate;
    
    @Column(name="MlsEndDate", nullable=true)
    @JsonProperty(value="MlsEndDate")
    private 	Date	MlsEndDate;
    
    @Column(name="MlsLoanGracePeriod", nullable=true)
    @JsonProperty(value="MlsLoanGracePeriod")
    private 	Integer	MlsLoanGracePeriod;
    
    @Column(name="MlsCreateBy", nullable=true)
    @JsonProperty(value="MlsCreateBy")
    private 	Integer	MlsCreateBy;
    
    @Column(name="MlsCreateDate", nullable=true)
    @JsonProperty(value="MlsCreateDate")
    private 	Date	MlsCreateDate;
    
    @Column(name="MlsIsActive", nullable=true)
    @JsonProperty(value="MlsIsActive")
    private 	String	MlsIsActive;
    
    @Column(name="MlsNewInterestRate", nullable=true)
    @JsonProperty(value="MlsNewInterestRate")
    private 	Double	MlsNewInterestRate;

}
