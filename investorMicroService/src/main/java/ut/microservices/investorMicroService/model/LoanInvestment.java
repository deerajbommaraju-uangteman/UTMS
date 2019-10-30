package ut.microservices.investorMicroService.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "INVMS_LoanInvestment")
@JsonIgnoreProperties(value = {"CreatedAt","UpdatedAt"}, allowGetters = true)
@Data
@DynamicUpdate
public class LoanInvestment implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long ID;

    @NotBlank
    @Column(name="LoanAppID")
    private String loanAppID;

   
    @Column(name="InvestorID")
    private Integer investorID;

    
    @Column(name="LoanAmount")
    private Double loanAmount;

    
    @Column(name="LoanTenor")
    private Integer LoanTenor;

    @NotBlank
    @Column(name="State")
    private String State;

   
    @Column(name="CreatedBy")
    private Integer createdBy;

   
    @Column(name="UpdatedBy")
    private Integer updatedBy;

    @Column(name="CreatedAt",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name="UpdatedAt",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

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