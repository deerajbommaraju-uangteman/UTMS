// package ut.microservices.loanapplicationmicroservice.model;

// import java.io.Serializable;
// import java.util.Date;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Table;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.fasterxml.jackson.annotation.JsonProperty;

// import lombok.Data;

// /**
//  * MrBanksList
//  */
// @Entity
// @Table(name = "Master_")
// @JsonIgnoreProperties(value = {""}, allowGetters = true)
// @Data
// public class Mr implements Serializable{

//     private static final long serialVersionUID = 1L;

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     @Column(name = "MasterID")
//     private Integer	MasterID;
    
//     @Column(name = "", nullable = true)
// }