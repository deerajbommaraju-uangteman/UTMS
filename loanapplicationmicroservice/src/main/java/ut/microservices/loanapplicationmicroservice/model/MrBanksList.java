package ut.microservices.loanapplicationmicroservice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * MrBanksList
 */
@Entity
@Table(name = "Master_BanksList")
//@JsonIgnoreProperties(value = {"InputDate","UpdateDate"}, allowGetters = true)
@Data
public class MrBanksList implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MasterID")
    private Integer	MasterID;

    @Column(name = "BankSort", nullable = true)
    //@JsonProperty(value="BankSort",required=true)
    private Integer	BankSort;
    
    @Column(name = "MrBankName", nullable = true)
    private String	MrBankName;
    
    @Column(name = "MrBankPermataName", nullable = true)
    private String	MrBankPermataName;
    
    @Column(name = "MrBankCode", nullable = true)
    private String	MrBankCode;
    
    @Column(name = "MrBankXenditName", nullable = true)
    private String	MrBankXenditName;
    
    @Column(name = "MrBankPermataClearingCode", nullable = true)
    private String	MrBankPermataClearingCode;
    
    @Column(name = "MrBankSwift", nullable = true)
    private String	MrBankSwift;
    
    @Column(name = "MrBankStatus", nullable = true)
    private String MrBankStatus;
    
}