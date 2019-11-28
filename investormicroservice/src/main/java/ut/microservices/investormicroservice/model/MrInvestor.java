package ut.microservices.investormicroservice.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "INVMS_MrInvestor")
@JsonIgnoreProperties(value = {"CreatedAt","UpdatedAt"}, allowGetters = true)
@Data
public class MrInvestor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private 	Integer	ID;

    @Column(name = "Name", nullable = true)
    private 	String	name;

    @Column(name = "Type", nullable = true)
    private 	String	ytpe;

    @Column(name = "Level", nullable = true)
    private 	Integer	level;

    @Column(name = "AccountNumber", nullable = true)
    private 	String	accountNumber;

    @Column(name = "InvestmentAmount", nullable = true)
    private 	Double	investmentAmount;

    @Column(name = "InvestmentRate", nullable = true)
    private 	Double	investmentRate;

    @Column(name = "Outstanding", nullable = true)
    private 	Double	outstanding;

    @Column(name = "PaymentAccountNumber", nullable = true)
    private 	String	paymentAccountNumber;

    @Column(name = "MbID", nullable = true)
    private 	Integer	mbID;

    @Column(name = "BeneficaryName", nullable = true)
    private 	String	beneficaryName;

    @Column(name = "State", nullable = true)
    private 	String	state;

    @Column(name = "Email", nullable = true)
    private 	String	email;

    @Column(name = "Username", nullable = true)
    private 	String	username;

    @Column(name = "Password", nullable = true)
    private 	String	password;

    @Column(name = "InvestorMobileNumber", nullable = true)
    private 	String	investorMobileNumber;

    @Column(name = "AgreementDoc", nullable = true)
    private 	String	agreementDoc;

    @Column(name = "Entity", nullable = true)
    private 	String	entity;

    @Column(name = "PowerOfAttorney", nullable = true)
    private 	String	powerOfAttorney;

    @Column(name = "CreatedAt", nullable = true)
    private 	Date	createdAt;

    @Column(name = "CreatedBy", nullable = true)
    private 	Integer	createdBy;

    @Column(name = "UpdatedAt", nullable = true)
    private 	Date	updatedAt;

    @Column(name = "UpdatedBy", nullable = true)
    private 	Integer	updatedBy;

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