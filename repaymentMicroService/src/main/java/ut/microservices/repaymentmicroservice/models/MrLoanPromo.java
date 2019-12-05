package ut.microservices.repaymentmicroservice.models;

import lombok.Data;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RPYMS_MrLoanPromo")
@Data
public class MrLoanPromo implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MlpID")
    private 	Integer	MlpID;
    
    @Column(name="MlpName", nullable=true)
    @JsonProperty(value="MlpName")
    private 	String	MlpName;
    
    @Column(name="MlpDesc")
    @JsonProperty(value="MlpDesc")
    private 	String	MlpDesc;
    
    @Column(name="MlpCode", nullable=true)
    @JsonProperty(value="MlpCode")
    private 	String	MlpCode;
    
    @Column(name="MlpMinValue", nullable=true)
    @JsonProperty(value="MlpMinValue")
    private 	Double	MlpMinValue;
    
    @Column(name="MlpMaxValue", nullable=true)
    @JsonProperty(value="MlpMaxValue")
    private 	Double	MlpMaxValue;
    
    @Column(name="MlpDefaultValue", nullable=true)
    @JsonProperty(value="MlpDefaultValue")
    private 	Double	MlpDefaultValue;
    
    @Column(name="MlpInterestRate", nullable=true)
    @JsonProperty(value="MlpInterestRate")
    private 	Double	MlpInterestRate;
    
    @Column(name="MlpMinTenor", nullable=true)
    @JsonProperty(value="MlpMinTenor")
    private 	Integer	MlpMinTenor;
    
    @Column(name="MlpMaxTenor", nullable=true)
    @JsonProperty(value="MlpMaxTenor")
    private 	Integer	MlpMaxTenor;
    
    @Column(name="MlpDefaultTenor", nullable=true)
    @JsonProperty(value="MlpDefaultTenor")
    private 	Integer	MlpDefaultTenor;
    
    @Column(name="MlpStartDate", nullable=true)
    @JsonProperty(value="MlpStartDate")
    private 	Date	MlpStartDate;
    
    @Column(name="MlpEndDate", nullable=true)
    @JsonProperty(value="MlpEndDate")
    private 	Date	MlpEndDate;
    
    @Column(name="MlpLoanGracePeriod", nullable=true)
    @JsonProperty(value="MlpLoanGracePeriod")
    private 	Integer	MlpLoanGracePeriod;
    
    @Column(name="MlpCreatBy", nullable=true)
    @JsonProperty(value="MlpCreatBy")
    private 	Integer	MlpCreatBy;
    
    @Column(name="MlpCreateDate", nullable=true)
    @JsonProperty(value="MlpCreateDate")
    private 	Date	MlpCreateDate;
    
    @Column(name="MlpNote")
    @JsonProperty(value="MlpNote")
    private 	String	MlpNote;
    
    @Column(name="MlpIsActive", nullable=true)
    @JsonProperty(value="MlpIsActive")
    private 	String	MlpIsActive;
    
    @Column(name="MlpIsRepeat")
    @JsonProperty(value="MlpIsRepeat")
    private 	String	MlpIsRepeat;
    
    @Column(name="MlpLoanNumber", nullable=true)
    @JsonProperty(value="MlpLoanNumber")
    private 	Integer	MlpLoanNumber;
    
    @Column(name="MlpBorrowerStart", nullable=true)
    @JsonProperty(value="MlpBorrowerStart")
    private 	Date	MlpBorrowerStart;
    
    @Column(name="MlpBorrowerEnd", nullable=true)
    @JsonProperty(value="MlpBorrowerEnd")
    private 	Date	MlpBorrowerEnd;

}
