package ut.microservices.investormicroservice.model;

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

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;


@Entity
@Table(name = "LAMS_ApplicantData")
@JsonIgnoreProperties(value = {"CreatedAt","UpdatedAt"}, allowGetters = true)
@Data
public class ApplicantData implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ApplicantID")
    private Integer ApplicantID;
    
    @Column(name = "ApplicantIDTemp", nullable = true)
    private Integer ApplicantIDTemp;
    
    @Column(name = "BorrowerReference", nullable = true)
    private String BorrowerReference;
    
    @Column(name = "Fullname", nullable = true)
    @JsonProperty(value="Fullname",required=true)
    private String FullName;
    
    @Column(name = "Gender", nullable = true)
    @JsonProperty(value="Gender",required=true)
    private String Gender;
    
    @Column(name = "EmailAddress", nullable = true)
    @JsonProperty(value="EmailAddress",required=true)
    private String EmailAddress;
    
    @Column(name = "SocialTypeID", nullable = true)
    private Integer SocialTypeID;
    
    
    @Column(name = "LinkedInID", nullable = true)
    private String LinkedInID;
    
    @Column(name = "FacebookID", nullable = true)
    private String FacebookID;
    
    @Column(name = "GooglePlusID", nullable = true)
    @JsonProperty(value="GooglePlusID",required=true)
    private String GooglePlusID;
    
    @Column(name = "PlaceOfBirth", nullable = true)
    @JsonProperty(value="PlaceOfBirth",required=true)
    private String PlaceOfBirth;
    
    @Column(name = "DateOfBirth", nullable = true)
    @JsonProperty(value="DateOfBirth",required=true)
    private Date DateOfBirth;
    
    @Column(name = "Age", nullable = true)
    private Integer Age;
    
    @Column(name = "Religion", nullable = true)
    @JsonProperty(value="Religion",required=true)
    private String Religion;
    
    @Column(name = "Education", nullable = true)
    @JsonProperty(value="Education",required=true)
    private Integer Education;
    
    @Column(name = "NationalStatus", nullable = true)
    private String NationalStatus;
    
    @Column(name = "RaceID", nullable = true)
    private Integer RaceID;
    
    @Column(name = "TaxIDNumber", nullable = true)
    private String TaxIDNumber;
    
    @Column(name = "PersonalIDNumber", nullable = true)
    @JsonProperty(value="PersonalIDNumber",required=true)
    private String PersonalIDNumber;
    
    @Column(name = "FamilyIDNumber", nullable = true)
    private String FamilyIDNumber;
    
    
    @Column(name = "MobilePrefix", nullable = true)
    private Integer MobilePrefix;

    @Column(name = "MobilePrefix2", nullable = true)
    private Integer MobilePrefix2;
    
    @Column(name = "MobileNumber", nullable = true)
    @JsonProperty(value="MobileNumber",required=true)
    private String MobileNumber;

    @Column(name = "MobileNumber2", nullable = true)
    private String MobileNumber2;
    
    @Column(name = "Address", nullable = true)
    @JsonProperty(value="Address",required=true)
    private String Address;
    
    @Column(name = "HomeStatus", nullable = true)
    @JsonProperty(value="HomeStatus",required=true)
    private String HomeStatus;

    @Column(name = "HomeStatus2", nullable = true)
    @JsonProperty(value="HomeStaus2",required=true)
    private String HomeStatus2;
    
    @Column(name = "TelephoneNumber", nullable = true)
    @JsonProperty(value="TelephoneNumber",required=true)
    private String TelephoneNumber;
    
    @Column(name = "HllKtp", nullable = true)
    private Integer HllKtp;
    
    @Column(name = "Village", nullable = true)
    @JsonProperty(value="Village",required=true)
    private String Village;

    @Column(name = "District", nullable = true)
    @JsonProperty(value="District",required=true)
    private String District;
    
    @Column(name = "City", nullable = true)
    @JsonProperty(value="City",required=true)
    private String City;
    
    @Column(name = "Province", nullable = true)
    @JsonProperty(value="Province",required=true)
    private String Province;
    
    @Column(name = "PostalCode", nullable = true)
    @JsonProperty(value="PostalCode",required=true)
    private String PostalCode;
    
    @Column(name = "DomicileAddress", nullable = true)
    private String DomicileAddress;
    
    @Column(name = "DomicileTelephone", nullable = true)
    private String DomicileTelephone;
    
    
    @Column(name = "HllDomicile", nullable = true)
    private String HllDomicile;
    
    @Column(name = "DomicileVillage", nullable = true)
    private String DomicileVillage;
    
    @Column(name = "DomicileDistrict", nullable = true)
    private String DomicileDistrict;
    
    @Column(name = "DomicileCity", nullable = true)
    private String DomicileCity;
    
    @Column(name = "DomicileProvince", nullable = true)
    private Date DomicileProvince;
    
    @Column(name = "DomicilePostalCode", nullable = true)
    private Integer DomicilePostalCode;
    
    // @Column(name = "DomicileCountry")
    // private Integer DomicileCountry;
    
    @Column(name = "Family1Name", nullable = true)
    @JsonProperty(value="Family1Name",required=true)
    private String Family1Name;

    @Column(name = "Family1Telephone", nullable = true)
    @JsonProperty(value="Family1TelephoneNumber",required=true)
    private String Family1Telephone;

    @Column(name = "Family1Address", nullable = true)
    @JsonProperty(value="Family1Address",required=true)
    private String Family1Address;

    @Column(name = "Family1Village", nullable = true)
    @JsonProperty(value="Family1Village",required=true)
    private String Family1Village;

    @Column(name = "Family1District", nullable = true)
    @JsonProperty(value="Family1District",required=true)
    private String Family1District;

    @Column(name = "Family1City", nullable = true)
    @JsonProperty(value="Family1City",required=true)
    private String Family1City;

    @Column(name = "Family1Province", nullable = true)
    @JsonProperty(value="Family1Province",required=true)
    private String Family1Province;
    
    @Column(name = "Family1PostalCode", nullable = true)
    @JsonProperty(value="Family1PostalCode",required=true)
    private String Family1PostalCode;
    
    // @Column(name = "Family1Country")
    // private Integer Family1Country;

    @Column(name = "Family2Name", nullable = true)
    private String Family2Name;

    @Column(name = "Family2Telephone", nullable = true)
    private String Family2Telephone;

    @Column(name = "Family2Address", nullable = true)
    private String Family2Address;

    @Column(name = "Family2Village", nullable = true)
    private String Family2Village;

    @Column(name = "Family2District", nullable = true)
    private String Family2District;

    @Column(name = "Family2City", nullable = true)
    private String Family2City;

    @Column(name = "Family2Province", nullable = true)
    private String Family2Province;
    
    @Column(name = "Family2PostalCode", nullable = true)
    private String Family2PostalCode;
    
    @Column(name = "MaritalStatus", nullable = true)
    @JsonProperty(value="MaritalStatus",required=true)
    private String MaritalStatus;
    
    @Column(name = "amountchild", nullable = true)
    private Integer AmountChild;
    
    @Column(name = "MonthlyIncome", nullable = true)
    private Double MonthlyIncome;
    
    @Column(name = "HllWork", nullable = true)
    private String HllWork;

    @Column(name = "BankNameID", nullable = true)
    @JsonProperty(value="BankNameID",required=true)
    private Integer BankNameID;
    
    @Column(name = "BankUsername", nullable = true)
    @JsonProperty(value="BankUserName",required=true)
    private String BankUsername;

    @Column(name = "BankNumber", nullable = true)
    @JsonProperty(value="BankNumber",required=true)
    private String BankNumber;
    
    @Column(name = "MrtwID", nullable = true)
    private Integer MrtwID;
    
    @Column(name = "EmployerType", nullable = true)
    @JsonProperty(value="EmployerType",required=true)
    private String EmployerType;

    @Column(name = "EmployerName", nullable = true)
    @JsonProperty(value="EmployerName",required=true)
    private String EmployerName;
    
    @Column(name = "EmployerIndustryID", nullable = true)
    @JsonProperty(value="EmployerIndustryID",required=true)
    private Integer EmployerIndustryID;
    
    @Column(name = "EmployerRole", nullable = true)
    @JsonProperty(value="EmployerRole",required=true)
    private String EmployerRole;
    
    @Column(name = "EmployerAddress", nullable = true)
    @JsonProperty(value="EmployerAddress",required=true)
    private String EmployerAddress;

    @Column(name = "EmployerVillage", nullable = true)
    @JsonProperty(value="EmployerVillage",required=true)
    private String EmployerVillage;
    
    
    @Column(name = "EmployerDistrict", nullable = true)
    @JsonProperty(value="EmployerDistrict",required=true)
    private String EmployerDistrict;

    @Column(name = "EmployerCity", nullable = true)
    @JsonProperty(value="EmployerCity",required=true)
    private String EmployerCity;
    
    @Column(name = "EmployerProvince", nullable = true)
    @JsonProperty(value="EmployerProvince",required=true)
    private String EmployerProvince;

    @Column(name = "EmployerPostalCode", nullable = true)
    @JsonProperty(value="EmployerPostalCode",required=true)
    private String EmployerPostalCode;
    
    // @Column(name = "EmployerCountry")
    // private Integer EmployerCountry;
    
    @Column(name = "WorkTelephone", nullable = true)
    @JsonProperty(value="WorkTelephone",required=true)
    private String WorkTelephone;

    @Column(name = "FromIPAddress", nullable = true)
    private String FromIPAddress;
    
    @Column(name = "StartCreatedAt", nullable = true)
    private Date StartCreatedAt;
    
    @Column(name = "SubmitAt", nullable = true)
    private Integer SubmitAt;
    
    @Column(name = "ParetoID", nullable = true)
    private Integer ParetoID;

    @Column(name = "RepeatedApplicant", nullable = true)
    private String RepeatedApplicant;
    
    @Column(name = "Latitude", nullable = true)
    private String Latitude;
    
    @Column(name = "Longitude", nullable = true)
    private String Longitude;
    
    @Column(name = "KnowUT", nullable = true)
    @JsonProperty(value="KnowUT",required=true)
    private Integer KnowUT;
    
    @Column(name = "Campaign", nullable = true)
    private String Campaign;
    
    // @Column(name = "Banned")
    // private String Banned;
    
    
    @Column(name = "BannedExpired", nullable = true)
    private Date BannedExpired;
    
    @Column(name = "IsBankAccountCorrect", nullable = true)
    private String IsBankAccountCorrect;
    
    @Column(name = "Organizer", nullable = true)
    private String Organizer;
    
    @Column(name = "LegalEntityDecree", nullable = true)
    private String LegalEntityDecree;
    
    @Column(name = "LegalEntityType", nullable = true)
    private String LegalEntityType;
    
    @Column(name = "LegalEntityName", nullable = true)
    private String LegalEntityName;
    
    @Column(name = "IDEmployerIndustry", nullable = true)
    private Integer IDEmployerIndustry;
    
    @Column(name = "MotherFullname", nullable = true)
    @JsonProperty(value="MotherFullName",required=true)
    private String MotherFullname;

    @Column(name = "AddressRT", nullable = true)
    @JsonProperty(value="AddressRT",required=true)
    private String AddressRT;

    @Column(name = "AddressRW", nullable = true)
    @JsonProperty(value="AddressRW",required=true)
    private String AddressRW;

    @Column(name = "SpouseName", nullable = true)
    private String SpouseName;

    @Column(name = "SpousePersonalID", nullable = true)
    private String SpousePersonalID;

    @Column(name = "SpouseFamilyID", nullable = true)
    private String SpouseFamilyID;

    @Column(name = "SpouseAddress", nullable = true)
    private String SpouseAddress;
    
    @Column(name = "SpouseAddressRT", nullable = true)
    private String SpouseAddressRT;
    
    @Column(name = "SpouseAddressRW", nullable = true)
    private String SpouseAddressRW;

    @Column(name = "SpouseVillage", nullable = true)
    private String SpouseVillage;

    @Column(name = "SpouseDistrict", nullable = true)
    private String SpouseDistrict;

    @Column(name = "SpouseCity", nullable = true)
    private String SpouseCity;

    @Column(name = "SpouseProvince", nullable = true)
    private String SpouseProvince;

    @Column(name = "SpousePostalCode", nullable = true)
    private String SpousePostalCode;

    @Column(name = "SpouseWorkName", nullable = true)
    private String SpouseWorkName;

    @Column(name = "SpouseWorkAddress", nullable = true)
    private String SpouseWorkAddress;
    
    @Column(name = "SpouseWorkTelephone", nullable = true)
    private String SpouseWorkTelephone;
    
    @Column(name = "SpouseWorkAddressRT", nullable = true)
    private String SpouseWorkAddressRT;
    
    @Column(name = "SpouseWorkAddressRW", nullable = true)
    private String SpouseWorkAddressRW;
    
    @Column(name = "SpouseWorkVillage", nullable = true)
    private String SpouseWorkVillage;
    
    @Column(name = "SpouseWorkDistrict", nullable = true)
    private String SpouseWorkDistrict;

    @Column(name = "SpouseWorkCity", nullable = true)
    private Integer SpouseWorkCity;
    
    @Column(name = "SpouseWorkProvince", nullable = true)
    private String SpouseWorkProvince;

    @Column(name = "SpouseWorkPostalCode", nullable = true)
    private String SpouseWorkPostalCode;
    
    @Column(name = "IsPartnerWorking", nullable = true)
    private Integer IsPartnerWorking;
    
    @Column(name = "SpouseMonthlyIncome", nullable = true)
    private Double SpouseMonthlyIncome;

    @Column(name="CreatedAt",nullable = false)
    @CreationTimestamp
    private Date CreatedAt;

    @Column(name="UpdatedAt",nullable = false)
    @CreationTimestamp
    private Date Update;
}
