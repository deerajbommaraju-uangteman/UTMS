package ut.microservices.investormicroservice.dto;

import java.util.Date;

public class LenderDocumentsDTO {
    private String transactionID;
    private String vaNumber;
    private String fundingAmount;
    private Date transactionDate;
    private Integer totalLoans;
    private String lenderUTAgreementsStatus;
    private String lenderBorrrowerAgreementsStatus;
    private boolean showLenderUTSign;
    private boolean showLenderBorrowerSign;

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getVaNumber() {
        return vaNumber;
    }

    public void setVaNumber(String vaNumber) {
        this.vaNumber = vaNumber;
    }

    public String getFundingAmount() {
        return fundingAmount;
    }

    public void setFundingAmount(String fundingAmount) {
        this.fundingAmount = fundingAmount;
    }

    public Integer getTotalLoans() {
        return totalLoans;
    }

    public void setTotalLoans(Integer totalLoans) {
        this.totalLoans = totalLoans;
    }

    public String getLenderUTAgreementsStatus() {
        return lenderUTAgreementsStatus;
    }

    public void setLenderUTAgreementsStatus(String lenderUTAgreementsStatus) {
        this.lenderUTAgreementsStatus = lenderUTAgreementsStatus;
    }

    public String getLenderBorrrowerAgreementsStatus() {
        return lenderBorrrowerAgreementsStatus;
    }

    public void setLenderBorrrowerAgreementsStatus(String lenderBorrrowerAgreementsStatus) {
        this.lenderBorrrowerAgreementsStatus = lenderBorrrowerAgreementsStatus;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }


    public boolean isShowLenderUTSign() {
        return showLenderUTSign;
    }

    public void setShowLenderUTSign(boolean showLenderUTSign) {
        this.showLenderUTSign = showLenderUTSign;
    }

    public boolean isShowLenderBorrowerSign() {
        return showLenderBorrowerSign;
    }

    public void setShowLenderBorrowerSign(boolean showLenderBorrowerSign) {
        this.showLenderBorrowerSign = showLenderBorrowerSign;
    }

}