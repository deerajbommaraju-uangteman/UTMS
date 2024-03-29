package ut.microservices.repaymentmicroservice.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.microservices.repaymentmicroservice.dao.IGenericDAO;
import ut.microservices.repaymentmicroservice.dto.CustomerRepaymentHomePageDTO;
import ut.microservices.repaymentmicroservice.dto.GenerateVaDTO;
import ut.microservices.repaymentmicroservice.models.*;

@Service
@Transactional
public class RepaymentService {

    IGenericDAO<ApplicantData> applicantDataDAO;
    IGenericDAO<ApplicationData> applicationDataDAO;
    IGenericDAO<CustomerVaHistory> customerVaHistoryDAO;
    IGenericDAO<CustomerLoanRepayment> custLoanRepaymentDAO;
    IGenericDAO<CustomerLoanInstallmentRepayment> clirDAO;
    IGenericDAO<LogDokuBca> logDokuBcaDAO;
    IGenericDAO<CustomerLoanData> custLoanDataDAO;
    IGenericDAO<CustomerPrimaryData> custPrimaryDataDAO;
    IGenericDAO<VaArtajasa> vaArtajasaDAO;
    IGenericDAO<LogsArtajasa> logsArtajasaDAO;
    IGenericDAO<CustomerStaticVaActiveLoan> custStaticVaActiveLoanDAO;

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
    public void setLogDokuBcaDAO(IGenericDAO<LogDokuBca> logDokuBcaDAO) {
        this.logDokuBcaDAO = logDokuBcaDAO;
        logDokuBcaDAO.setClazz(LogDokuBca.class);
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
    public void setvaArtajasaDAO(IGenericDAO<VaArtajasa> vaArtajasaDAO) {
        this.vaArtajasaDAO = vaArtajasaDAO;
        vaArtajasaDAO.setClazz(VaArtajasa.class);
    }

    @Autowired
    public void setLogsArtajasaDAO(IGenericDAO<LogsArtajasa> logsArtajasaDAO) {
        this.logsArtajasaDAO = logsArtajasaDAO;
        logsArtajasaDAO.setClazz(LogsArtajasa.class);
    }

    @Autowired
    public void setCustomerStaticVaActiveLoanDAO(IGenericDAO<CustomerStaticVaActiveLoan> custStaticVaActiveLoanDAO) {
        this.custStaticVaActiveLoanDAO = custStaticVaActiveLoanDAO;
        custStaticVaActiveLoanDAO.setClazz(CustomerStaticVaActiveLoan.class);
    }
    
    @Autowired
    private DokuPaymentService dokuPaymentService;

    @Autowired
    private CimbNiagaPaymentService cimbPaymentService;

    @Autowired
    private ArtajasaPaymentService artajasaPaymentService;

    @Autowired
    private ObjectMapper objectMapper;
       
    public CustomerRepaymentHomePageDTO postCustomerLogin(HashMap<String, String> data) throws Exception{
        CustomerLoanRepayment cust=custLoanRepaymentDAO.findValueByColumn("LoanApplicationID",data.get("LoanApplicationID")).get(0);

        ApplicationData apli = applicationDataDAO.findValueByColumn("LoanApplicationID", data.get("LoanApplicationID")).get(0);
        CustomerRepaymentHomePageDTO response=new CustomerRepaymentHomePageDTO();
        if(cust == null){
            response.setMessage("No Active Loans");
        }
        else{
            response.setRepaymentAmount(cust.getRepaymentAmount()); 
            response.setMessage("success");
            SimpleDateFormat dateformatJava = new SimpleDateFormat("dd-MM-yyyy");
            response.setDueDate(dateformatJava.format(apli.getLoanDueDateTime()));
        }
        return response;
    }

	public GenerateVaDTO makeLoanRepayment(HashMap<String, String> userdata) throws Exception {
        GenerateVaDTO result = new GenerateVaDTO();

        CustomerLoanRepayment clr = custLoanRepaymentDAO.findValueByColumn("LoanApplicationID",userdata.get("LoanApplicationID")).get(0);       
        // Map<String , Map<String, Object>> lamsData= this.loanDataFromLAMS(clr.getLoanApplicationID().toString());
        ApplicantData apliData =applicantDataDAO.findValueByColumn("ApplicantID", clr.getApplicantID().toString()).get(0);
        userdata.put("TransactionID", "2131");
        userdata.put("CustomerID", "12");
        // String data = objectMapper.writeValueAsString(lamsData);
        userdata.put("phoneNumber", apliData.getMobileNumber());
        userdata.put("paymentType", userdata.get("paymentType"));
        userdata.put("ApplicantID", clr.getApplicantID());
        userdata.put("AmountToPay", clr.getRepaymentAmount().toString());
        userdata.put("ktp", apliData.getPersonalIDNumber());

		// check for duplicate va existence
		if(this.isDuplicateVa(userdata.get("phoneNumber"))){
            result.setResponse("Duplicate- Va already created!");
			// result.setMessage("pAlert1", "You already have an active Virtual Account number. You can choose another payment method after your Virtual Account number is expired.");
			return result;
		}

        String paymentType = userdata.get("paymentType");
        
        if(paymentType.equals("atm_bersama")){
			//Artajasa Payment
			return this.artajasaChargeStaticVA(userdata);
		}else if(paymentType.equals("doku") || paymentType.equals("doku-bca")){
			// Doku-Alfa and BCA payment
			return this.generateDokuVA(userdata);
		}else if(paymentType.equals("cimb")){
			// CIMB payment
			return this.cimbCharge(userdata);
		}else{
			//Missing paymentType, return failed
            result.setResponse("Failed - Missing payment type");
        }
        return result;
	}

    public boolean isDuplicateVa(String VaNumber) {
        List<CustomerVaHistory> cust=customerVaHistoryDAO.findValueByColumn("VaNumber", VaNumber);
        if(cust.isEmpty())
            return false;

        return true;
    }
    
    // VA GENERATION logic for doku alfa and BCA
	public GenerateVaDTO generateDokuVA(HashMap<String, String> userdata) throws Exception {
        GenerateVaDTO result = new GenerateVaDTO();
        try{
            if(userdata.isEmpty()){
                // log to debug_result - data is empty
                result.setResponse("Failed to generate doku VA - Data is empty");
                return result;
            }

            // Map<String , Map<String, Object>> lamsData= this.loanDataFromLAMS(userdata.get("LoanApplicationID"));
            // $cld = CustomerLoanData::find($data['cld_id']);
            CustomerLoanData cld = custLoanDataDAO.findValueByColumn("LoanApplicationID", userdata.get("LoanApplicationID")).get(0);
            CustomerPrimaryData borrower = custPrimaryDataDAO.findValueByColumn("ApplicantID", cld.getApplicantID().toString()).get(0);
            userdata.put("emailAddress", borrower.getEmailAddress());
            userdata.put("fullName", borrower.getFullName());

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
            va.setApplicantID(userdata.get("ApplicantID"));
            va.setCustomerID(cld.getID());
            va.setVaNumber(response.get("payment_code"));
            va.setAmountToPay(Double.parseDouble(userdata.get("AmountToPay")));
            va.setVaCreatedOutstandingAmt(Double.parseDouble(userdata.get("AmountToPay")));
            va.setVaCreatedTime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
            va.setIsVaActive("Y");
            va.setStatus(0);
            customerVaHistoryDAO.save(va);

            ApplicationData apli = applicationDataDAO.findByTwoColumns("LoanApplicationID", cld.getLoanApplicationID(), "ApplicationApplicantID", cld.getApplicantID().toString()).get(0);

            // Add is_installment logic
            if(apli.getIsInstallment() != null && apli.getIsInstallment().equals("Y")){
                
                if(userdata.containsKey("CustomerLoanInstallmentRepaymentID")){
                    CustomerLoanInstallmentRepayment clir = clirDAO.findValueByColumn("CustomerLoanInstallmentRepaymentID",userdata.get("CustomerLoanInstallmentRepaymentID")).get(0);
                    clir.setRepaymentType(userdata.get("paymentType"));
                    clir.setRepaymentDate(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                    clir.setRepaymentAmount(Double.parseDouble(userdata.get("AmountToPay")));
                    clir.setVtransStatus("P");
                    clir.setStatus("D");
                    clirDAO.update(clir);
                }
            }
                
            CustomerLoanRepayment repay = custLoanRepaymentDAO.findByApplicantId(userdata.get("ApplicantID")).get(0);
            if(repay != null){
                // Update CustomerLoanRepayment columns for the applicant
                repay.setRepaymentType(userdata.get("paymentType"));
                repay.setRepaymentDate(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                repay.setRepaymentAmount(Double.parseDouble(userdata.get("AmountToPay")));
                // "clr_mcs_id" => $clc['clc_mcs_id'],
                // "clr_mcts_id" => $cld['cld_cus_status_id'],                
                repay.setVtransactionStatus("P");
                repay.setClrStatus("D");
                custLoanRepaymentDAO.update(repay);
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
    public HashMap<String, Object> getDokuInquiry(HashMap<String, String> requestdata) throws Exception {
        HashMap<String, Object> response = new HashMap<>();

        if(this.validateBin(requestdata.get("PAYMENTCODE").toString(), dokuPaymentService.getAlfaBin().toString())){
            LogDokuAlfa objLog = new LogDokuAlfa();

            // this.checkVAExistanceAndCreateVA($_POST,dokuPaymentService.getBcaBin(),"doku");			
            // dokuPaymentService.setVAInquiryResponse(requestdata, objLog);
            response = dokuPaymentService.getResponse();    
        }
		else if(this.validateBin(requestdata.get("PAYMENTCODE").toString(), dokuPaymentService.getBcaBin().toString())){
            LogDokuBca objLog = new LogDokuBca();

            // this.checkVAExistanceAndCreateVA($_POST,dokuPaymentService.getBcaBin(),"doku-bca");			
            dokuPaymentService.setVAInquiryResponse(requestdata, objLog);
            response = dokuPaymentService.getResponse();

        }
        else{
            response.put("status", false);
            response.put("message", "doku inquiry failed");
        }
        
		return response;
    }
    
	private boolean validateBin(String paymentCode, String bin) {
        // System.out.println(paymentCode.substring(0, bin.length()).equals(bin) + "len: "+ bin.length());
        return paymentCode.substring(0, bin.length()).equals(bin);
    }

    public String setAsDokuPaid(HashMap<String, String> requestdata) throws Exception {
        CustomerVaHistory va = customerVaHistoryDAO.findByVANumber(requestdata.get("PAYMENTCODE")).get(0);
        LogDokuBca logs = logDokuBcaDAO.findValueByColumn("VaNumber", requestdata.get("PAYMENTCODE").toString()).get(0);
        
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
            logDokuBcaDAO.update(logs);
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
                logDokuBcaDAO.update(logs);
                return "stop - words doesn't match";
            }
        }

        boolean responsecode = requestdata.get("RESPONSECODE").equals("0000") ? true : false;

        if(responsecode && !requestdata.get("AMOUNT").isEmpty() && requestdata.get("RESULTMSG").equals("SUCCESS")){

            System.out.println(va.getAmountToPay().toString());
            // Inside SUCCESS condition

            // if(!requestdata.get("AMOUNT").equals(va.getAmountToPay().toPlainString())){
            //     // Amounts doesn't match
            //     LogDokuBca logs = logDokuBcaDAO.findVAInLogs(requestdata.get("PAYMENTCODE")).get(0);
            //     logs.setNotifyRequest(objectMapper.writeValueAsString(requestdata));
            //     logs.setNotifyResponse("stop");
            //     // int attemptno = logs.getAttemptNumber().intValue();
            //     logs.setAttemptNumber(1);
            //     logDokuBcaDAO.update(logs);
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
                 customerVaHistoryDAO.update(va);
            }     

            CustomerLoanRepayment clr = custLoanRepaymentDAO.findValueByColumn("ApplicantID", va.getApplicantID()).get(0);
            if(!clr.getRepaymentType().equals("manual_transfer")  && !clr.getClrStatus().equals("Y") && !clr.getVtransactionStatus().equals("Y")){
                if(responsecode){
                    clr.setClrStatus("Y");
                    clr.setVtransactionStatus("Y");
                    clr.setRepaymentType("doku-bca");
                    clr.setRepaymentDate(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                    custLoanRepaymentDAO.update(clr);
                    
                }else{
                    clr.setClrStatus("D");
                    clr.setVtransactionStatus("D");
                    custLoanRepaymentDAO.update(clr);

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
                logDokuBcaDAO.update(logs);
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
                logDokuBcaDAO.update(logs);
            }
            return "stop - Amounts doesn't match"; 
        }
    }

    public GenerateVaDTO cimbCharge(HashMap<String, String> userdata) throws Exception {
        GenerateVaDTO result = new GenerateVaDTO();
        try{
            if(userdata.isEmpty()){
                // log to debug_result - data is empty
                result.setResponse("Failed to generate CIMB VA - Data is empty");
                return result;
            }

            CustomerLoanData cld = custLoanDataDAO.findValueByColumn("LoanApplicationID", userdata.get("LoanApplicationID")).get(0);
            // userdata.put("name", cld.getFullName());

            cimbPaymentService.setRequest(userdata);
            HashMap<String, String> response = cimbPaymentService.getRequest();

            String transId= userdata.get("ApplicantID")+'_'+response.get("payment_code");

            // current_datetime = (payment_date!=NULL) ? payment_date :  date('Y-m-d H:i:s'); - for cimb teller
            CustomerVaHistory va = objectMapper.readValue(objectMapper.writeValueAsString(userdata), CustomerVaHistory.class);
            // log to save customer va
            va.setTransactionID(transId);
            va.setCustomerID(cld.getID());
            va.setApplicantID(userdata.get("ApplicantID"));
            va.setVaNumber(response.get("payment_code"));
            va.setAmountToPay(Double.parseDouble(userdata.get("AmountToPay")));
            va.setVaCreatedOutstandingAmt(Double.parseDouble(userdata.get("AmountToPay")));
            va.setVaCreatedTime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
            va.setIsVaActive("Y");
            va.setStatus(0);
            customerVaHistoryDAO.save(va);

            // Add is_installment logic
            // if(($apli->apli_is_installment == "Y"){}
            if(userdata.containsKey("CustomerLoanInstallmentRepaymentID")){
                CustomerLoanInstallmentRepayment clir = clirDAO.findValueByColumn("CustomerLoanInstallmentRepaymentID",userdata.get("CustomerLoanInstallmentRepaymentID")).get(0);
                clir.setRepaymentType(userdata.get("paymentType"));
                clir.setRepaymentDate(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                clir.setRepaymentAmount(Double.parseDouble(userdata.get("AmountToPay")));
                clir.setVtransStatus("P");
                clir.setStatus("D");
                clirDAO.update(clir);
            }

            CustomerLoanRepayment repay = custLoanRepaymentDAO.findByApplicantId(userdata.get("ApplicantID")).get(0);
            if(repay != null){
                // Update CustomerLoanRepayment columns for the applicant
                repay.setRepaymentType(userdata.get("paymentType"));
                repay.setRepaymentDate(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                repay.setRepaymentAmount(Double.parseDouble(userdata.get("AmountToPay")));
                // "clr_mcs_id" => $clc['clc_mcs_id'],
                // "clr_mcts_id" => $cld['cld_cus_status_id'],                
                repay.setVtransactionStatus("P");
                repay.setClrStatus("D");
                custLoanRepaymentDAO.update(repay);
            }
            
            result.setResponse("CIMB VA Created Successfully: " + va.getVaNumber().toString());
            result.setloanAppId(repay.getLoanApplicationID());
            result.setVaNumber(va.getVaNumber().toString());
            result.setRepaymentAmount(va.getVaCreatedOutstandingAmt());

        }catch(Exception e){
            result.setResponse("Failed to create VA");
            e.printStackTrace();
        }
        return result;
    }

    public GenerateVaDTO artajasaChargeStaticVA(HashMap<String, String> userdata) {
        GenerateVaDTO result = new GenerateVaDTO();
        try{
            if(userdata.isEmpty()){
                // log to debug_result - data is empty
                result.setResponse("Failed to generate Artajasa VA - Data is empty");
                return result;
            }
        
            artajasaPaymentService.setStaticVARequest(userdata);
            HashMap<String, String> response = artajasaPaymentService.getStaticVARequest();

            String transId= userdata.get("ApplicantID")+'_'+response.get("payment_code");
            List<CustomerVaHistory> checkVa = customerVaHistoryDAO.findByVANumber(response.get("payment_code").toString());
            
            if(checkVa.isEmpty()){// if there is no VA generated on the day which is active and no payment against this VA
                // log to save customer va
                CustomerVaHistory va = objectMapper.readValue(objectMapper.writeValueAsString(userdata), CustomerVaHistory.class);
                va.setTransactionID(transId);
                // va.setCustomerID(cld.getID());
                va.setVaNumber(response.get("payment_code"));
                va.setAmountToPay(Double.parseDouble(userdata.get("AmountToPay")));
                va.setVaCreatedOutstandingAmt(Double.parseDouble(userdata.get("AmountToPay")));
                va.setVaCreatedTime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                va.setIsVaActive("Y");
                va.setStatus(0);
                customerVaHistoryDAO.save(va);

                // add record to VaArtajasa
                VaArtajasa vaa= new VaArtajasa();
                vaa.setCvhID(va.getID());
                // vaa.setBackupBookingID(ArtajasaPaymentService.getBackupBookingId());
                vaa.setBookingID(response.get("bookingid"));
                vaa.setSignature(response.get("signature"));
                vaArtajasaDAO.save(vaa);

                // Add is_installment logic
                // if(($apli->apli_is_installment == "Y"){}
                if(userdata.containsKey("CustomerLoanInstallmentRepaymentID")){
                    CustomerLoanInstallmentRepayment clir = clirDAO.findValueByColumn("CustomerLoanInstallmentRepaymentID",userdata.get("CustomerLoanInstallmentRepaymentID")).get(0);
                    clir.setRepaymentType(userdata.get("paymentType"));
                    clir.setRepaymentDate(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                    clir.setRepaymentAmount(Double.parseDouble(userdata.get("AmountToPay")));
                    clir.setVtransStatus("P");
                    clir.setStatus("D");
                    clirDAO.update(clir);
                }
                    
                CustomerLoanRepayment repay = custLoanRepaymentDAO.findByApplicantId(userdata.get("ApplicantID")).get(0);
                if(repay != null){
                    // Update CustomerLoanRepayment columns for the applicant
                    repay.setRepaymentType(userdata.get("paymentType"));
                    repay.setRepaymentDate(new java.util.Date(Calendar.getInstance().getTime().getTime()));
                    repay.setRepaymentAmount(Double.parseDouble(userdata.get("AmountToPay")));
                    // "clr_mcs_id" => $clc['clc_mcs_id'],
                    // "clr_mcts_id" => $cld['cld_cus_status_id'],                
                    repay.setVtransactionStatus("P");
                    repay.setClrStatus("D");
                    custLoanRepaymentDAO.update(repay);
                }
    
                result.setResponse("Artajasa VA Created Successfully: " + va.getVaNumber().toString());
                result.setloanAppId(repay.getLoanApplicationID());
                result.setVaNumber(va.getVaNumber().toString());
                result.setRepaymentAmount(va.getVaCreatedOutstandingAmt());
            }    
        }catch(Exception e){
            result.setResponse("Failed to create Artajasa VA");
            e.printStackTrace();
        }
        return result;          
    }
    
    public HashMap<String, Object> getArtajasaInquiry(HashMap<String, String> requestdata) throws Exception {
        HashMap<String, Object> inquiryResp = new HashMap<>();
        // boolean result = this.checkVAExistanceAndCreateVAForArtajasa(requestdata,artajasaPaymentService.getBillerBin(), "atm_bersama");
        LogsArtajasa logArtajasa = new LogsArtajasa();
        if(true){ // result
            
            artajasaPaymentService.setInquiryResponse(requestdata, logArtajasa);
            inquiryResp = artajasaPaymentService.getInquiryResponse();
           
        }
        else{
            String headResp = "<?xml version='1.0'?>\n";
            headResp += "<data>\n";
            String footResp = "</data>\n";
            String Resp = "<type>resinqpayment</type>\n";
            Resp += "<ack>76</ack>\n";
            Resp += "<bookingid>NULL</bookingid>\n";
            Resp += "<customer_name>NULL</customer_name>\n";
            Resp += "<min_amount>0</min_amount>\n";
            Resp += "<max_amount>0</max_amount>\n";
            Resp += "<productid>NULL</productid>\n";
            Resp += "<signature>" + artajasaPaymentService.getSignatureBiller() +"</signature>\n";

            String xml = headResp + Resp + footResp;
            
            inquiryResp.put("status", false);
            inquiryResp.put("message", xml);

            logArtajasa.setVaNumber(null);
            logArtajasa.setLogAppID(null);
            logArtajasa.setVaRequest(requestdata.toString());
            logArtajasa.setVaResponse(xml);
            logArtajasa.setVaReqDatetime(new Date());
            logArtajasa.setVaRespDatetime(new Date());
            logsArtajasaDAO.save(logArtajasa);

            // return ResponseEntity.ok(xml).contentType(MediaType.APPLICATION_XML_VALUE);
            // return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_XML).body(xml);      
        }
        return inquiryResp;
    }

    // public boolean checkVAExistanceAndCreateVAForArtajasa(HashMap<String, Object> rawResponse, String bin, String repaymentType) throws Exception{
    //     boolean isVaGenerated =false;
    //     String mobile_No = rawResponse.get("vaid").toString().substring(bin.length());
    //     CustomerStaticVaActiveLoan va_active_status = custStaticVaActiveLoanDAO.findByVANumber(rawResponse.get("vaid").toString()).get(0);

    //     if(va_active_status == null){
    //         CustomerVaHistory vaNumberData = customerVaHistoryDAO.findByVANumber(rawResponse.get("vaid").toString()).get(0);

    //         if(vaNumberData == null){

    //         }
    //         else{
    //             isVaGenerated = true;
    //         }
            
    //     }
    //     else{
    //         //Insert the loan details to Static VA loan table.

    //     }
    // }

    public String loanDataForReconcile(String VaNumber) throws JsonProcessingException {
        CustomerVaHistory cVaHistory = customerVaHistoryDAO.findValueByColumn("VaNumber", VaNumber).get(0);
        CustomerLoanRepayment custLoanRepayment = custLoanRepaymentDAO.findValueByColumn("ApplicantID", cVaHistory.getApplicantID()).get(0);
        CustomerLoanData custLoanData = custLoanDataDAO.findValueByColumn("ApplicantID",cVaHistory.getApplicantID()).get(0);
        ApplicantData applicantData = applicantDataDAO.findValueByColumn("ApplicantID", custLoanData.getApplicantID().toString()).get(0);
        ApplicationData apliData = applicationDataDAO.findValueByColumn("ApplicationApplicantID", custLoanData.getApplicantID().toString()).get(0);

        HashMap<String, Object> data = new HashMap<>();
        if(apliData.getIsInstallment().equals("Y")){
            CustomerLoanInstallmentRepayment clir = clirDAO.findValueByColumn("CustomerLoanRepaymentID", custLoanRepayment.getId().toString()).get(0);
            data.put("CustomerLoanInstallmentRepayment", clir);
        }
        
        data.put("ApplicationData", apliData);
        data.put("ApplicantData", applicantData);
        data.put("CustomerLoanRepayment", custLoanRepayment);
        data.put("CustomerVaHistory", cVaHistory);
        data.put("CustomerLoanData", custLoanData);
        return objectMapper.writeValueAsString(data);
    }


	public String getLoanDetails(String data) {

		return null;
	}

}