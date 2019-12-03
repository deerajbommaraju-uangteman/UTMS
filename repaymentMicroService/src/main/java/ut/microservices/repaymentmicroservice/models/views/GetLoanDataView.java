package ut.microservices.repaymentmicroservice.models.views;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "RPYMS_VW_GetLoanDataView")
@Data
public class GetLoanDataView implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="CldID")
    private Integer CldID;
    
    @Column(name="CldLoanApplicationID", nullable = true)
    private String CldLoanApplicationID;

    @Column(name="CldLoanStartDatetime", nullable = true)
    private Date CldLoanStartDatetime;

    @Column(name="CldLoanDueDatetime", nullable = true)
    private Date CldLoanDueDatetime;

    @Column(name="LoanCountdownToDue", nullable = true)
    private Integer LoanCountdownToDue;

    @Column(name="CldLoanDaysLength", nullable = true)
    private Integer CldLoanDaysLength;

    @Column(name="CldStatus")
    private String CldStatus;

    @Column(name="ClrStatus", nullable = true)
    private String ClrStatus;

    @Column(name="ClrRepaymentDate", nullable = true)
    private Date ClrRepaymentDate;    

    @Column(name="ClrRepaymentAmount", nullable = true)
    private Double ClrRepaymentAmount;

    @Column(name="ExtensionDay", nullable = true)
    private Integer ExtensionDay; 
    
    @Column(name="ExtensionStatus", nullable = true)
    private String ExtensionStatus;      
}
