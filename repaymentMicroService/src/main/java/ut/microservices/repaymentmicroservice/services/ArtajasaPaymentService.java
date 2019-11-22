package ut.microservices.repaymentmicroservice.services;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ut.microservices.repaymentmicroservice.configurations.ServicesConfig;
import ut.microservices.repaymentmicroservice.dao.IGenericDAO;
import ut.microservices.repaymentmicroservice.models.ApplicantData;
import ut.microservices.repaymentmicroservice.models.ApplicationData;
import ut.microservices.repaymentmicroservice.models.CustomerLoanInstallmentRepayment;
import ut.microservices.repaymentmicroservice.models.CustomerLoanRepayment;
import ut.microservices.repaymentmicroservice.models.CustomerVaHistory;
import ut.microservices.repaymentmicroservice.models.LogsArtajasa;
import ut.microservices.repaymentmicroservice.models.VaArtajasa;

@Component
@Service
@Transactional
public class ArtajasaPaymentService {

    IGenericDAO<ApplicantData> applicantDataDAO;
    IGenericDAO<ApplicationData> applicationDataDAO;
    IGenericDAO<CustomerVaHistory> customerVaHistoryDAO;
    IGenericDAO<CustomerLoanRepayment> custLoanRepaymentDAO;
    IGenericDAO<CustomerLoanInstallmentRepayment> clirDAO;
    IGenericDAO<VaArtajasa> vaArtajasaDAO;
    IGenericDAO<LogsArtajasa> logsArtajasaDAO;

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
    public void setVaArtajasaDAO(IGenericDAO<VaArtajasa> vaArtajasaDAO) {
        this.vaArtajasaDAO = vaArtajasaDAO;
        vaArtajasaDAO.setClazz(VaArtajasa.class);
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
    public void setLogsArtajasaDAO(IGenericDAO<LogsArtajasa> logsArtajasaDAO) {
        this.logsArtajasaDAO = logsArtajasaDAO;
        logsArtajasaDAO.setClazz(LogsArtajasa.class);
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

    public HashMap<String, String> data = new HashMap<>();

    public HashMap<String, String> inquiryResponse = new HashMap<>();

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

	public String getSignatureBiller() {
		if(this.isLive){
			return DigestUtils.md5Hex(this.secretKeyBillerProd + this.usernameBillerProd); // add md5 hashing
		}else{
			return DigestUtils.md5Hex(this.secretKeyBiller + this.usernameBiller); 
		}
    }

    public HashMap<String, String> getStaticVARequest() {
		return this.data;
    }
    
    public void setInquiryResponse(HashMap<String, String> requestdata, LogsArtajasa logArtajasa){

        Date inqRequestDatetime = new Date();
        // Set XML Format for Inquiry response
        String headResp = "<?xml version='1.0'?>\n";
        headResp += "<data>\n";
		String footResp = "</data>\n";
        String Resp = "<type>resinqpayment</type>\n";

        if(requestdata.isEmpty()){
    
            Resp += "<ack>"+ ServicesConfig.GENERAL_ERROR_05 +"</ack>\n";
            Resp += "<bookingid>NULL</bookingid>\n";
            Resp += "<customer_name>NULL</customer_name>\n";
            Resp += "<min_amount>0</min_amount>\n";
            Resp += "<max_amount>0</max_amount>\n";
            Resp += "<productid>NULL</productid>\n";
            Resp += "<signature>" + this.getSignatureBiller() +"</signature>\n";

            String xml = headResp + Resp + footResp;
            
            logArtajasa.setVaNumber(null);
            logArtajasa.setLogAppID(null);
            logArtajasa.setVaRequest(requestdata.toString());
            logArtajasa.setVaResponse(xml);
            logArtajasa.setVaReqDatetime(inqRequestDatetime);
            logArtajasa.setVaRespDatetime(new Date());
            logsArtajasaDAO.save(logArtajasa);

            this.inquiryResponse.put("status","false");
            this.inquiryResponse.put("message", xml);

			return ;
        }

        // signature mismatch during inquiry request
        if(requestdata.get("signature") != this.getSignatureBiller()){

            Resp += "<ack>"+ ServicesConfig.ILLEGAL_SIGNATURE_INQ_REQUEST_01 +"</ack>\n";
            Resp += "<bookingid>NULL</bookingid>\n";
            Resp += "<customer_name>NULL</customer_name>\n";
            Resp += "<min_amount>0</min_amount>\n";
            Resp += "<max_amount>0</max_amount>\n";
            Resp += "<productid>NULL</productid>\n";
            Resp += "<signature>" + this.getSignatureBiller() +"</signature>\n";

            String xml = headResp + Resp + footResp;
            
            logArtajasa.setVaNumber(null);
            logArtajasa.setLogAppID(null);
            logArtajasa.setVaRequest(requestdata.toString());
            logArtajasa.setVaResponse(xml);
            logArtajasa.setVaReqDatetime(inqRequestDatetime);
            logArtajasa.setVaRespDatetime(new Date());
            logsArtajasaDAO.save(logArtajasa);

            this.inquiryResponse.put("status","false");
            this.inquiryResponse.put("message", xml);

			return ;
        }

		CustomerVaHistory vaNumberData = customerVaHistoryDAO.findByVANumber(requestdata.get("va")).get(0);
        
        // when va is not found
		if(vaNumberData == null){ 
            Resp += "<ack>"+ ServicesConfig.INVALID_TO_ACCOUNT_76 +"</ack>\n";
            Resp += "<bookingid>NULL</bookingid>\n";
            Resp += "<customer_name>NULL</customer_name>\n";
            Resp += "<min_amount>0</min_amount>\n";
            Resp += "<max_amount>0</max_amount>\n";
            Resp += "<productid>NULL</productid>\n";
            Resp += "<signature>" + this.getSignatureBiller() +"</signature>\n";

            String xml = headResp + Resp + footResp;
            
            logArtajasa.setVaNumber(requestdata.get("va"));
            logArtajasa.setLogAppID(null);
            logArtajasa.setVaRequest(requestdata.toString());
            logArtajasa.setVaResponse(xml);
            logArtajasa.setVaReqDatetime(inqRequestDatetime);
            logArtajasa.setVaRespDatetime(new Date());
            logsArtajasaDAO.save(logArtajasa);

			return ;                  
        }else{

            VaArtajasa vaa = vaArtajasaDAO.findValueByColumn("CvhID", vaNumberData.getID().toString()).get(0);

            if(vaNumberData.getStatus() == 1 && vaNumberData.getIsVaActive().equals("Y")){
                // already paid 
                Resp += "<ack>"+ ServicesConfig.ALREADY_PAID_78 +"</ack>\n";
                Resp += "<bookingid>" + vaa.getBookingID() + "</bookingid>\n";
                Resp += "<customer_name>NULL</customer_name>\n";
                Resp += "<min_amount>" + vaNumberData.getAmountToPay() + "</min_amount>\n";
                Resp += "<max_amount>" + vaNumberData.getAmountToPay() +"</max_amount>\n";
                Resp += "<productid>NULL</productid>\n";
                Resp += "<signature>" + this.getSignatureBiller() +"</signature>\n";
    
                String xml = headResp + Resp + footResp;
                
                logArtajasa.setVaNumber(vaNumberData.getVaNumber());
                logArtajasa.setLogAppID(null);
                logArtajasa.setVaRequest(requestdata.toString());
                logArtajasa.setVaResponse(xml);
                logArtajasa.setVaReqDatetime(inqRequestDatetime);
                logArtajasa.setVaRespDatetime(new Date());
                logsArtajasaDAO.save(logArtajasa);
    
                return ;   
            }else if(vaNumberData.getStatus() == 0 && !vaNumberData.getIsVaActive().equals("Y")){
                // not yet paid 
                Resp += "<ack>"+ ServicesConfig.TRANSACTION_SUCCESS_00 +"</ack>\n";
                Resp += "<bookingid>" + vaa.getBookingID() + "</bookingid>\n";
                Resp += "<customer_name>NULL</customer_name>\n";
                Resp += "<min_amount>" + vaNumberData.getAmountToPay() + "</min_amount>\n";
                Resp += "<max_amount>" + vaNumberData.getAmountToPay() +"</max_amount>\n";
                Resp += "<productid>NULL</productid>\n";
                Resp += "<signature>" + this.getSignatureBiller() +"</signature>\n";
    
                String xml = headResp + Resp + footResp;
                
                logArtajasa.setVaNumber(vaNumberData.getVaNumber());
                logArtajasa.setLogAppID(null);
                logArtajasa.setVaRequest(requestdata.toString());
                logArtajasa.setVaResponse(xml);
                logArtajasa.setVaReqDatetime(inqRequestDatetime);
                logArtajasa.setVaRespDatetime(new Date());
                logsArtajasaDAO.save(logArtajasa);
    
            }else{
                // error
                Resp += "<ack>"+ ServicesConfig.GENERAL_ERROR_05 +"</ack>\n";
                Resp += "<bookingid>NULL</bookingid>\n";
                Resp += "<customer_name>NULL</customer_name>\n";
                Resp += "<min_amount>0</min_amount>\n";
                Resp += "<max_amount>0</max_amount>\n";
                Resp += "<productid>NULL</productid>\n";
                Resp += "<signature>" + this.getSignatureBiller() +"</signature>\n";
    
                String xml = headResp + Resp + footResp;
                
                logArtajasa.setVaNumber(null);
                logArtajasa.setLogAppID(null);
                logArtajasa.setVaRequest(requestdata.toString());
                logArtajasa.setVaResponse(xml);
                logArtajasa.setVaReqDatetime(inqRequestDatetime);
                logArtajasa.setVaRespDatetime(new Date());
                logsArtajasaDAO.save(logArtajasa);

                this.inquiryResponse.put("status", "false");
                this.inquiryResponse.put("message", xml);
            }

        }
    }

    public HashMap<String, String> getInquiryResponse() {
		return this.inquiryResponse;
	}

}
