package ut.microservices.investormicroservice.dto;
public class LoansDTO {
    private String ID;
    private String key;
    private String loanAppID;
    private String loanAmount;
    private String loanTenor;
    private String applicationID;
    private String status;
    private String button;
    

    public String getButton() {
        return button;
    }

    public void setButton(String btn) {
        button = btn;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}