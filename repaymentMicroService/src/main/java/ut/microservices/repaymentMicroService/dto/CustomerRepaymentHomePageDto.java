package ut.microservices.repaymentMicroService.dto;

import java.util.Date;

public class CustomerRepaymentHomePageDto {
    private Double repaymentAmount;
    private String dueDate;
    private String message;

    public Double getRepaymentAmount() {
        return repaymentAmount;
    }
    public void setRepaymentAmount(Double repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}