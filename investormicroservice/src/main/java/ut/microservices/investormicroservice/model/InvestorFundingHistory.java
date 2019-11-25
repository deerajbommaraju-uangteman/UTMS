package ut.microservices.investormicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "INVMS_InvestorFundingHistory")
@JsonIgnoreProperties(value = {"CreatedAt","UpdatedAt"}, allowGetters = true)
@Data
public class InvestorFundingHistory implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long ID;

   
    @Column(name="InvestorID")
    private Integer investorID;
    
    @NotBlank
    @Column(name="InvestorVaNumber")
    private String investorVaNumber;

    @NotBlank
    @Column(name="FundTxnNumber")
    private String fundTxnNumber;

    
    @Column(name="TxnStatus")
    private Integer txnStatus;

    
    @Column(name="InvestmentLogID")
    private Integer investmentLogID;

    @Column(name="CreatedAt",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;



    @Column(name="UpdatedAt",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    protected void onCreated() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdated() {
        updatedAt = new Date();
    }
}