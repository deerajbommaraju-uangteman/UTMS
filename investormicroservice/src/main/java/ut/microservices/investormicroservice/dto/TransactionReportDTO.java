package ut.microservices.investormicroservice.dto;
public class TransactionReportDTO {
    private String fundTxnNumber;
    private String vaNumber;
    private String transactionDate;
    private int totalCustomers;
    private Double totalInvestment;
    private String txnStatus;


    public String getFundTxnNumber() {
        return fundTxnNumber;
    }

    public void setFundTxnNumber(String fundTxnNumber) {
        this.fundTxnNumber = fundTxnNumber;
    }

    public String getVaNumber() {
        return vaNumber;
    }

    public void setVaNumber(String vaNumber) {
        this.vaNumber = vaNumber;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public Double getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(Double totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public String getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }
}