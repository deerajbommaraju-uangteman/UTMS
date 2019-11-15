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
 * ApplicationNotesModel
 */
@Entity
@Table(name = "LAMS_ApplicationNotes")
@JsonIgnoreProperties(value = {"InputDate","UpdateDate"}, allowGetters = true)
@Data
public class ApplicationNotesModel implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ApplicationNotesID")
    private Integer	ApplicationNotesID;

    @Column(name = "ApplicationID", nullable = true)
    @JsonProperty(value="ApplicationID",required=true)
    private Integer	ApplicationID;
    
    @Column(name = "TypeNote", nullable = true)
    @JsonProperty(value="TypeNote")
    private String	TypeNote;
    
    @Column(name = "Note", nullable = true)
    @JsonProperty(value="Note")
    private String	Note;
    
    @Column(name = "InputDate", nullable = true)
    private Date	InputDate;
    
    @Column(name = "UpdateDate", nullable = true)
    private Date	UpdateDate;
    
    @Column(name = "InputBy", nullable = true)
    @JsonProperty(value="InputBy")
    private String	InputBy;
    
    @Column(name = "UpdateBy", nullable = true)
    @JsonProperty(value="UpdateBy")
    private String	UpdateBy;

}