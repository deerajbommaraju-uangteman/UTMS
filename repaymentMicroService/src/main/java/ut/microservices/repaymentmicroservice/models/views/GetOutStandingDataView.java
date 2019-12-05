package ut.microservices.repaymentmicroservice.models.views;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "RPYMS_VW_GetOutStandingDataView")
@Data
public class GetOutStandingDataView implements Serializable{

    private static final long serialVersionUID = 1L;

    
    @Column(name="ApplicationID")
    private Integer ApplicationID;

    @Column(name="CldLoanApplicationID")
    private String CldLoanApplicationID;

    @Id
    @Column(name="ApplicationApplicantID")
    private Integer ApplicationApplicantID;

    @Column(name="CldLoanAmount")
    private Double CldLoanAmount;

    @Column(name="CldLoanStartDatetime")
    private Date CldLoanStartDatetime;

    @Column(name="CldLoanDueDatetime")
    private Date CldLoanDueDatetime;

    @Column(name="CldPromoCode")
    private String CldPromoCode;

    @Column(name="IsInstallment")
    private String IsInstallment;

    @Column(name="CldStatus")
    private String CldStatus;

}
