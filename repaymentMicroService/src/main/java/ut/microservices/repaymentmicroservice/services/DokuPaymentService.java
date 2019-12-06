package ut.microservices.repaymentmicroservice.services;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ut.microservices.repaymentmicroservice.dao.IGenericDAO;
import ut.microservices.repaymentmicroservice.models.*;

@Component
@Service
@Transactional
public class DokuPaymentService {

    private final boolean isLive = false;
    private final boolean isBcaLive = false;

    //Payment Type Sent to Alfa
    private final int PAY_CHANNEL = 35;
    //Payment Type Sent to BCA
    private final int BCA_PAY_CHANNEL = 29;

    private final int MALL_ID_DEV = 4549;
    private final int BCA_MALL_ID_DEV = 4549;
    
    private final String prefixVaDev = "88888402";
    private final String prefixVaProd = "88888184";

    private final String bcaPrefixVaDev = "39991";
    private final String bcaPrefixVaProd = "10813";
    
    //CHAIN MERCHANT
    private final int CHAIN_MERCHANT = 0;
    private final int BCA_CHAIN_MERCHANT = 0;

    //Standard Currency Code for IDR
	final int PAY_CURRENCY = 360;
	//Shared key from DOKU merchant 
    final String SHARED_KEY = "848xymdHV9GR";
    
    private int mallId;
    private int bcamallId;

	HashMap<String, String> data = new HashMap<>();
    
    IGenericDAO<ApplicationData> applicationDataDAO;
    IGenericDAO<CustomerVaHistory> customerVaHistoryDAO;
    IGenericDAO<CustomerLoanData> customerLoanDataDAO;
    IGenericDAO<CustomerPrimaryData> custPrimaryDataDAO;
    IGenericDAO<CustomerLoanRepayment> custLoanRepaymentDAO;
    IGenericDAO<CustomerLoanInstallmentRepayment> custLoanInstallmentRepaymentDAO;
    IGenericDAO<LogDokuBca> logDokuBcaDAO;
    IGenericDAO<VaFirstPay> vaFirstPayDAO;

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
    public void setCustomerLoanDataDAO(IGenericDAO<CustomerLoanData> customerLoanDataDAO) {
        this.customerLoanDataDAO = customerLoanDataDAO;
        customerLoanDataDAO.setClazz(CustomerLoanData.class);
    }

    @Autowired
    public void setCustPrimaryDataDAO(IGenericDAO<CustomerPrimaryData> custPrimaryDataDAO) {
        this.custPrimaryDataDAO = custPrimaryDataDAO;
        custPrimaryDataDAO.setClazz(CustomerPrimaryData.class);
    }

    @Autowired
    public void setCustLoanRepaymentDAO(IGenericDAO<CustomerLoanRepayment> custLoanRepaymentDAO) {
        this.custLoanRepaymentDAO = custLoanRepaymentDAO;
        custLoanRepaymentDAO.setClazz(CustomerLoanRepayment.class);
    }

    @Autowired
    public void setCustLoanInstallmentRepaymentDAO(IGenericDAO<CustomerLoanInstallmentRepayment> custLoanInstallmentRepaymentDAO) {
        this.custLoanInstallmentRepaymentDAO = custLoanInstallmentRepaymentDAO;
        custLoanInstallmentRepaymentDAO.setClazz(CustomerLoanInstallmentRepayment.class);
    }
    
    @Autowired
    public void setLogDokuBcaDAO(IGenericDAO<LogDokuBca> logDokuBcaDAO) {
        this.logDokuBcaDAO = logDokuBcaDAO;
        logDokuBcaDAO.setClazz(LogDokuBca.class);
    }
    
    @Autowired
    public void setVaFirstPayDAO(IGenericDAO<VaFirstPay> vaFirstPayDAO) {
        this.vaFirstPayDAO = vaFirstPayDAO;
        vaFirstPayDAO.setClazz(VaFirstPay.class);
	}

	@Autowired
	private ObjectMapper objectMapper;
	
	HashMap<String, Object> inqResponse = new HashMap<>();

    // Set Doku Alfa request data for generateDokuVA - VA generation
    public void setRequest(HashMap<String, String> userdata) {
		if (userdata.isEmpty()) {
			// log to debug_result - data is empty in Alfa request
			return;
		}
		String generatedVaNumber = this.setDokuAlfaVANumber(userdata.get("phoneNumber"));

		// Add logic to check registered virtual number existance
			
		this.data.put("mall_id", this.getBcaMallId());
		this.data.put("chain_merchant", String.valueOf(CHAIN_MERCHANT));
		this.data.put("payment_channel", String.valueOf(PAY_CHANNEL));
		this.data.put("payment_code", generatedVaNumber);
		this.data.put("status_type", "I");
		this.data.put("words", "");
		// this.data.put("email_address", userdata.get("emailAddress"));
		this.data.put("loan_app_id", userdata.get("LoanApplicationID"));
		this.data.put("amount", userdata.get("AmountToPay"));
		// this.data.put("signature", this.setSignature());        
    }
    
	// Set BCA request data for generateDokuVA - VA generation
	public void setBCARequest(HashMap<String, String> userdata) {

		if (userdata.isEmpty()) {
			// log to debug_result - data is empty in BCA request
			return;
		}
		String generatedVaNumber = this.setBcaVANumber(userdata.get("phoneNumber"));

		// Add logic to check registered virtual number existance
			
		this.data.put("mall_id", this.getBcaMallId());
		this.data.put("chain_merchant", String.valueOf(BCA_CHAIN_MERCHANT));
		this.data.put("payment_channel", String.valueOf(BCA_PAY_CHANNEL));
		this.data.put("payment_code", generatedVaNumber);
		this.data.put("status_type", "I");
		this.data.put("words", "");
		// this.data.put("email_address", userdata.get("emailAddress"));
		this.data.put("loan_app_id", userdata.get("LoanApplicationID"));
		this.data.put("amount", userdata.get("AmountToPay"));
		// this.data.put("signature", this.setSignature());
    }
    
	private String setDokuAlfaVANumber(String phoneNumber) {
		if (this.isLive) {
			return this.prefixVaProd + phoneNumber;
		}
		return this.prefixVaDev + phoneNumber;
	}    

	private String setBcaVANumber(String phoneNumber) {
		if (this.isBcaLive) {
			return this.bcaPrefixVaProd + phoneNumber;
		}
		return this.bcaPrefixVaDev + phoneNumber;
	}

	public void setVAInquiryResponse(HashMap<String, String> requestdata, LogDokuBca objLog) throws Exception{

        Date dateRequest = new Date();

        // Set XML Format for Inquiry response
        String headResp = "<?xml version='1.0'?>\n";
        headResp += "<INQUIRY_RESPONSE>\n";
		String footResp = "</INQUIRY_RESPONSE>\n";
		String failResp = "<RESPONSECODE>9999</RESPONSECODE>\n";
		String paidResp = "<RESPONSECODE>3002</RESPONSECODE>\n";
		String xml = headResp + failResp + footResp;

        if(requestdata.isEmpty()){
            System.out.println("Request Data is empty for Inquiry");
            objLog.setVaNumber(null);
            objLog.setLogAppID(null);
            objLog.setInquiryRequest(objectMapper.writeValueAsString(requestdata));
            objLog.setInquiryReqDatetime(dateRequest);
            objLog.setInquiryRespDatetime(new Date());
            objLog.setInquiryResponse(xml);
            objLog.setInquiryRespDatetime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
            logDokuBcaDAO.save(objLog);

            inqResponse.put("status", false);
            inqResponse.put("message", "Invalid data");
            return;
        }

		CustomerVaHistory vaNumberData = customerVaHistoryDAO.findByVANumber(requestdata.get("PAYMENTCODE")).get(0);

		if(vaNumberData == null){
			System.out.println("VA data is empty for Inquiry");
            objLog.setVaNumber(requestdata.get("PAYMENTCODE"));
            objLog.setLogAppID(null);
            objLog.setInquiryRequest(objectMapper.writeValueAsString(requestdata));
            objLog.setInquiryReqDatetime(dateRequest);
            objLog.setInquiryRespDatetime(new Date());
            objLog.setInquiryResponse(xml);
            objLog.setInquiryRespDatetime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
            logDokuBcaDAO.save(objLog);

            inqResponse.put("status", false);
            inqResponse.put("message", "Invalid VA number");
            return;
		}
        
        CustomerLoanData cld = customerLoanDataDAO.findValueByColumn("ApplicantID", vaNumberData.getApplicantID()).get(0);
        CustomerPrimaryData borrower = custPrimaryDataDAO.findValueByColumn("ApplicantID", cld.getApplicantID().toString()).get(0);
        CustomerLoanRepayment clr = custLoanRepaymentDAO.findValueByColumn("ApplicantID",cld.getApplicantID().toString()).get(0);  
        String customerLoanRepaymentStatus = clr.getClrStatus();

        ApplicationData apli = applicationDataDAO.findByTwoColumns("LoanApplicationID", cld.getLoanApplicationID(), "ApplicationApplicantID", cld.getApplicantID().toString()).get(0);
        if(apli.getIsInstallment() != null && apli.getIsInstallment().equals("Y")){
            CustomerLoanInstallmentRepayment clir = custLoanInstallmentRepaymentDAO.findValueByColumn("CustomerLoanRepaymentID", clr.getId().toString()).get(0);
            customerLoanRepaymentStatus = clir.getStatus();
        }

        //this transaction is already being paid
        if(vaNumberData.getStatus() == 1 && customerLoanRepaymentStatus.equals("Y")){
            xml = headResp + paidResp + footResp;

            objLog.setVaNumber(requestdata.get("PAYMENTCODE"));
            objLog.setLogAppID(clr.getLoanApplicationID());
            objLog.setInquiryRequest(objectMapper.writeValueAsString(requestdata));
            objLog.setInquiryReqDatetime(dateRequest);
            objLog.setInquiryRespDatetime(new Date());
            objLog.setInquiryResponse(xml);
            logDokuBcaDAO.save(objLog);

            inqResponse.put("status", false);
            inqResponse.put("message",xml);
            return;
        }
        else if(vaNumberData.getStatus() == 0 && !customerLoanRepaymentStatus.equals("Y")){

            //Updating the generated Transmerchant id in CustomerVaHistory
            String randTransId = RandomStringUtils.randomAlphanumeric(10);
            vaNumberData.setVaTransMerchantID(randTransId);
            customerVaHistoryDAO.update(vaNumberData);

            String signature = this.getBcaMallId() + this.SHARED_KEY + randTransId; // add vaNumberData.getAmountToPay() and DigestUtils.shaHex()

            // add record to VaFirstPay
            VaFirstPay fpay = new VaFirstPay();
            fpay.setCvhID(vaNumberData.getID());
            fpay.setIDOrder(randTransId);
            fpay.setSignature(signature);
            vaFirstPayDAO.save(fpay);

            // Inquiry response for unpaid loan
            String nPaidResp = "<PAYMENTCODE>" + requestdata.get("PAYMENTCODE") + "</PAYMENTCODE>\n";
            nPaidResp += "<AMOUNT>"+ vaNumberData.getAmountToPay() +"</AMOUNT>\n";
            nPaidResp += "<PURCHASEAMOUNT>"+ vaNumberData.getAmountToPay() +"</PURCHASEAMOUNT>\n";
            nPaidResp += "<TRANSIDMERCHANT>" + randTransId + "</TRANSIDMERCHANT>\n";
            nPaidResp += "<WORDS>"+ signature +"</PURCHASEAMOUNT>\n";
            nPaidResp += "<CURRENCY>" + this.PAY_CURRENCY +"</CURRENCY>\n";
            nPaidResp += "<PURCHASECURRENCY>" + this.PAY_CURRENCY +"</PURCHASECURRENCY>\n";
            nPaidResp += "<SESSIONID>" + DigestUtils.shaHex(RandomStringUtils.randomAlphanumeric(32)) +"</SESSIONID>\n";
            nPaidResp += "<ADDITIONALDATA>UangTeman</ADDITIONALDATA>\n";
            // CustomerLoanRepayment clr = custLoanRepaymentDAO.findValueByColumn("ApplicantID",vaNumberData.getApplicantID()).get(0);    

            objLog.setVaNumber(requestdata.get("PAYMENTCODE"));
            objLog.setLogAppID(clr.getLoanApplicationID());
            objLog.setInquiryRequest(objectMapper.writeValueAsString(requestdata));
            objLog.setInquiryReqDatetime(dateRequest);
            objLog.setInquiryRespDatetime(new Date());
            xml = headResp + nPaidResp + footResp;
            objLog.setInquiryResponse(xml);
            logDokuBcaDAO.save(objLog);

            inqResponse.put("status", true);
            inqResponse.put("message",xml);

        }else{

            objLog.setVaNumber(requestdata.get("PAYMENTCODE"));
            objLog.setLogAppID(null);
            objLog.setInquiryRequest(objectMapper.writeValueAsString(requestdata));
            objLog.setInquiryResponse(headResp + failResp + footResp);
            objLog.setInquiryReqDatetime(dateRequest);
            objLog.setInquiryRespDatetime(new Date());
            logDokuBcaDAO.save(objLog);

            inqResponse.put("status", false);
            inqResponse.put("message", headResp + failResp + footResp);

        }

        // response.put("message", msg);
        // response.put("status", "success");
	}

	public HashMap<String, Object> getResponse() {
		return this.inqResponse;
	}

    public String getMallId() {
		return String.valueOf(this.mallId = MALL_ID_DEV);
    }
    
	public String getBcaMallId() {
		return String.valueOf(this.bcamallId = BCA_MALL_ID_DEV);
	}

    public String getAlfaBin() {
		return (this.isLive) ? this.prefixVaProd : this.prefixVaDev;
    }
    
	public String getBcaBin() {
		return (this.isBcaLive) ? this.bcaPrefixVaProd : this.bcaPrefixVaDev;
	}

	private String setSignature() {
		return DigestUtils.md5Hex(this.data.get("email_address") + "-" + this.data.get("loan_app_id") + "-" + this.data.get("amount"));
	}

	public HashMap<String, String> getRequest() {
		return this.data;
	}




	

}