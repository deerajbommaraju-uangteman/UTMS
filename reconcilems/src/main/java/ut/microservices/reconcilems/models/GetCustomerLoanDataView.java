package ut.microservices.reconcilems.models;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="RCNMS_VW_GetCustomerLoanData")
@Data
public class GetCustomerLoanDataView implements Serializable {

	private static  final long serialVersionUID=1L;
	
	@Id
    @Column(name = "cld_ID")
    private 	Integer	cld_ID;

    @Column(name = "cld_LoanApplicationID",nullable = true)
    private 	String	cld_LoanApplicationID;
  
	@Column(name="Apli_IsInstallment",nullable = true)
	public 	String	Apli_IsInstallment;

    @Column(name="NotifiedTime",nullable = true)
    @DateTimeFormat
	public 	Date	NotifiedTime;

    @Column(name = "VaTransactionMerchantID",nullable = true)
	public 	String	VaTransactionMerchantID;  
}