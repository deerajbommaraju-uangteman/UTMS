package ut.microservices.investorMicroService.dto;
public class InvestorFundedLoansDto {
    private String ID;
    private String key;
    private String loanAppID;
    private String loanAmount;
    private String loanTenor;
    private String applicationID;

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLoanAppID() {
        return loanAppID;
    }

    public void setLoanAppID(String loanAppID) {
        this.loanAppID = loanAppID;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanTenor() {
        return loanTenor;
    }

    public void setLoanTenor(String loanTenor) {
        this.loanTenor = loanTenor;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }
}