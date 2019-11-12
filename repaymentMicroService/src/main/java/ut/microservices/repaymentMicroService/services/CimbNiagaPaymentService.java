package ut.microservices.repaymentmicroservice.services;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ut.microservices.repaymentmicroservice.configurations.RepaymentConfig;
import ut.microservices.repaymentmicroservice.dao.IGenericDAO;
import ut.microservices.repaymentmicroservice.models.ApplicantData;
import ut.microservices.repaymentmicroservice.models.ApplicationData;
import ut.microservices.repaymentmicroservice.models.CustomerLoanInstallmentRepayment;
import ut.microservices.repaymentmicroservice.models.CustomerLoanRepayment;
import ut.microservices.repaymentmicroservice.models.CustomerVaHistory;

@Component
@Service
@Transactional
public class CimbNiagaPaymentService {

    IGenericDAO<ApplicantData> applicantDataDAO;
    IGenericDAO<ApplicationData> applicationDataDAO;
    IGenericDAO<CustomerVaHistory> customerVaHistoryDAO;
    IGenericDAO<CustomerLoanRepayment> custLoanRepaymentDAO;
    IGenericDAO<CustomerLoanInstallmentRepayment> clirDAO;

    @Autowired
    public void setApplicantDataDAO(IGenericDAO<ApplicantData> applicantDataDAO){
        this.applicantDataDAO = applicantDataDAO;
        applicantDataDAO.setClazz(ApplicantData.class);
    }

    @Autowired
    public void setApplicationDataDAO(IGenericDAO<ApplicationData> applicationDataDAO){
        this.applicationDataDAO = applicationDataDAO;
        applicationDataDAO.setClazz(ApplicationData.class);
    }

    @Autowired
    public void setCustomerVaHistoryDAO(IGenericDAO<CustomerVaHistory> customerVaHistoryDAO) {
        this.customerVaHistoryDAO = customerVaHistoryDAO;
        customerVaHistoryDAO.setClazz(CustomerVaHistory.class);
    }

    @Autowired
    public void setCustLoanRepaymentDAO(IGenericDAO<CustomerLoanRepayment> custLoanRepaymentDAO) {
        this.custLoanRepaymentDAO = custLoanRepaymentDAO;
        custLoanRepaymentDAO.setClazz(CustomerLoanRepayment.class);
    }

    @Autowired
    public void setCustLoanInstallmentDAO(IGenericDAO<CustomerLoanInstallmentRepayment> clirDAO) {
        this.clirDAO = clirDAO;
        clirDAO.setClazz(CustomerLoanInstallmentRepayment.class);
    }

    @Autowired
    private ObjectMapper objectMapper;

    // @Value("${cimb.prefix}")
    // private String prefixVa;

    HashMap<String, String> data = new HashMap<>();

    // set request values for CIMB va generation
	public void setRequest(HashMap<String, String> userdata) {
        if (userdata.isEmpty()) {
			// log to debug_result - data is empty in request
			return;
		}
        String generatedVaNumber = this.setStaticVirtualAccount(userdata.get("phoneNumber"));
        
        this.data.put("payment_code", generatedVaNumber);
		this.data.put("status_type", "I");
		this.data.put("words", "");
		this.data.put("loan_app_id", userdata.get("LoanApplicationID"));
		this.data.put("amount", userdata.get("AmountToPay"));
	}

	private String setStaticVirtualAccount(String mobileNo) {
        return  RepaymentConfig.CIMB_PREFIX + RepaymentConfig.CIMB_ESCROW_CODE + mobileNo;
    }

    public HashMap<String, String> getRequest() {
	    return this.data;
	}

}