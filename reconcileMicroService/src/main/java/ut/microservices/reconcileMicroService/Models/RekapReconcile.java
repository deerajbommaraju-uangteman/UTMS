package ut.microservices.reconcileMicroService.Models;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "RCNMS_RekapReconcile")
// @NamedQuery(name="RCNMS_RekapReconcile.findAll", query="SELECT b FROM RCNMS_RekapReconcile b")
@JsonIgnoreProperties(value = {"CreatedAt","UpdatedAt"}, allowGetters = true)
@Data
public class RekapReconcile implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private 	Integer	ID;


    @Column(name="Description",nullable = true)
    @JsonProperty(value="Description")
	public 	String	Description;

    @Column(name="Credit",nullable = true)
    @JsonProperty(value="Credit")
	public 	Double	Credit;

    @Column(name = "ReceiptDate",nullable = true)
    @JsonProperty(value="ReceiptDate")
    @DateTimeFormat
	public 	Date	ReceiptDate;

    @Column(name = "SettlementID",nullable = true)
    @JsonProperty(value="SettlementID")
	public 	String	SettlementID;  

    @Column(name="IsReconcile",nullable = true)
    private 	String	IsReconcile;     

    @Column(name = "Note",nullable = true)
    private 	String	Note;
 


    @Column(name="CreatedAt",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedAt;

    @Column(name="UpdatedAt",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date UpdatedAt;

    @PrePersist
    protected void onCreated() {
        CreatedAt = new Date() ;
        UpdatedAt = new Date() ;
    }

}