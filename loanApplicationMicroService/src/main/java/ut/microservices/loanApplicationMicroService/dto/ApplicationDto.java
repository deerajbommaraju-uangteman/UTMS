package ut.microservices.loanapplicationmicroservice.dto;

/**
 * ApplicationDTO
 */
public class ApplicationDTO {

    private 	Integer	ApplicationID;
    private 	Integer	ApplicationApplicantID;
    private 	Integer	ApplictionAmID;
    private 	String	PromoCode;
    private 	String	LoanApplicationID;
    private 	Double	LoanAmount;
    private 	Integer	LoanDaysLength;
    private 	Double	LoanInterestRate;

    public Integer getApplicationID() {
        return ApplicationID;
    }

    public void setApplicationID(Integer applicationID) {
        ApplicationID = applicationID;
    }

    public Integer getApplicationApplicantID() {
        return ApplicationApplicantID;
    }

    public void setApplicationApplicantID(Integer applicationApplicantID) {
        ApplicationApplicantID = applicationApplicantID;
    }

    public Integer getApplictionAmID() {
        return ApplictionAmID;
    }

    public void setApplictionAmID(Integer applictionAmID) {
        ApplictionAmID = applictionAmID;
    }

    public String getPromoCode() {
        return PromoCode;
    }

    public void setPromoCode(String promoCode) {
        PromoCode = promoCode;
    }

    public String getLoanApplicationID() {
        return LoanApplicationID;
    }

    public void setLoanApplicationID(String loanApplicationID) {
        LoanApplicationID = loanApplicationID;
    }

    public Double getLoanAmount() {
        return LoanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        LoanAmount = loanAmount;
    }

    public Integer getLoanDaysLength() {
        return LoanDaysLength;
    }

    public void setLoanDaysLength(Integer loanDaysLength) {
        LoanDaysLength = loanDaysLength;
    }

    public Double getLoanInterestRate() {
        return LoanInterestRate;
    }

    public void setLoanInterestRate(Double loanInterestRate) {
        LoanInterestRate = loanInterestRate;
    }

}
