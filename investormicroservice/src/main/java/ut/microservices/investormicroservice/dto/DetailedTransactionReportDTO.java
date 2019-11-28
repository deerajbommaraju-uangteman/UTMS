package ut.microservices.investormicroservice.dto;
public class DetailedTransactionReportDTO{
    private String loanAppID;
    private String applicantName;
    private Double loanAmount;
    private String loanType;
    private Integer loanDuration;
    private String Status;
    private String operation;
    private String Remarks;

    public String getLoanAppID() {
        return loanAppID;
    }

    public void setLoanAppID(String loanAppID) {
        this.loanAppID = loanAppID;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Integer getLoanDuration() {
        return loanDuration;
    }

    public void setLoanDuration(Integer loanDuration) {
        this.loanDuration = loanDuration;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}