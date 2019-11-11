package ut.microservices.loanApplicationMicroService.dto;

public class ApplicantFormDto{

    private 	Integer	ApplicantID;
    private 	String	Fullname;
    private 	String	EmailAddress;
    private 	String	PlaceOfBirth;
    private 	Integer	Age;
    private 	String	Religion;
    private 	Integer	Education;
    private 	String	NationalStatus;
    private 	String	PersonalIDNumber;
    private 	String	FamilyIDNumber;
    private 	String	Address;

    public Integer getApplicantID() {
        return ApplicantID;
    }

    public void setApplicantID(Integer applicantID) {
        ApplicantID = applicantID;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getPlaceOfBirth() {
        return PlaceOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        PlaceOfBirth = placeOfBirth;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public String getReligion() {
        return Religion;
    }

    public void setReligion(String religion) {
        Religion = religion;
    }

    public Integer getEducation() {
        return Education;
    }

    public void setEducation(Integer education) {
        Education = education;
    }

    public String getNationalStatus() {
        return NationalStatus;
    }

    public void setNationalStatus(String nationalStatus) {
        NationalStatus = nationalStatus;
    }

    public String getPersonalIDNumber() {
        return PersonalIDNumber;
    }

    public void setPersonalIDNumber(String personalIDNumber) {
        PersonalIDNumber = personalIDNumber;
    }

    public String getFamilyIDNumber() {
        return FamilyIDNumber;
    }

    public void setFamilyIDNumber(String familyIDNumber) {
        FamilyIDNumber = familyIDNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
    
}