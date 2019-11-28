package ut.microservices.repaymentmicroservice.services;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat; 
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
import ut.microservices.repaymentmicroservice.models.CustomerLoanData;
import ut.microservices.repaymentmicroservice.models.CustomerLoanInstallmentRepayment;
import ut.microservices.repaymentmicroservice.models.CustomerLoanRepayment;
import ut.microservices.repaymentmicroservice.models.CustomerPrimaryData;
import ut.microservices.repaymentmicroservice.models.CustomerVaHistory;
import ut.microservices.repaymentmicroservice.models.LogsArtajasa;
import ut.microservices.repaymentmicroservice.models.VaArtajasa;

@Component
@Service
@Transactional
public class ArtajasaPaymentService {

    IGenericDAO<ApplicantData> applicantDataDAO;
    IGenericDAO<ApplicationData> applicationDataDAO;
    IGenericDAO<CustomerLoanData> custLoanDataDAO;
    IGenericDAO<CustomerPrimaryData> custPrimaryDataDAO;
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
    public void setCustLoanDataDAO(IGenericDAO<CustomerLoanData> custLoanDataDAO) {
        this.custLoanDataDAO = custLoanDataDAO;
        custLoanDataDAO.setClazz(CustomerLoanData.class);
    }

    @Autowired
    public void setCustPrimaryDataDAO(IGenericDAO<CustomerPrimaryData> custPrimaryDataDAO) {
        this.custPrimaryDataDAO = custPrimaryDataDAO;
        custPrimaryDataDAO.setClazz(CustomerPrimaryData.class);
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

    public HashMap<String, Object> inquiryResponse = new HashMap<>();

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

            this.inquiryResponse.put("status", false);
            this.inquiryResponse.put("message", xml);

			return ;
        }

        // signature mismatch during inquiry request
        if(!requestdata.get("signature").equals(this.getSignatureBiller())){

            Resp += "<ack>"+ ServicesConfig.ILLEGAL_SIGNATURE_INQ_REQUEST_01 +"</ack>\n";
            Resp += "<bookingid>NULL</bookingid>\n";
            Resp += "<customer_name>NULL</customer_name>\n";
            Resp += "<min_amount>0</min_amount>\n";
            Resp += "<max_amount>0</max_amount>\n";
            Resp += "<productid>NULL</productid>\n";
            Resp += "<signature>" + this.getSignatureBiller() +"</signature>\n";
            System.out.println("signature:"+this.getSignatureBiller());
            String xml = headResp + Resp + footResp;
            
            logArtajasa.setVaNumber(null);
            logArtajasa.setLogAppID(null);
            logArtajasa.setVaRequest(requestdata.toString());
            logArtajasa.setVaResponse(xml);
            logArtajasa.setVaReqDatetime(inqRequestDatetime);
            logArtajasa.setVaRespDatetime(new Date());
            logsArtajasaDAO.save(logArtajasa);

            this.inquiryResponse.put("status", false);
            this.inquiryResponse.put("message", xml);

			return ;
        }

		CustomerVaHistory vaNumberData = customerVaHistoryDAO.findByVANumber(requestdata.get("vaid")).get(0);
        
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
            
            logArtajasa.setVaNumber(requestdata.get("vaid"));
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
            }else if(vaNumberData.getStatus() == 0 && vaNumberData.getIsVaActive().equals("Y")){
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

                this.inquiryResponse.put("status", false);
                this.inquiryResponse.put("message", xml);
            }

        }
    }

    public HashMap<String, Object> getInquiryResponse() {
		return this.inquiryResponse;
    }
    
    public String setAsArtajasaPaid(HashMap<String, String> requestdata) throws Exception {
        Date NotifReqDatetime = new Date();
        String headResp = "<?xml version='1.0'?>\n";
        headResp += "<return>\n";
        String footResp = "</return>\n";
        String Resp = "<type>resnotification</type>\n";
        
        if(requestdata.isEmpty()){
            LogsArtajasa logsArtajasa = new LogsArtajasa();

            Resp += "<ack>05</ack>\n";
            Resp += "<bookingid>" + requestdata.get("bookingid") + "</bookingid>\n";
            Resp += "<signature>" + requestdata.get("signature") +"</signature>\n";
            String xml = headResp + Resp + footResp; 

            System.out.println("Request Data is empty for Artajasa Notify");
            logsArtajasa.setVaNumber(null);
            logsArtajasa.setLogAppID(null);
            logsArtajasa.setNotifyRequest(objectMapper.writeValueAsString(requestdata));
            logsArtajasa.setNotifyReqDatetime(new Date());
            logsArtajasa.setNotifyResponse(xml);
            logsArtajasa.setNotifyRespDatetime(new Date());
            logsArtajasaDAO.save(logsArtajasa);
			return xml;
        }
        VaArtajasa vaArtajasa = vaArtajasaDAO.findValueByColumn("BookingID", requestdata.get("bookingid")).get(0);
        CustomerVaHistory paymentCodeData = customerVaHistoryDAO.findValueByColumn("ID", vaArtajasa.getCvhID().toString()).get(0);
        CustomerVaHistory va = customerVaHistoryDAO.findByVANumber(paymentCodeData.getVaNumber()).get(0);

        if(va == null){
            LogsArtajasa logsArtajasa = new LogsArtajasa();

            Resp += "<ack>76</ack>\n";
            Resp += "<bookingid>" + requestdata.get("bookingid") + "</bookingid>\n";
            Resp += "<signature>" + requestdata.get("signature") +"</signature>\n";
            String xml = headResp + Resp + footResp; 

            logsArtajasa.setVaNumber(null);
            logsArtajasa.setLogAppID(null);
            logsArtajasa.setNotifyRequest(objectMapper.writeValueAsString(requestdata));
            logsArtajasa.setNotifyReqDatetime(new Date());
            logsArtajasa.setNotifyResponse(xml);
            logsArtajasa.setNotifyRespDatetime(new Date());
            logsArtajasaDAO.save(logsArtajasa);
			return xml;

        }

        if(va.getStatus() == 1){
            LogsArtajasa logsArtajasa = new LogsArtajasa();

            Resp += "<ack>78</ack>\n";
            Resp += "<bookingid>" + requestdata.get("bookingid") + "</bookingid>\n";
            Resp += "<signature>" + requestdata.get("signature") +"</signature>\n";
            String xml = headResp + Resp + footResp; 

            logsArtajasa.setVaNumber(null);
            logsArtajasa.setLogAppID(null);
            logsArtajasa.setNotifyRequest(objectMapper.writeValueAsString(requestdata));
            logsArtajasa.setNotifyReqDatetime(new Date());
            logsArtajasa.setNotifyResponse(xml);
            logsArtajasa.setNotifyRespDatetime(new Date());
            logsArtajasaDAO.save(logsArtajasa);
			return xml;            
        }

        if(requestdata.get("type").equals("reqnotification") && requestdata.containsKey("amount")){

            CustomerLoanData cld = custLoanDataDAO.findValueByColumn("ApplicantID", va.getApplicantID()).get(0);
            CustomerPrimaryData borrower = custPrimaryDataDAO.findValueByColumn("ApplicantID", cld.getApplicantID().toString()).get(0);


            ApplicationData apli = applicationDataDAO.findBy("LoanApplicationID", cld.getLoanApplicationID(), "ApplicationApplicantID", cld.getApplicantID().toString()).get(0);

            
            System.out.println(va.getAmountToPay().compareTo(Double.parseDouble(String.valueOf(requestdata.get("amount")))));

            // amount not matched
            if(va.getAmountToPay().compareTo(Double.parseDouble(String.valueOf(requestdata.get("amount")))) != 0){
                LogsArtajasa logsArtajasa = logsArtajasaDAO.findValueByColumn("LogAppID", cld.getLoanApplicationID()).get(0);
              
                Resp += "<ack>13</ack>\n";
                Resp += "<bookingid>" + requestdata.get("bookingid") + "</bookingid>\n";
                Resp += "<signature>" + requestdata.get("signature") +"</signature>\n";
                String xml = headResp + Resp + footResp; 
    
                logsArtajasa.setNotifyRequest(objectMapper.writeValueAsString(requestdata));
                logsArtajasa.setNotifyReqDatetime(new Date());
                logsArtajasa.setNotifyResponse(xml);
                logsArtajasa.setNotifyRespDatetime(new Date());
                logsArtajasaDAO.update(logsArtajasa);
                return xml; 
            }

            if(requestdata.get("type").equals("reqnotification")) va.setStatus(1); 
            va.setPaymentTime(new Date());
            va.setVaTransMerchantID(requestdata.get("bookingid"));
            DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
            va.setNotifiedTime(df.parse(requestdata.get("notification_datetime")));
            customerVaHistoryDAO.update(va);

            CustomerLoanRepayment clr = custLoanRepaymentDAO.findValueByColumn("LoanApplicationID",cld.getLoanApplicationID()).get(0);  
            if(!clr.getRepaymentType().equals("manual_transfer")){
                if(!clr.getVtransactionStatus().equals("Y") && !clr.getClrStatus().equals("Y")){
                    String status = (requestdata.get("type").equals("eqnotification")) ? "Y" : "D";

                    // if(apli.getIsInstallment().equals("Y")){

                    // }else{
                        Integer mctsId = cld.getCusStatusID() == 2 ? 3 : cld.getCusStatusID();
                        String cldStatus = (requestdata.get("type").equals("eqnotification")) ? "Y" : "N";
                        if(clr.getPartialStatus().equals("Y")){
                            // resPartPayday = partialPaymentService.paydayPartialPayment(clr, apli, va, resp, cld);
                        }else{
                            va.setLoanIndex(0);
                            customerVaHistoryDAO.update(va);
                        }

                        cld.setCusStatusID(mctsId);
                        cld.setStatus(cldStatus);
                        custLoanDataDAO.update(cld);

                        clr.setRepaymentType("atm_bersama");
                        clr.setRepaymentDate(new Date());
                        clr.setRepaymentAmount(va.getAmountToPay());
                        // clr.setMcsId(clc.getMcsID);
                        // clr.setMctsId(mctsID);
                        clr.setVtransactionStatus(status);
                        clr.setClrStatus(status);
                        custLoanRepaymentDAO.update(clr);
                    // }

                    // If Repayment Success
	                // if ($requestdata.get("type").equals("reqnotification")) {  

                    Resp += "<ack>13</ack>\n";
                    Resp += "<bookingid>" + requestdata.get("bookingid") + "</bookingid>\n";
                    Resp += "<signature>" + requestdata.get("signature") +"</signature>\n";
                    String xml = headResp + Resp + footResp; 
        
                    LogsArtajasa logsArtajasa = logsArtajasaDAO.findValueByColumn("LogAppID", cld.getLoanApplicationID()).get(0);
                    if(logsArtajasa != null){
                        logsArtajasa.setNotifyRequest(objectMapper.writeValueAsString(requestdata));
                        logsArtajasa.setNotifyReqDatetime(new Date());
                        logsArtajasa.setNotifyResponse(xml);
                        logsArtajasa.setNotifyRespDatetime(new Date());
                        logsArtajasaDAO.update(logsArtajasa);
                        return xml;                         
                    }
                } 
            }
        }
        return "Failed to do artajasa payment";
    }
}

