package ut.microservices.repaymentmicroservice.models.views;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "RPYMS_VW_GetLoanDataView")
@Data
public class GetLoanDataView implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name="CldLoanApplicationID")
    private String CldLoanApplicationID;

    @Column(name="CldLoanStartDatetime")
    private Date CldLoanStartDatetime;

    @Column(name="CldLoanDueDatetime")
    private Date CldLoanDueDatetime;

    @Column(name="LoanCountdownToDue")
    private Integer LoanCountdownToDue;

    @Column(name="CldLoanDaysLength")
    private Integer CldLoanDaysLength;

    @Column(name="CldStatus")
    private String CldStatus;

    @Column(name="ClrStatus")
    private String ClrStatus;

    @Column(name="ClrRepaymentDate")
    private Date ClrRepaymentDate;    

    @Column(name="ClrRepaymentAmount")
    private Double ClrRepaymentAmount;

    @Column(name="ExtensionDay")
    private Integer ExtensionDay; 
    
    @Column(name="ExtensionStatus")
    private String ExtensionStatus;      
}
