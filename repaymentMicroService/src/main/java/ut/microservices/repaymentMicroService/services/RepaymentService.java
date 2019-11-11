package ut.microservices.repaymentMicroService.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import ut.microservices.repaymentMicroService.dao.IGenericDao;
import ut.microservices.repaymentMicroService.dto.CustomerRepaymentHomePageDto;
import ut.microservices.repaymentMicroService.dto.GenerateVaDto;
import ut.microservices.repaymentMicroService.models.ApplicantData;
import ut.microservices.repaymentMicroService.models.ApplicationData;
import ut.microservices.repaymentMicroService.models.CustomerLoanData;
import ut.microservices.repaymentMicroService.models.CustomerLoanInstallmentRepayment;
import ut.microservices.repaymentMicroService.models.CustomerLoanRepayment;
import ut.microservices.repaymentMicroService.models.CustomerPrimaryData;
import ut.microservices.repaymentMicroService.models.CustomerVaHistory;
import ut.microservices.repaymentMicroService.models.LogDokuBca;

@Service
@Transactional
public class RepaymentService {

    IGenericDao<ApplicantData> applicantDataDao;
    IGenericDao<ApplicationData> applicationDataDao;
    IGenericDao<CustomerVaHistory> customerVaHistoryDao;
    IGenericDao<CustomerLoanRepayment> custLoanRepaymentDao;
    IGenericDao<CustomerLoanInstallmentRepayment> clirDao;
    IGenericDao<LogDokuBca> LogDokuBcaDao;
    IGenericDao<CustomerLoanData> custLoanDataDao;
    IGenericDao<CustomerPrimaryData> custPrimaryDataDao;

    @Autowired
    public void setApplicantDataDao(IGenericDao<ApplicantData> daoToSet){
        applicantDataDao = daoToSet;
        applicantDataDao.setClazz(ApplicantData.class);
    }

    @Autowired
    public void setApplicationDataDao(IGenericDao<ApplicationData> daoToSet){
        applicationDataDao = daoToSet;
        applicationDataDao.setClazz(ApplicationData.class);
    }

    @Autowired
    public void setCustomerVaHistoryDao(IGenericDao<CustomerVaHistory> daoToSet) {
        customerVaHistoryDao = daoToSet;
        customerVaHistoryDao.setClazz(CustomerVaHistory.class);
    }

    @Autowired
    public void setCustLoanRepaymentDao(IGenericDao<CustomerLoanRepayment> daoToSet) {
        custLoanRepaymentDao = daoToSet;
        custLoanRepaymentDao.setClazz(CustomerLoanRepayment.class);
    }

    @Autowired
    public void setCustLoanInstallmentDao(IGenericDao<CustomerLoanInstallmentRepayment> daoToSet) {
        clirDao = daoToSet;
        clirDao.setClazz(CustomerLoanInstallmentRepayment.class);
    }

    @Autowired
    public void setLogDokuBcaDao(IGenericDao<LogDokuBca> daoToSet) {
        LogDokuBcaDao = daoToSet;
        LogDokuBcaDao.setClazz(LogDokuBca.class);
    }

    @Autowired
    public void setCustLoanDataDao(IGenericDao<CustomerLoanData> daoToSet) {
        custLoanDataDao = daoToSet;
        custLoanDataDao.setClazz(CustomerLoanData.class);
    }

    @Autowired
    public void setCustPrimaryDataDao(IGenericDao<CustomerPrimaryData> daoToSet) {
        custPrimaryDataDao = daoToSet;
        custPrimaryDataDao.setClazz(CustomerPrimaryData.class);
    }

    @Autowired
    private DokuPaymentService dokuPaymentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @RequestMapping(value = "/loanDisbursed", method = RequestMethod.POST)
    public void loanDataToRepayment(Integer ApplicantID, String ApplicationID, String LoanApplicationID,Double Amount) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("ApplicantID", ApplicantID);
        map.put("ApplicationID", ApplicationID);
        map.put("LoanApplicationID", LoanApplicationID);
        map.put("RepaymentAmount", Amount);
        // Send data to repayment micro service
        kafkaTemplate.send("loanDisbursed", objectMapper.writeValueAsString(map));
    }

    @KafkaListener(topics = "loanDisbursed", groupId = "myGroupId")
    public void loanDisbursedData(ConsumerRecord<String, String> data, Acknowledgment ack) throws Exception{  
        HashMap<String, String>  dataMap= new ObjectMapper().readValue(data.value(), new TypeReference<HashMap<String,String>>() {});  
        CustomerLoanRepayment clr = new CustomerLoanRepayment();
        clr.setApplicantID(dataMap.get("ApplicantID").toString());
        clr.setLoanApplicationID(dataMap.get("LoanApplicationID").toString());
        clr.setRepaymentAmount(Double.parseDouble(dataMap.get("RepaymentAmount").toString()));
        clr.setPartialStatus("N");
        custLoanRepaymentDao.save(clr);

        this.postDisbusementSaveDetails(dataMap.get("LoanApplicationID").toString());
        ack.acknowledge();
    }

    public Map<String , Map<String, Object>> loanDataFromLAMS(String LoanApplicationID) throws Exception{
        final String baseUrl = "http://localhost:9090/application-form/getApplicationData/"+LoanApplicationID;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class);
        JsonNode jn1 = objectMapper.readTree(responseEntity.getBody());

        //save to ApplicationData
        JsonNode jnApplicationData = jn1.get("ApplicationData");
        JsonNode jnApplicantData = jn1.get("ApplicantData");
        // ApplicationData apliData = objectMapper.convertValue(jnApplicationData, ApplicationData.class);
        // applicationDataDao.save(apliData);
        Map<String , Map<String, Object>> result = objectMapper.convertValue(jn1, new TypeReference<Map<String , Map<String, Object>>>(){});
        System.out.println("result"+ result.get("ApplicantData"));
        return result;
    }
       
    public CustomerRepaymentHomePageDto postCustomerLogin(HashMap<String, String> data) throws Exception{
        CustomerLoanRepayment cust=custLoanRepaymentDao.findValueByColumn("LoanApplicationID",data.get("LoanApplicationID")).get(0);       
        CustomerRepaymentHomePageDto response=new CustomerRepaymentHomePageDto();
        if(cust == null){
            response.setMessage("No Active Loans");
        }
        else{
            response.setRepaymentAmount(cust.getRepaymentAmount()); 
            response.setMessage("success");
            SimpleDateFormat dateformatJava = new SimpleDateFormat("dd-MM-yyyy");
            response.setDueDate(dateformatJava.format(cust.getDueDate()));
        }
        return response;
    }

    public void postDisbusementSaveDetails(String LoanApplicationID) throws Exception {

        Map<String , Map<String, Object>> lamsData= this.loanDataFromLAMS(LoanApplicationID);

        // save details to CustomerLoanData
        CustomerLoanData cld = new CustomerLoanData();
        cld.setApplicantID(Integer.parseInt(lamsData.get("ApplicationData").get("applicationApplicantID").toString()));
        cld.setLoanApplicationID(lamsData.get("ApplicationData").get("LoanApplicationID").toString());
        cld.setLoanDaysLength((Integer) lamsData.get("ApplicationData").get("LoanDaysLength"));
        cld.setLoanStartDatetime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
        // cld.setLoanDueDatetime((Date) lamsData.get("ApplicationData").get("LoanDueDateTime"));
        cld.setLoanRepayAmount((Double) lamsData.get("ApplicationData").get("LoanAmount"));
        cld.setCreated(new java.util.Date(Calendar.getInstance().getTime().getTime()));
        cld.setCusStatusID(2);
        cld.setStatus("N");
        custLoanDataDao.save(cld);

        // save details to CustomerPrimaryData
        // CustomerPrimaryData  cd= new CustomerPrimaryData();
        // cd.setApplicantID((int) lamsData.get("ApplicantData").get("applicantID"));
        // cd.setFullName(lamsData.get("ApplicantData").get("Fullname").toString());
        // cd.setGender(lamsData.get("ApplicantData").get("Gender").toString());
        // cd.setEmailAddress(lamsData.get("ApplicantData").get("EmailAddress").toString());
        // cd.setPassword("testing");
        // cd.setPersonalIDNumber(lamsData.get("ApplicantData").get("PersonalIDNumber").toString());
        // cd.setMobileNumber(lamsData.get("ApplicantData").get("MobileNumber").toString());
        // cd.setDomicileCountry(102);
        // cd.setFamily1Country(102);
        // cd.setEmployerCountry(102);
        // custPrimaryDataDao.save(cd);
    }


	public GenerateVaDto makeLoanRepayment(HashMap<String, String> userdata) throws Exception {
        GenerateVaDto result = new GenerateVaDto();

        CustomerLoanRepayment clr = custLoanRepaymentDao.findValueByColumn("LoanApplicationID",userdata.get("LoanApplicationID")).get(0);       
        Map<String , Map<String, Object>> lamsData= this.loanDataFromLAMS(clr.getLoanApplicationID().toString());
        // ApplicantData apliData =applicantDataDao.findValueByColumn("ApplicantID", clr.getApplicantID().toString()).get(0);
        userdata.put("TransactionID", "2131");
        userdata.put("CustomerID", "12");
        // String data = objectMapper.writeValueAsString(lamsData);
        userdata.put("phoneNumber", lamsData.get("ApplicantData").get("MobileNumber").toString());
        userdata.put("paymentType", "doku-bca");
        userdata.put("ApplicantID", clr.getApplicantID());
        userdata.put("AmountToPay", clr.getRepaymentAmount().toString());

		// check for duplicate va existence
		if(this.isDuplicateVa(userdata.get("phoneNumber"))){
			result.setResponse("duplicate");
			// result.setMessage("pAlert1", "You already have an active Virtual Account number. You can choose another payment method after your Virtual Account number is expired.");
			return result;
		}

        String paymentType = userdata.get("paymentType");
        
        if(paymentType.equals("atm_bersama")){
			//Artajasa Payment
			if(this.artajasaChargeStaticVA(userdata)){
				result.setResponse("success");
			}
		}else if(paymentType.equals("doku") || paymentType.equals("doku-bca")){
			// Doku-Alfa and BCA payment
			return this.generateDokuVA(userdata);
		}else if(paymentType.equals("cimb")){
			// CIMB payment
			if(this.cimbCharge(userdata)){
                result.setResponse("success");
            }
		}else{
			//Missing paymentType, return failed
            result.setResponse("Failed - Missing payment type");
        }
        return result;
	}

    public boolean isDuplicateVa(String VaNumber) {
        List<CustomerVaHistory> cust=customerVaHistoryDao.findValueByColumn("VaNumber", VaNumber);
        if(cust.isEmpty())
            return false;

        return true;
    }
    
    // VA GENERATION logic for doku alfa and BCA
	public GenerateVaDto generateDokuVA(HashMap<String, String> userdata) throws Exception {
        GenerateVaDto result = new GenerateVaDto();
        try{
            if(userdata.isEmpty()){
                // log to debug_result - data is empty
                result.setResponse("Failed to generate VA - Data is empty");
                return result;
            }

            // Map<String , Map<String, Object>> lamsData= this.loanDataFromLAMS(userdata.get("LoanApplicationID"));
            // $cld = CustomerLoanData::find($data['cld_id']);
            CustomerLoanData cld = custLoanDataDao.findValueByColumn("LoanApplicationID", userdata.get("LoanApplicationID")).get(0);
            // CustomerPrimaryData borrower = custPrimaryDataDao.findValueByColumn("ApplicantID", cld.getApplicantID().toString()).get(0);
            // userdata.put("emailAddress", borrower.getEmailAddress());
            // userdata.put("fullName", borrower.getFullName());

            String paymentType = userdata.get("paymentType").toUpperCase();    

            if(paymentType.equals("DOKU")){
                // log- generate va for doku
                dokuPaymentService.setRequest(userdata);
            }
            else if(paymentType.equals("DOKU-BCA")){
                // generate va for doku-bca
                dokuPaymentService.setBCARequest(userdata);
            }

            // log for- get BCA request
            HashMap<String, String> response = dokuPaymentService.getRequest();

            String transId= userdata.get("ApplicantID")+'_'+response.get("payment_code");
            CustomerVaHistory va = objectMapper.readValue(objectMapper.writeValueAsString(userdata), CustomerVaHistory.class);
            // log to save customer va
            va.setTransactionID(transId);
            // va.setCustomerID(cld.getID());
            va.setVaNumber(response.get("payment_code"));
            va.setAmountToPay(Double.parseDouble(userdata.get("AmountToPay")));
            va.setVaCreatedOutstandingAmt(Double.parseDouble(userdata.get("AmountToPay")));
            va.setVaCreatedTime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
            va.setIsVaActive("Y");
            va.setStatus(0);
            customerVaHistoryDao.save(va);

            // Add is_installment logic
            // if(($apli->apli_is_installment == "Y"){}
            if(userdata.containsKey("CustomerLoanInstallmentRepaymentID")){
                CustomerLoanInstallmentRepayment clir = clirDao.findValueByColumn("CustomerLoanInstallmentRepaymentID",userdata.get("CustomerLoanInstallmentRepaymentID")).get(0);
                clir.setRepaymentType(userdata.get("paymentType"));
                clir.setRepaymentDate(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                clir.setRepaymentAmount(Double.parseDouble(userdata.get("AmountToPay")));
                clir.setVtransStatus("P");
                clir.setStatus("D");
                clirDao.update(clir);
            }
                
            CustomerLoanRepayment repay = custLoanRepaymentDao.findByApplicantId(userdata.get("ApplicantID")).get(0);
            if(repay != null){
                // Update CustomerLoanRepayment columns for the applicant
                repay.setRepaymentType(userdata.get("paymentType"));
                repay.setDueDate(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                repay.setRepaymentAmount(Double.parseDouble(userdata.get("AmountToPay")));
                // "clr_mcs_id" => $clc['clc_mcs_id'],
                // "clr_mcts_id" => $cld['cld_cus_status_id'],                
                repay.setVtransactionStatus("P");
                repay.setClrStatus("D");
                custLoanRepaymentDao.update(repay);
            }

            
            result.setResponse("VA Created Successfully: " + va.getVaNumber().toString());
            result.setloanAppId(repay.getLoanApplicationID());
            result.setVaNumber(va.getVaNumber().toString());
            result.setRepaymentAmount(va.getVaCreatedOutstandingAmt());
        }catch(Exception e){
            result.setResponse("Failed to create VA");
            e.printStackTrace();
            
        }

        return result;
    }

    // Doku Alfa and BCA Inquiry data
    public String getDokuInquiry(HashMap<String, String> requestdata) throws Exception {
        // String inquiryResp[];
		if(this.validateBin(requestdata.get("PAYMENTCODE").toString(), dokuPaymentService.getBcaBin().toString())){
            LogDokuBca objLog = new LogDokuBca();

            // this.checkVAExistanceAndCreateVA($_POST,dokuPaymentService.getBcaBin(),"doku-bca");			
            dokuPaymentService.setVAInquiryResponse(requestdata, objLog);
            return dokuPaymentService.getResponse();
		}
		return "In doku inquiry";
    }
    
	private boolean validateBin(String paymentCode, String bin) {
        if(paymentCode.substring(0, bin.length()+1).equals(bin)){
            return true;
        }
        return true;
    }

    public String setAsDokuPaid(HashMap<String, String> requestdata) throws Exception {
        CustomerVaHistory va = customerVaHistoryDao.findByVANumber(requestdata.get("PAYMENTCODE")).get(0);
        LogDokuBca logs = LogDokuBcaDao.findValueByColumn("VaNumber", requestdata.get("PAYMENTCODE").toString()).get(0);
        
        // request data is empty or VA is not found
        if(requestdata.isEmpty() || va == null){
            System.out.println("Request Data is empty for Notify");
            logs.setVaNumber(null);
            logs.setLogAppID(null);
            logs.setNotifyRequest(objectMapper.writeValueAsString(requestdata));
            logs.setNotifyReqDatetime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
            logs.setNotifyResponse("stop");
            logs.setNotifyRespDatetime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
            // objLog.setInquiryReqDatetime(currentdate.format(d));
            LogDokuBcaDao.update(logs);
			return "stop - Data not found ";
        }

        //Compare the WORDS
        String ourWords = dokuPaymentService.getBcaMallId() + dokuPaymentService.SHARED_KEY + requestdata.get("RESULTMSG") + requestdata.get("VERIFYSTATUS");
        
        if(!requestdata.get("WORDS").equals(ourWords)){
            //words doesn't match, update log response as stop
            if(this.validateBin(requestdata.get("PAYMENTCODE").toString(), dokuPaymentService.getBcaBin().toString())){

                logs.setNotifyRequest(objectMapper.writeValueAsString(requestdata));
                logs.setNotifyReqDatetime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                logs.setNotifyResponse("stop");
                logs.setNotifyRespDatetime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                // int attemptno = logs.getAttemptNumber().intValue();
                logs.setAttemptNumber(1);
                LogDokuBcaDao.update(logs);
                return "stop - words doesn't match";
            }
        }

        boolean responsecode = requestdata.get("RESPONSECODE").equals("0000") ? true : false;

        if(responsecode && !requestdata.get("AMOUNT").isEmpty() && requestdata.get("RESULTMSG").equals("SUCCESS")){

            System.out.println(va.getAmountToPay().toString());
            // Inside SUCCESS condition

            // if(!requestdata.get("AMOUNT").equals(va.getAmountToPay().toPlainString())){
            //     // Amounts doesn't match
            //     LogDokuBca logs = LogDokuBcaDao.findVAInLogs(requestdata.get("PAYMENTCODE")).get(0);
            //     logs.setNotifyRequest(objectMapper.writeValueAsString(requestdata));
            //     logs.setNotifyResponse("stop");
            //     // int attemptno = logs.getAttemptNumber().intValue();
            //     logs.setAttemptNumber(1);
            //     LogDokuBcaDao.update(logs);
            // return "stop - amounts doesn't match";
            // }

            if(this.validateBin(requestdata.get("PAYMENTCODE").toString(), String.valueOf(dokuPaymentService.getBcaBin().toString()))) {
                va.setVaTransMerchantID(requestdata.get("APPROVALCODE"));
            }

            if(responsecode){
                 va.setStatus(1);
                 va.setIsVaActive("N");
                 va.setPaymentTime(new java.util.Date(Calendar.getInstance().getTime().getTime())); //  va.setPaymentTime((Date)requestdata.get("PAYMENTDATETIME"));
                 va.setNotifiedTime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                 customerVaHistoryDao.update(va);
            }     

            CustomerLoanRepayment clr = custLoanRepaymentDao.findValueByColumn("ApplicantID", va.getApplicantID()).get(0);
            if(!clr.getRepaymentType().equals("manual_transfer")  && !clr.getClrStatus().equals("Y") && !clr.getVtransactionStatus().equals("Y")){
                if(responsecode){
                    clr.setClrStatus("Y");
                    clr.setVtransactionStatus("Y");
                    clr.setRepaymentType("doku-bca");
                    clr.setRepaymentDate(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                    custLoanRepaymentDao.update(clr);
                    
                }else{
                    clr.setClrStatus("D");
                    clr.setVtransactionStatus("D");
                    custLoanRepaymentDao.update(clr);

                }
                // if($apli->apli_is_installment =="Y") conditions

                // Send repayment data to reconcile micro service
                // this.loanDataToReconcile(clr.getLoanApplicationID(), va.getVaNumber().toString());
            }

            // update log response as continue
            if(this.validateBin(requestdata.get("PAYMENTCODE").toString(), dokuPaymentService.getBcaBin().toString())){
                logs.setNotifyRequest(objectMapper.writeValueAsString(requestdata));
                logs.setNotifyRespDatetime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                logs.setNotifyResponse("continue");
                logs.setNotifyRespDatetime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                // int attemptno = logs.getAttemptNumber().intValue();
                logs.setAttemptNumber(1);
                LogDokuBcaDao.update(logs);
            }
            return "continue";
    
            // update mapped loan investment table   
            // Set Banned When Customer  overdue , Payment:  Doku Bca 
        }else{
            // update log response as stop
            if(this.validateBin(requestdata.get("PAYMENTCODE").toString(), dokuPaymentService.getBcaBin().toString())){
                logs.setNotifyRequest(objectMapper.writeValueAsString(requestdata));
                logs.setNotifyRespDatetime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                logs.setNotifyResponse("stop unsuccessful payment");
                logs.setNotifyRespDatetime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                // int attemptno = logs.getAttemptNumber().intValue();
                logs.setAttemptNumber(1);
                LogDokuBcaDao.update(logs);
            }
            return "stop - Amounts doesn't match"; 
        }
    }



    public String loanDataForReconcile(String VaNumber) throws Exception {
        CustomerVaHistory cVaHistory = customerVaHistoryDao.findValueByColumn("VaNumber", VaNumber).get(0);
        CustomerLoanRepayment custLoanRepayment = custLoanRepaymentDao.findValueByColumn("ApplicantID", cVaHistory.getApplicantID()).get(0);
        HashMap<String, Object> data = new HashMap<>();
        data.put("CustomerLoanRepayment", custLoanRepayment);
        data.put("CustomerVaHistory", cVaHistory);
        return objectMapper.writeValueAsString(data);
    }

    public boolean artajasaChargeStaticVA(HashMap<String, String> userdata) {
		return false;
	}

	public boolean cimbCharge(HashMap<String, String> userdata) throws Exception {
        return false;
    }

    
	public String getLoanDetails(String data) {

		return null;
	}



}