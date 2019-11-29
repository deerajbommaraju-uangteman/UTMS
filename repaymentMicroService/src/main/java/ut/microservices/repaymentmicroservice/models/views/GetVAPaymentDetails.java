package ut.microservices.repaymentmicroservice.models.views;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "RPYMS_VW_GetVAPaymentDetails")
@Data
public class GetVAPaymentDetails implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name="ApliLoanApplicationID")
    private String ApliLoanApplicationID;

    @Id
    @Column(name="cldId")
    private Integer cldId;

    @Column(name="CldApplicantID")
    private Integer CldApplicantID;

    @Column(name="ApplicationID")
    private Integer ApplicationID;

    @Column(name="IsInstallment")
    private String IsInstallment;

    @Column(name="CldStatus")
    private String CldStatus;

}