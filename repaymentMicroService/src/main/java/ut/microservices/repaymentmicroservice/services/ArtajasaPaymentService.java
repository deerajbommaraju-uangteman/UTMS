package ut.microservices.repaymentmicroservice.services;

import java.util.Calendar;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ut.microservices.repaymentmicroservice.dao.IGenericDAO;
import ut.microservices.repaymentmicroservice.models.ApplicantData;
import ut.microservices.repaymentmicroservice.models.ApplicationData;
import ut.microservices.repaymentmicroservice.models.CustomerLoanInstallmentRepayment;
import ut.microservices.repaymentmicroservice.models.CustomerLoanRepayment;
import ut.microservices.repaymentmicroservice.models.CustomerVaHistory;

@Component
@Service
@Transactional
public class ArtajasaPaymentService {

    IGenericDAO<ApplicantData> applicantDataDAO;
    IGenericDAO<ApplicationData> applicationDataDAO;
    IGenericDAO<CustomerVaHistory> customerVaHistoryDAO;
    IGenericDAO<CustomerLoanRepayment> custLoanRepaymentDAO;
    IGenericDAO<CustomerLoanInstallmentRepayment> clirDAO;

    @Autowired
    public void setApplicantDataDAO(IGenericDAO<ApplicantData> applicantDataDAO) {
        this.applicantDataDAO = applicantDataDAO;
        applicantDataDAO.setClazz(ApplicantData.class);
    }

    @Autowired
    public void setApplicationDataDAO(IGenericDAO<ApplicationData> applicationDataDAO) {
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

	private final boolean isLive = false;
    private String bankId;
	private final String artajasaPrefixVaProd = "505";
	private final String artajasaPrefixVaDev = "222";
    private final String artajasaCode = "500";

    private final String username = "andrew.tooy@uangteman.com";
	private final String usernameBiller	= "edria.julianata@uangteman.com";
	private final String usernameBillerProd = "Uangteman123";
        
    private String backupBookingId = null;

    private final String billerUsername	= "edria.julianata@uangteman.com";
    private String secretKey = "OY2bQgPU";
    private final String secretKeyBiller = "NNwV9OV0";
	private final String secretKeyBillerProd= "Uangteman123";

    HashMap<String, String> data = new HashMap<>();

    public void setStaticVARequest(HashMap<String, String> userdata) {
        if (userdata.isEmpty()) {
			// log to debug_result - data is empty in Artajasa request
			return;
		}
		String generatedVaNumber = this.setArtajasaVANumber(userdata.get("phoneNumber"));

        // Add logic to check registered virtual number existance
        
        String bookingId = userdata.get("ktp") + RandomUtils.nextInt(100000000, 999999999);;
        this.backupBookingId = bookingId.substring(0, 7);
        bookingId = backupBookingId.substring(0, 7); //add this.encrypt

        this.data.put("type","n/a");
        this.data.put("bookingid",bookingId);
		this.data.put("clientid", userdata.get("ApplicantID"));
        this.data.put("amount", userdata.get("AmountToPay"));
        this.data.put("productid", userdata.get("ApplicantID"));
        // this.data.put("interval", this.getExpiredMinutes());
		// this.data.put("customer_name", userdata.get("FullName"));
		this.data.put("username", this.billerUsername);
        this.data.put("booking_datetime",new java.util.Date(Calendar.getInstance().getTime().getTime()).toString());
        this.data.put("signature", this.getSignatureBiller());
        this.data.put("payment_code", generatedVaNumber);
		
	}

    private String setArtajasaVANumber(String phoneNumber) {
        if(this.isLive){
            return this.artajasaCode + this.artajasaPrefixVaProd + phoneNumber;
        }
      return this.artajasaCode + this.artajasaPrefixVaDev + phoneNumber;
    }

	private String getSignatureBiller() {
		if(this.isLive){
			return this.secretKeyBillerProd + this.usernameBillerProd; // add md5 hashing
		}else{
			return this.secretKeyBiller + this.usernameBiller; // add md5 hashing
		}
    }

    public HashMap<String, String> getStaticVARequest() {
		return this.data;
	}

}