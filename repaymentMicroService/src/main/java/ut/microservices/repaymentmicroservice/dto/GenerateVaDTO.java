package ut.microservices.repaymentmicroservice.dto;

import java.util.Date;

public class GenerateVaDTO {
    private String response;
    private String loanAppId;
    private Double repaymentAmount;
    private String vaNumber;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getloanAppId() {
        return loanAppId;
    }

    public void setloanAppId(String loanAppId) {
        this.loanAppId = loanAppId;
    }

    public Double getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(Double repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public String getVaNumber() {
        return vaNumber;
    }

    public void setVaNumber(String vaNumber) {
        this.vaNumber = vaNumber;
    }


}