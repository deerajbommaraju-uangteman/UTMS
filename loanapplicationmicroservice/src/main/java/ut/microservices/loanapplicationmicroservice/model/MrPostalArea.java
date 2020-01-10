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
@Table(name = "Master_PostalArea")
@JsonIgnoreProperties(value = {""}, allowGetters = true)
@Data
public class MrPostalArea implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer	ID;
    
    @Column(name = "PACode", nullable = true)
    private	String	PACode;
    
    @Column(name = "PAKelurahan", nullable = true)
    private	String	PAKelurahan;
    
    @Column(name = "PAKecamatan", nullable = true)
    private	String	PAKecamatan;
    
    @Column(name = "PAKabKotType", nullable = true)
    private	String	PAKabKotType;
    
    @Column(name = "PAKabKot", nullable = true)
    private	String	PAKabKot;
    
    @Column(name = "PAProvince", nullable = true)
    private	String	PAProvince;
    
    @Column(name = "PAStatus", nullable = true)
    private	String	PAStatus;
}