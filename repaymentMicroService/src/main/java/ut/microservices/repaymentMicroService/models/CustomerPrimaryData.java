
package ut.microservices.repaymentMicroService.models;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RPYMS_CustomerPrimaryData")
@JsonIgnoreProperties(value = {"CreatedAt"},allowGetters = true) 
// @NamedQuery(name="CustomerLoanRepayment.findAll", query="SELECT b FROM CustomerLoanRepayment b")
@Data
public class CustomerPrimaryData implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private 	Integer	ID;

    @Column(name="ApplicantID", nullable = true)
    @JsonProperty(value="ApplicantID")
    private 	Integer	ApplicantID;

    @Column(name="BorrowerID", nullable = true)
    @JsonProperty(value="BorrowerID")
    private 	String	BorrowerID;
    
    @Column(name="BorrowerReference", nullable = true)
    @JsonProperty(value="BorrowerReference")
    private 	String	BorrowerReference;
    
    @Column(name="Withdraw", nullable = true)
    @JsonProperty(value="Withdraw")
    private 	String	Withdraw;
    
    @Column(name="WithdrawPaid", nullable = true)
    @JsonProperty(value="WithdrawPaid")
    private 	String	WithdrawPaid;
    
    @Column(name="WithdrawAmount", nullable = true)
    @JsonProperty(value="WithdrawAmount")
    private 	Double	WithdrawAmount;
    
    @Column(name="FullName", nullable = true)
    @JsonProperty(value="FullName")
    private 	String	FullName;
    
    @Column(name="Gender", nullable = true)
    @JsonProperty(value="Gender")
    private 	String	Gender;
    
    @Column(name="EmailAddress", nullable = true)
    @JsonProperty(value="EmailAddress")
    private 	String	EmailAddress;
    
    @Column(name="", nullable = true)
    @JsonProperty(value="Password")
    private 	String	Password;
    
    @Column(name="SocialTypeID", nullable = true)
    private 	Integer	SocialTypeID;
    
    @Column(name="LinkedinID", nullable = true)
    @JsonProperty(value="LinkedinID")
    private 	String	LinkedinID;
    
    @Column(name="FacebookID", nullable = true)
    @JsonProperty(value="FacebookID")
    private 	String	FacebookID;
    
    @Column(name="PlaceOfBirth", nullable = true)
    @JsonProperty(value="PlaceOfBirth")
    private 	String	PlaceOfBirth;
    
    @Column(name="DateOfBirth", nullable = true)
    @JsonProperty(value="DateOfBirth")
    private 	Date	DateOfBirth;
    
    @Column(name="Age", nullable = true)
    @JsonProperty(value="Age")
    private 	Integer	Age;
    
    @Column(name="Religion", nullable = true)
    @JsonProperty(value="Religion")
    private 	String	Religion;
    
    @Column(name="Education", nullable = true)
    @JsonProperty(value="Education")
    private 	Integer	Education;
    
    @Column(name="NationalStatus", nullable = true)
    @JsonProperty(value="NationalStatus")
    private 	String	NationalStatus;
    
    @Column(name="RaceID", nullable = true)
    @JsonProperty(value="RaceID")
    private 	Integer	RaceID;
    
    @Column(name="TaxIDNumber", nullable = true)
    @JsonProperty(value="TaxIDNumber")
    private 	String	TaxIDNumber;
    
    @Column(name="PersonalIDNumber", nullable = true)
    @JsonProperty(value="PersonalIDNumber")
    private 	String	PersonalIDNumber;
    
    @Column(name="", nullable = true)
    @JsonProperty(value="FamilyIDNumber")
    private 	String	FamilyIDNumber;
    
    @Column(name="MobilePrefix", nullable = true)
    @JsonProperty(value="MobilePrefix")
    private 	String	MobilePrefix;
    
    @Column(name="MobilePrefix2", nullable = true)
    @JsonProperty(value="MobilePrefix2")
    private 	String	MobilePrefix2;
    
    @Column(name="MobileNumber", nullable = true)
    @JsonProperty(value="MobileNumber")
    private 	String	MobileNumber;
    
    @Column(name="MobileNo2", nullable = true)
    @JsonProperty(value="MobileNo2")
    private 	String	MobileNo2;
    
    @Column(name="Address", nullable = true)
    @JsonProperty(value="Address")
    private 	String	Address;
    
    @Column(name="HomeStatus", nullable = true)
    @JsonProperty(value="HomeStatus")
    private 	String	HomeStatus;
    
    @Column(name="HomeStatus2", nullable = true)
    @JsonProperty(value="HomeStatus2")
    private 	String	HomeStatus2;
    
    @Column(name="TelephoneNumber", nullable = true)
    @JsonProperty(value="TelephoneNumber")
    private 	String	TelephoneNumber;
    
    @Column(name="HllKtp", nullable = true)
    @JsonProperty(value="HllKtp")
    private 	String	HllKtp;
    
    @Column(name="Village", nullable = true)
    @JsonProperty(value="Village")
    private 	String	Village;
    
    @Column(name="District", nullable = true)
    @JsonProperty(value="District")
    private 	String	District;
    
    @Column(name="City", nullable = true)
    @JsonProperty(value="")
    private 	String	City;
    
    @Column(name="Province", nullable = true)
    @JsonProperty(value="Province")
    private 	String	Province;
    
    @Column(name="PostalCode", nullable = true)
    @JsonProperty(value="PostalCode")
    private 	String	PostalCode;
    
    @Column(name="DomicileAddress", nullable = true)
    @JsonProperty(value="DomicileAddress")
    private 	String	DomicileAddress;
    
    @Column(name="TelephoneDomicile", nullable = true)
    @JsonProperty(value="TelephoneDomicile")
    private 	String	TelephoneDomicile;
    
    @Column(name="HllDomicile", nullable = true)
    @JsonProperty(value="HllDomicile")
    private 	String	HllDomicile;
    
    @Column(name="DomicileVillage", nullable = true)
    @JsonProperty(value="DomicileVillage")
    private 	String	DomicileVillage;
    
    @Column(name="DomicileDistrict", nullable = true)
    @JsonProperty(value="DomicileDistrict")
    private 	String	DomicileDistrict;
    
    @Column(name="DomicileCity", nullable = true)
    @JsonProperty(value="DomicileCity")
    private 	String	DomicileCity;
    
    @Column(name="DomicileProvince", nullable = true)
    @JsonProperty(value="DomicileProvince")
    private 	String	DomicileProvince;
    
    @Column(name="DomicilePostalCode", nullable = true)
    @JsonProperty(value="DomicilePostalCode")
    private 	String	DomicilePostalCode;
    
    @Column(name="DomicileCountry")
    @JsonProperty(value="DomicileCountry")
    private 	Integer	DomicileCountry;
    
    @Column(name="Family1Name", nullable = true)
    @JsonProperty(value="Family1Name")
    private 	String	Family1Name;
    
    @Column(name="TelephoneFamily1", nullable = true)
    @JsonProperty(value="TelephoneFamily1")
    private 	String	TelephoneFamily1;
    
    @Column(name="Family1Address", nullable = true)
    @JsonProperty(value="Family1Address")
    private 	String	Family1Address;
    
    @Column(name="Family1Village", nullable = true)
    @JsonProperty(value="Family1Village")
    private 	String	Family1Village;
    
    @Column(name="Family1District", nullable = true)
    @JsonProperty(value="Family1District")
    private 	String	Family1District;
    
    @Column(name="Family1City", nullable = true)
    @JsonProperty(value="Family1City")
    private 	String	Family1City;
    
    @Column(name="Family1Province", nullable = true)
    @JsonProperty(value="Family1Province")
    private 	String	Family1Province;
    
    @Column(name="Family1PostalCode", nullable = true)
    @JsonProperty(value="Family1PostalCode")
    private 	String	Family1PostalCode;
    
    @Column(name="Family1Country")
    @JsonProperty(value="Family1Country")
    private 	Integer	Family1Country;
    
    @Column(name="Family2Name", nullable = true)
    @JsonProperty(value="Family2Name")
    private 	String	Family2Name;
    
    @Column(name="TelephoneFamily2", nullable = true)
    @JsonProperty(value="TelephoneFamily2")
    private 	String	TelephoneFamily2;
    
    @Column(name="Family2Address", nullable = true)
    @JsonProperty(value="Family2Address")
    private 	String	Family2Address;
    
    @Column(name="Family2Village", nullable = true)
    @JsonProperty(value="Family2Village")
    private 	String	Family2Village;
    
    @Column(name="Family2District", nullable = true)
    @JsonProperty(value="Family2District")
    private 	String	Family2District;
    
    @Column(name="Family2City", nullable = true)
    @JsonProperty(value="Family2City")
    private 	String	Family2City;
    
    @Column(name="Family2Province", nullable = true)
    @JsonProperty(value="Family2Province")
    private 	String	Family2Province;
    
    @Column(name="Family2PostalCode", nullable = true)
    @JsonProperty(value="Family2PostalCode")
    private 	String	Family2PostalCode;
    
    @Column(name="MaritalStatus", nullable = true)
    @JsonProperty(value="MaritalStatus")
    private 	String	MaritalStatus;
    
    @Column(name="AmountChild", nullable = true)
    @JsonProperty(value="AmountChild")
    private 	Integer	AmountChild;
    
    @Column(name="MonthlyIncome", nullable = true)
    @JsonProperty(value="MonthlyIncome")
    private 	Double	MonthlyIncome;
    
    @Column(name="HllWork", nullable = true)
    @JsonProperty(value="HllWork")
    private 	String	HllWork;
    
    @Column(name="BankNameID", nullable = true)
    @JsonProperty(value="BankNameID")
    private 	Integer	BankNameID;
    
    @Column(name="BankUsername", nullable = true)
    @JsonProperty(value="BankUsername")
    private 	String	BankUsername;
    
    @Column(name="BankNumber", nullable = true)
    @JsonProperty(value="BankNumber")
    private 	String	BankNumber;
    
    @Column(name="MrtwID", nullable = true)
    @JsonProperty(value="MrtwID")
    private 	Integer	MrtwID;
    
    @Column(name="EmployerType", nullable = true)
    @JsonProperty(value="EmployerType")
    private 	String	EmployerType;
    
    @Column(name="EmployerName", nullable = true)
    @JsonProperty(value="EmployerName")
    private 	String	EmployerName;
    
    @Column(name="EmployerRole", nullable = true)
    @JsonProperty(value="EmployerRole")
    private 	String	EmployerRole;
    
    @Column(name="EmployerAddress", nullable = true)
    @JsonProperty(value="EmployerAddress")
    private 	String	EmployerAddress;
    
    @Column(name="EmployerVillage", nullable = true)
    @JsonProperty(value="EmployerVillage")
    private 	String	EmployerVillage;
    
    @Column(name="EmployerDistrict", nullable = true)
    @JsonProperty(value="EmployerDistrict")
    private 	String	EmployerDistrict;
    
    @Column(name="EmployerCity", nullable = true)
    @JsonProperty(value="EmployerCity")
    private 	String	EmployerCity;
    
    @Column(name="EmployerProvince", nullable = true)
    @JsonProperty(value="EmployerProvince")
    private 	String	EmployerProvince;
    
    @Column(name="EmployerPostalCode", nullable = true)
    @JsonProperty(value="EmployerPostalCode")
    private 	String	EmployerPostalCode;
    
    @Column(name="EmployerCountry")
    @JsonProperty(value="EmployerCountry")
    private 	Integer	EmployerCountry;
    
    @Column(name="TelephoneWork", nullable = true)
    @JsonProperty(value="TelephoneWork")
    private 	String	TelephoneWork;
    
    @Column(name="LastFromIpAddress", nullable = true)
    @JsonProperty(value="LastFromIpAddress")
    private 	String	LastFromIpAddress;

    @Column(name="CreatedAt", nullable = true)
    private 	Date	CreatedAt;

    @Column(name="LastUpdatedAt", nullable = true)
    private 	Date	LastUpdatedAt;
    
    @Column(name="CustomerLastLoanPosition", nullable = true)
    @JsonProperty(value="CustomerLastLoanPosition")
    private 	String	CustomerLastLoanPosition;
    
    @Column(name="CustomerLastLoanStatusID", nullable = true)
    @JsonProperty(value="CustomerLastLoanStatusID")
    private 	Integer	CustomerLastLoanStatusID;
    
    @Column(name="InterestFees", nullable = true)
    @JsonProperty(value="InterestFees")
    private 	Double	InterestFees;
    
    @Column(name="Remember", nullable = true)
    @JsonProperty(value="Remember")
    private 	String	Remember;
    
    @Column(name="CustomerStatus", nullable = true)
    @JsonProperty(value="CustomerStatus")
    private 	String	CustomerStatus;
    
    @Column(name="QuotaAmount", nullable = true)
    @JsonProperty(value="QuotaAmount")
    private 	Double	QuotaAmount;
    
    @Column(name="Commission", nullable = true)
    @JsonProperty(value="Commission")
    private 	Integer	Commission;
    
    // @Column(name="Repeat", columnDefinition = "ENUM('Y','N')")
    // @JsonProperty(value="Repeat")
    // private 	String	Repeat;
    
    @Column(name="ChangePassword", nullable = true)
    @JsonProperty(value="ChangePassword")
    private 	String	ChangePassword;
    
    @Column(name="Latitude", nullable = true)
    @JsonProperty(value="Latitude")
    private 	String	Latitude;
    
    @Column(name="Longitude", nullable = true)
    @JsonProperty(value="Longitude")
    private 	String	Longitude;
    
    @Column(name="MotherFullname", nullable = true)
    @JsonProperty(value="MotherFullname")
    private 	String	MotherFullname;
    
    @Column(name="AddressRt", nullable = true)
    @JsonProperty(value="AddressRt")
    private 	String	AddressRt;
    
    @Column(name="AddressRw", nullable = true)
    @JsonProperty(value="AddressRw")
    private 	String	AddressRw;
    
    @Column(name="SpouseName", nullable = true)
    @JsonProperty(value="SpouseName")
    private 	String	SpouseName;
    
    @Column(name="SpousePersonalIDNumber", nullable = true)
    @JsonProperty(value="SpousePersonalIDNumber")
    private 	String	SpousePersonalIDNumber;
    
    @Column(name="SpouseFamilyIDNumber", nullable = true)
    @JsonProperty(value="SpouseFamilyIDNumber")
    private 	String	SpouseFamilyIDNumber;
    
    // @Column(name="SpouseAddress", nullable = true)
    // @JsonProperty(value="SpouseAddress")
    // private 	String	SpouseAddress;
    
    // @Column(name="SpouseAddressRT", nullable = true)
    // @JsonProperty(value="SpouseAddressRT")
    // private 	String	SpouseAddressRT;
    
    // @Column(name="SpouseAddressRW", nullable = true)
    // @JsonProperty(value="SpouseAddressRW")
    // private 	String	SpouseAddressRW;
    
    @Column(name="SpouseVillage", nullable = true)
    @JsonProperty(value="SpouseVillage")
    private 	String	SpouseVillage;
    
    @Column(name="SpouseDistrict", nullable = true)
    @JsonProperty(value="SpouseDistrict")
    private 	String	SpouseDistrict;
    
    // @Column(name="SpouseCity", nullable = true)
    // @JsonProperty(value="SpouseCity")
    // private 	String	SpouseCity;
    
    @Column(name="SpouseProvince", nullable = true)
    @JsonProperty(value="SpouseProvince")
    private 	String	SpouseProvince;
    
    @Column(name="SpousePostalCode", nullable = true)
    @JsonProperty(value="SpousePostalCode")
    private 	String	SpousePostalCode;
    
    @Column(name="SpouseWorkName", nullable = true)
    @JsonProperty(value="SpouseWorkName")
    private 	String	SpouseWorkName;
    
    @Column(name="SpouseWorkAddress", nullable = true)
    @JsonProperty(value="SpouseWorkAddress")
    private 	String	SpouseWorkAddress;
    
    @Column(name="SpouseWorkTelephone", nullable = true)
    @JsonProperty(value="SpouseWorkTelephone")
    private 	String	SpouseWorkTelephone;
    
    @Column(name="SpouseWorkAddressRt", nullable = true)
    @JsonProperty(value="SpouseWorkAddressRt")
    private 	String	SpouseWorkAddressRt;
    
    @Column(name="SpouseWorkAddressRw", nullable = true)
    @JsonProperty(value="SpouseWorkAddressRw")
    private 	String	SpouseWorkAddressRw;
    
    @Column(name="SpouseWorkVillage", nullable = true)
    @JsonProperty(value="SpouseWorkVillage")
    private 	String	SpouseWorkVillage;
    
    @Column(name="SpouseWorkDistrict", nullable = true)
    @JsonProperty(value="SpouseWorkDistrict")
    private 	String	SpouseWorkDistrict;
    
    @Column(name="SpouseWorkCity", nullable = true)
    @JsonProperty(value="SpouseWorkCity")
    private 	String	SpouseWorkCity;
    
    @Column(name="SpouseWorkProvince", nullable = true)
    @JsonProperty(value="SpouseWorkProvince")
    private 	String	SpouseWorkProvince;
    
    @Column(name="SpouseWorkPostalCode", nullable = true)
    @JsonProperty(value="SpouseWorkPostalCode")
    private 	String	SpouseWorkPostalCode;
    
    @Column(name="SpouseMonthlyIncome", nullable = true)
    @JsonProperty(value="SpouseMonthlyIncome")
    private 	Double	SpouseMonthlyIncome;
    
}    