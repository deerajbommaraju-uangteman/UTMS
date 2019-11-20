package ut.microservices.loanapplicationmicroservice.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "LAMS_TempApplicantData")
@JsonIgnoreProperties(value = {"CreatedAt","UpdatedAt"}, allowGetters = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TempApplicantDataModel implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = -2096498813134825330L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonProperty(value="ID",required=true)
    private Integer	ID;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ApplicantID")
    private Integer ApplicantID;

    @Column(name = "BorrowerReference", nullable = true)
    private String BorrowerReference;
    
    @Column(name = "Fullname", nullable = true)
    @JsonProperty(value="Fullname",required=true)
    private String FullName;
    
    @Column(name = "Gender")
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
    
    @Column(name = "HouseStatus", nullable = true)
    @JsonProperty(value="HouseStatus",required=true)
    private String HouseStatus;

    @Column(name = "HouseStatus2", nullable = true)
    @JsonProperty(value="HouseStatus2",required=true)
    private String HouseStatus2;
    
    @Column(name = "TelephoneNumber", nullable = true)
    @JsonProperty(value="TelephoneNumber",required=true)
    private String TelephoneNumber;
    
    @Column(name = "HllKtp", nullable = true)
    private Integer HllKtp;
    
    @Column(name = "VillageOfResidence", nullable = true)
    @JsonProperty(value="VillageOfResidence",required=true)
    private String VillageOfResidence;

    @Column(name = "DistrictOfResidence", nullable = true)
    @JsonProperty(value="DistrictOfResidence",required=true)
    private String DistrictOfResidence;
    
    @Column(name = "CityOfResidence", nullable = true)
    @JsonProperty(value="CityOfResidence",required=true)
    private String CityOfResidence;
    
    @Column(name = "Province", nullable = true)
    @JsonProperty(value="Province",required=true)
    private String Province;
    
    @Column(name = "ZipCodeOfResidence", nullable = true)
    @JsonProperty(value="ZipCodeOfResidence",required=true)
    private String ZipCodeOfResidence;
    
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
    
    // @Column(name = "DomicileCountry", nullable = true)
    // private Integer DomicileCountry;
    
    @Column(name = "Family1Name", nullable = true)
    @JsonProperty(value="Family1Name",required=true)
    private String Family1Name;

    @Column(name = "Family1PhoneNumber", nullable = true)
    @JsonProperty(value="Family1PhoneNumber",required=true)
    private String Family1PhoneNumber;

    @Column(name = "Family1Address", nullable = true)
    @JsonProperty(value="Family1Address",required=true)
    private String Family1Address;

    @Column(name = "VillageOf1Family", nullable = true)
    @JsonProperty(value="VillageOf1Family",required=true)
    private String VillageOf1Family;

    @Column(name = "DistrictOf1Family", nullable = true)
    @JsonProperty(value="DistrictOf1Family",required=true)
    private String DistrictOf1Family;

    @Column(name = "CityOf1Family", nullable = true)
    @JsonProperty(value="CityOf1Family",required=true)
    private String CityOf1Family;

    @Column(name = "ProvinceOf1Family", nullable = true)
    @JsonProperty(value="ProvinceOf1Family",required=true)
    private String ProvinceOf1Family;
    
    @Column(name = "ZipCodeOf1Family", nullable = true)
    @JsonProperty(value="ZipCodeOf1Family",required=true)
    private String ZipCodeOf1Family;
    
    // @Column(name = "Family1Country", nullable = true)
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
    @JsonProperty(value="MonthlyIncome",required=true)
    private Double MonthlyIncome;
    
    @Column(name = "HllWork", nullable = true)
    private String HllWork;

    @Column(name = "BankNameID", nullable = true)
    @JsonProperty(value="BankNameID",required=true)
    private Integer BankNameID;
    
    @Column(name = "BankUsername", nullable = true)
    @JsonProperty(value="BankUserName",required=true)
    private String BankUsername;
    
    @Column(name = "BankAccountNumber", nullable = true)
    @JsonProperty(value="BankAccountNumber",required=true)
    private String BankAccountNumber;
    
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
    
    // @Column(name = "EmployerCountry", nullable = true)
    // private Integer EmployerCountry;
    
    @Column(name = "EmployerTelephone", nullable = true)
    @JsonProperty(value="EmployerTelephone",required=true)
    private String EmployerTelephone;

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
    
    @Column(name = "IsBankAccountCorrect", nullable = true)
    private String IsBankAccountCorrect;
    
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

    @Column(name = "FormID", nullable = true)
    @JsonProperty(value="FormID",required=true)
    private String FormID;

    public void setformData(TempApplicantDataModel formData){
        System.out.println(formData.getFormID().toString());
        switch (formData.getFormID().toString()) {

            case "form2":
                break;
            case "form3":
                break;    
            case "form4":
                this.setFullName(formData.getFullName());
                this.setPlaceOfBirth(formData.getPlaceOfBirth());
                this.setGender(formData.getGender());
                //this.setDateOfBirth(formData.getDateOfBirth());
                this.setReligion(formData.getReligion());
                this.setMaritalStatus(formData.getMaritalStatus());
                break;
            case "form5":
                this.setEducation(formData.getEducation());
                break;
            case "form6":
                this.setAddress(formData.getAddress());    
                this.setTelephoneNumber(formData.getTelephoneNumber());            
                this.setMrtwID(formData.getMrtwID());
                this.setProvince(formData.getProvince());
                this.setCityOfResidence(formData.getCityOfResidence());
                this.setDistrictOfResidence(formData.getDistrictOfResidence());
                this.setVillageOfResidence(formData.getVillageOfResidence());
                this.setZipCodeOfResidence(formData.getZipCodeOfResidence());
                this.setHouseStatus(formData.getHouseStatus());
                break;
            case "form7":
                this.setFamily1Name(formData.getFamily1Name());
                this.setFamily1PhoneNumber(formData.getFamily1PhoneNumber());
                this.setFamily1Address(formData.getFamily1Address());
                this.setProvinceOf1Family(formData.getProvinceOf1Family());
                this.setCityOf1Family(formData.getCityOf1Family());            
                this.setDistrictOf1Family(formData.getDistrictOf1Family());            
                this.setVillageOf1Family(formData.getVillageOf1Family());            
                this.setZipCodeOf1Family(formData.getZipCodeOf1Family());            
                break;
            case "form8":
                this.setBankUsername(formData.getBankUsername());
                this.setBankNameID(formData.getBankNameID());
                this.setBankAccountNumber(formData.getBankAccountNumber());
                break;
            case "form9":
                this.setEmployerName(formData.getEmployerName());
                this.setEmployerTelephone(formData.getEmployerTelephone());
                this.setEmployerAddress(formData.getEmployerAddress());
                this.setEmployerProvince(formData.getEmployerProvince());
                this.setEmployerCity(formData.getEmployerCity());
                this.setEmployerDistrict(formData.getEmployerDistrict());
                this.setEmployerVillage(formData.getEmployerVillage());
                this.setEmployerPostalCode(formData.getEmployerPostalCode());
                break;
        }
    }
}