package ut.microservices.reconcilems.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "RCNMS_MrVendorFee")
// @NamedQuery(name="RCNMS_RekapPayment.findAll", query="SELECT b FROM RCNMS_RekapPayment b")
@JsonIgnoreProperties(value = {"CreatedAt","UpdatedAt"}, allowGetters = true)
@Data
public class MrVendorFee implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private 	Integer	ID;

    @Column(name = "VendorName",nullable = false)
    @JsonProperty(value="VendorName")
    private 	String	VendorName; 

    @Column(name="VendorFee",nullable = true)
    @JsonProperty(value="VendorFee")
    private 	Double	VendorFee;

    @Column(name="VendorKey",nullable = true)
    private 	String	VendorKey;

    @Column(name = "AdminFee",nullable = true)
    private 	Double	AdminFee;

    @Column(name="PaymentFee",nullable = true)
    @JsonProperty(value="IDOrder")
    private 	Double	PaymentFee;     

    @Column(name="IsActive",nullable = false)
    private 	String	IsActive;   

    @Column(name="ModifiedBy",nullable = true)
    private Integer ModifiedBy;

    @Column(name="CreatedBy",nullable = true)
    private Integer CreatedBy;

    @Column(name="CreatedDate",nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedDate;

    @Column(name="ModifiedDate",nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ModifiedDate;

    @PrePersist
    protected void onCreated() {
        CreatedDate = new Date() ;
        ModifiedDate = new Date() ;
    }
}