package ut.microservices.investormicroservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Entity
@Table(name = "INVMS_MrBankList")
@JsonIgnoreProperties(value = {"CreatedAt","UpdatedAt"}, allowGetters = true)
@Data
public class MrBankList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private	Integer	ID;

    @Column(name = "Sort", nullable = true)
    private	Integer	sort;

    @Column(name = "Name", nullable = true)
    private	String	name;

    @Column(name = "PermataName", nullable = true)
    private	String	permataName;

    @Column(name = "Code", nullable = true)
    private	String	code;

    @Column(name = "XenditName", nullable = true)
    private	String	xenditName;

    @Column(name = "PermataClearingCode", nullable = true)
    private	String	permataClearingCode;

    @Column(name = "Swift", nullable = true)
    private	String	swift;

    @Column(name = "Status", nullable = true)
    private	String	status;
}
