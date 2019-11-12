package ut.microservices.repaymentmicroservice.services;

import java.util.Calendar;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ut.microservices.repaymentmicroservice.dao.IGenericDAO;
import ut.microservices.repaymentmicroservice.models.*;

@Component
@Service
@Transactional
public class DokuPaymentService {

	//Standard Currency Code for IDR
	final int PAY_CURRENCY = 360;
	//Shared key from DOKU merchant 
	final String SHARED_KEY = "848xymdHV9GR";

    private final boolean isBcaLive = false;
    private final String bcaPrefixVaDev = "39991";
    private final String bcaPrefixVaProd = "10813";
    
    private final int BCA_PAY_CHANNEL = 29;
	private final int BCA_MALL_ID_DEV = 4549;

	//CHAIN MERCHANT
    private final int BCA_CHAIN_MERCHANT = 0;
    
    private int bcamallId;

	HashMap<String, String> data = new HashMap<>();
	
    IGenericDAO<CustomerVaHistory> customerVaHistoryDAO;
    IGenericDAO<CustomerLoanRepayment> custLoanRepaymentDAO;
	IGenericDAO<LogDokuBca> logDokuBcaDAO;

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
    public void setLogDokuBcaDAO(IGenericDAO<LogDokuBca> logDokuBcaDAO) {
        this.logDokuBcaDAO = logDokuBcaDAO;
        logDokuBcaDAO.setClazz(LogDokuBca.class);
    }

	@Autowired
	private ObjectMapper objectMapper;
	
	String inqResponse = null;

	// Set BCA request data for generateDokuVA - VA generation
	public void setBCARequest(HashMap<String, String> userdata) {

		if (userdata.isEmpty()) {
			// log to debug_result - data is empty in BCA request
			return;
		}
		String generatedVaNumber = this.setBcaVANumber(userdata.get("phoneNumber"));

		// Ad dlogic to check registered virtual number existance
			
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

	private String setBcaVANumber(String phoneNumber) {
		if (this.isBcaLive) {
			return this.bcaPrefixVaProd + phoneNumber;
		}
		return this.bcaPrefixVaDev + phoneNumber;
	}

	public void setVAInquiryResponse(HashMap<String, String> requestdata, LogDokuBca objLog) throws Exception{

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
            objLog.setInquiryReqDatetime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
            // objLog.setInquiryReqDatetime(currentdate.format(d));
            objLog.setInquiryResponse(xml);
            objLog.setInquiryRespDatetime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
            logDokuBcaDAO.save(objLog);
			inqResponse = "Invalid data";
        }

		CustomerVaHistory vaNumberData = customerVaHistoryDAO.findByVANumber(requestdata.get("PAYMENTCODE")).get(0);
		
		if(vaNumberData == null){
			System.out.println("VA data is empty for Inquiry");
            objLog.setVaNumber(requestdata.get("PAYMENTCODE"));
            objLog.setLogAppID(null);
            objLog.setInquiryRequest(objectMapper.writeValueAsString(requestdata));
            objLog.setInquiryReqDatetime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
            // objLog.setInquiryReqDatetime(currentdate.format(d));
            objLog.setInquiryResponse(xml);
            objLog.setInquiryRespDatetime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
            logDokuBcaDAO.save(objLog);
			inqResponse = "Invalid VA number";
		}
		
        if(vaNumberData.getStatus() == 0 && vaNumberData.getIsVaActive().equals("Y")){

            //Updating the generated Transmerchant id in CustomerVaHistory
            String randTransId = RandomStringUtils.randomAlphanumeric(10);
            vaNumberData.setVaTransMerchantID(randTransId);
            customerVaHistoryDAO.update(vaNumberData);

            String signature = this.getBcaMallId() + this.SHARED_KEY + randTransId;

            // Inquiry response for unpaid loan
            String nPaidResp = "<PAYMENTCODE>" + requestdata.get("PAYMENTCODE") + "</PAYMENTCODE>\n";
            nPaidResp += "<AMOUNT>"+ vaNumberData.getAmountToPay() +"</AMOUNT>\n";
            nPaidResp += "<PURCHASEAMOUNT>"+ vaNumberData.getAmountToPay() +"</PURCHASEAMOUNT>\n";
            nPaidResp += "<TRANSIDMERCHANT>" + randTransId + "</TRANSIDMERCHANT>\n";
            nPaidResp += "<WORDS>"+ signature +"</PURCHASEAMOUNT>\n";
            nPaidResp += "<CURRENCY>" + this.PAY_CURRENCY +"</CURRENCY>\n";
            nPaidResp += "<PURCHASECURRENCY>" + this.PAY_CURRENCY +"</PURCHASECURRENCY>\n";
            nPaidResp += "<SESSIONID>" + RandomStringUtils.randomAlphanumeric(32) +"</SESSIONID>\n";
            nPaidResp += "<ADDITIONALDATA>UangTeman</ADDITIONALDATA>\n";
            CustomerLoanRepayment clr = custLoanRepaymentDAO.findValueByColumn("ApplicantID",vaNumberData.getApplicantID()).get(0);    

            objLog.setVaNumber(requestdata.get("PAYMENTCODE"));
            objLog.setLogAppID(clr.getLoanApplicationID());
            objLog.setInquiryRequest(objectMapper.writeValueAsString(requestdata));
            // objLog.setInquiryReqDatetime(currentdate.format(now));
            xml = headResp + nPaidResp + footResp;
            objLog.setInquiryResponse(xml);
            logDokuBcaDAO.save(objLog);

            inqResponse = "success";
        }

        // response.put("message", msg);
        // response.put("status", "success");
	}

	public String getResponse() {
		return this.inqResponse;
	}

	public String getBcaMallId() {
		return String.valueOf(this.bcamallId = BCA_MALL_ID_DEV);
	}

	public String getBcaBin() {
		return (this.isBcaLive) ? this.bcaPrefixVaProd : this.bcaPrefixVaDev;
	}

	private String setSignature() {
		return this.data.get("email_address") + "-" + this.data.get("loan_app_id") + "-" + this.data.get("amount");
		// return md5(this.data.get("email_address") + "-" +
		// this.data.get("loan_app_id") + "-" + this.data.get("amount"));
	}

	public void setRequest(HashMap<String, String> userdata) {
	}

	public HashMap<String, String> getRequest() {
		return this.data;
	}

	public int getAlfaBin() {
		return 0;
		// return (this.isLive) ? this.prefixVaProd : this.prefixVaDev;
	}


	

}