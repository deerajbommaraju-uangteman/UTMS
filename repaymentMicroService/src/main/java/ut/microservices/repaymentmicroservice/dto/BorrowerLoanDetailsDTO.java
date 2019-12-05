package ut.microservices.repaymentmicroservice.dto;

import ut.microservices.repaymentmicroservice.models.views.GetVAPaymentDetails;

public class BorrowerLoanDetailsDTO {
    private String cldStatus;
    private String isInstallment;

    private String paymentMethod;
    private Integer cvhId;
    private String vaNumber;
    private GetVAPaymentDetails cld;
    private Double repayAmount;
    private String loanApplicationID;
    private Integer applicationID;
    private Integer loanType;

    public String getCldStatus() {
        return cldStatus;
    }

    public void setCldStatus(String cldStatus) {
        this.cldStatus = cldStatus;
    }

    public String getIsInstallment() {
        return isInstallment;
    }

    public void setIsInstallment(String isInstallment) {
        this.isInstallment = isInstallment;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getCvhId() {
        return cvhId;
    }

    public void setCvhId(Integer cvhId) {
        this.cvhId = cvhId;
    }

    public String getVaNumber() {
        return vaNumber;
    }

    public void setVaNumber(String vaNumber) {
        this.vaNumber = vaNumber;
    }

    public GetVAPaymentDetails getCld() {
        return cld;
    }

    public void setCld(GetVAPaymentDetails cld) {
        this.cld = cld;
    }

    public Double getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(Double repayAmount) {
        this.repayAmount = repayAmount;
    }

    public String getLoanApplicationID() {
        return loanApplicationID;
    }

    public void setLoanApplicationID(String loanApplicationID) {
        this.loanApplicationID = loanApplicationID;
    }

    public Integer getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(Integer applicationID) {
        this.applicationID = applicationID;
    }

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }
    
}