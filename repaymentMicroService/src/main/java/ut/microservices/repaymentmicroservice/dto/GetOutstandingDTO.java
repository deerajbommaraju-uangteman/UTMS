package ut.microservices.repaymentmicroservice.dto;

import java.util.Date;

import ut.microservices.repaymentmicroservice.models.views.GetOutStandingDataView;

public class GetOutstandingDTO<T>{

    private Integer clirID;
    private Integer installment_index;
    private Integer gracePeriod;
    private Date dueDate;
    private T detailInstallment;  
    private Double repaymentAmount;
    private Double totalRepaymentAmount;
    private Integer tenor;
    private String status;
    private String message;

    public Integer getClirID() {
        return clirID;
    }

    public void setClirID(Integer clirID) {
        this.clirID = clirID;
    }

    public Integer getInstallment_index() {
        return installment_index;
    }

    public void setInstallment_index(Integer installment_index) {
        this.installment_index = installment_index;
    }

    public Integer getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(Integer gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public T getDetailInstallment() {
        return detailInstallment;
    }

    public void setDetailInstallment(T detailInstallment) {
        this.detailInstallment = detailInstallment;
    }

    public Double getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(Double repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public Double getTotalRepaymentAmount() {
        return totalRepaymentAmount;
    }

    public void setTotalRepaymentAmount(Double totalRepaymentAmount) {
        this.totalRepaymentAmount = totalRepaymentAmount;
    }

    public Integer getTenor() {
        return tenor;
    }

    public void setTenor(Integer tenor) {
        this.tenor = tenor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}