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
@Table(name = "Master_MobilePrefix")
@JsonIgnoreProperties(value = {""}, allowGetters = true)
@Data
public class MrMobilePrefix implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer	ID;
    
    @Column(name = "MPCode", nullable = true)
    private	String	MPCode;
    
    @Column(name = "MPCountryName", nullable = true)
    private	String	MPCountryName;
    
    @Column(name = "MPCountryFlag", nullable = true)
    private	String	MPCountryFlag;
    
    @Column(name = "MPStatus", nullable = true)
    private	String	MPStatus;
}