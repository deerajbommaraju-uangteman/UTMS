package ut.microservices.reconcileMicroService.Services;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import ut.microservices.reconcileMicroService.Dao.IGenericDao;
import ut.microservices.reconcileMicroService.Models.DetailModel;
import ut.microservices.reconcileMicroService.Models.ReconcileCustomerLoanData;
import ut.microservices.reconcileMicroService.Models.ReconcileCustomerLoanRepayment;
import ut.microservices.reconcileMicroService.Models.ReconcileCustomerVaHistory;
import ut.microservices.reconcileMicroService.Models.ReconcileDetailDataviewModel;
import ut.microservices.reconcileMicroService.Models.RekapPayment;
import ut.microservices.reconcileMicroService.Models.RekapPaymentDetail;
import ut.microservices.reconcileMicroService.Models.RekapReconcile;
import ut.microservices.reconcileMicroService.Models.RekapReconcilePaymentviewModel;
import ut.microservices.reconcileMicroService.Repository.ReconcileDetailDataviewRepo;
import ut.microservices.reconcileMicroService.Repository.RekapReconcilePaymentviewRepo;

@Service
public class ReconcileService{
  
  @Autowired
  RekapReconcilePaymentviewRepo rpv;

  @Autowired
  ReconcileDetailDataviewRepo rdd;

  IGenericDao<ReconcileDetailDataviewModel> reconcileDetailDataviewModeldao;
  @Autowired
  public void setDaoReconcileDetailDataviewModel(IGenericDao<ReconcileDetailDataviewModel> daoToSet) {
    reconcileDetailDataviewModeldao = daoToSet;
    reconcileDetailDataviewModeldao.setClazz(ReconcileDetailDataviewModel.class);
  }
  
  IGenericDao<RekapReconcile> rekapReconciledao;
  @Autowired
  public void setDaoRekapReconcile(IGenericDao<RekapReconcile> daoToSet) {
    rekapReconciledao = daoToSet;
    rekapReconciledao.setClazz(RekapReconcile.class);
  }

  IGenericDao<DetailModel> detailModeldao;

  @Autowired
  public void setDaoDetailModel(IGenericDao<DetailModel> daoToSet) {
        detailModeldao = daoToSet;
        detailModeldao.setClazz(DetailModel.class);
  }

  IGenericDao<RekapPayment> rekapPaymentdao;
  @Autowired
  public void setDaoRekapPayment(IGenericDao<RekapPayment> daoToSet) {
      rekapPaymentdao = daoToSet;
      rekapPaymentdao.setClazz(RekapPayment.class);
  }

  IGenericDao<RekapPaymentDetail> rekapPaymentDetaildao;
  @Autowired
  public void setDaoRekapPaymentDetail(IGenericDao<RekapPaymentDetail> daoToSet) {
      rekapPaymentDetaildao = daoToSet;
      rekapPaymentDetaildao.setClazz(RekapPaymentDetail.class);
  }

  IGenericDao<ReconcileCustomerLoanRepayment> reconcileCustomerLoanRepaymentdao;
  @Autowired
  public void setDaoCustomerVaHistory(IGenericDao<ReconcileCustomerVaHistory> daoToSet) {
      reconcileCustomerVaHistorydao = daoToSet;
      reconcileCustomerVaHistorydao.setClazz(ReconcileCustomerVaHistory.class);
  }

  IGenericDao<ReconcileCustomerVaHistory> reconcileCustomerVaHistorydao;
  @Autowired
  public void setDaoCustomerLoanRepayment(IGenericDao<ReconcileCustomerLoanRepayment> daoToSet) {
      reconcileCustomerLoanRepaymentdao = daoToSet;
      reconcileCustomerLoanRepaymentdao.setClazz(ReconcileCustomerLoanRepayment.class);
  }

  IGenericDao<ReconcileCustomerLoanData> reconcileCustomerLoanDatadao;
  @Autowired
  public void setDaoCustomerLoanData(IGenericDao<ReconcileCustomerLoanData> daoToSet) {
      reconcileCustomerLoanDatadao = daoToSet;
      reconcileCustomerLoanDatadao.setClazz(ReconcileCustomerLoanData.class);
  }

  @Autowired
  ObjectMapper objectMapper;
    
  public String uploadBCAStatement(MultipartFile file) throws Exception {
      
      BufferedReader br;
      Date now = new Date();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");   
      String settlementID = "BCA" + simpleDateFormat.format(now);
      int countRecords = rekapPaymentdao.findCountByColumn("SettlementID", settlementID);
           
      RekapPayment rp =  rekapPaymentdao.findLatestRecord().get(0);
      int idpayment = rp.getID()+1;

      // System.out.println("Payment Id:"+idpayment );
      // System.out.println("Count records"+countRecords);
      settlementID = (countRecords<10)? settlementID+"0"+(countRecords+1):settlementID+(countRecords+1);
      // System.out.println(settlementID);
      try {
        InputStream is = file.getInputStream();
        br = new BufferedReader(new InputStreamReader(is));
        Double amount =new Double(0);
        br.readLine();
        int count = 0;
        String line = null;
        while ((line = br.readLine()) != null) {
          count++;
          String[] columns = line.split(",");
          Double val=Double.parseDouble(columns[3]);
          amount=Double.sum(amount,val);
          // Map<String , Map<String, Object>> rpymsData= this.loanDataFromRPYMS(columns[6]);
          RekapPaymentDetail rekapPaymentDetail = new RekapPaymentDetail();
          rekapPaymentDetail.setIDOrder(columns[2]);
          rekapPaymentDetail.setAmount(columns[3]);
          rekapPaymentDetail.setIDPayment(idpayment);
          rekapPaymentDetail.setPaymentCode(columns[6]); 
          rekapPaymentDetaildao.save(rekapPaymentDetail);
           
          DetailModel detailModel = new DetailModel();
          detailModel.setAmount(columns[3]);
          detailModel.setIDPayment(idpayment);
          detailModel.setSettlementID(settlementID);
          detailModel.setTotalAmountTransferred(amount);
          detailModel.setVendorFeeID(4);
          detailModeldao.save(detailModel);
        }
          
        String description = "BCA Payment Virtual Account Report: "+settlementID;

        RekapReconcile rekapReconcile = new RekapReconcile();
        rekapReconcile.setCredit(amount);
        rekapReconcile.setDescription(description);
        rekapReconcile.setReceiptDate(now);
        rekapReconcile.setSettlementID(settlementID);
        rekapReconcile.setIsReconcile("N");
        rekapReconciledao.save(rekapReconcile);

        double vendorfee = 2500;
        double adminfee = 0;

        RekapPayment rekapPayment = new RekapPayment();
        rekapPayment.setTotalAmount(amount);
        rekapPayment.setSettlementID(settlementID);
        rekapPayment.setSettlementDate(now);
        rekapPayment.setTotalAmountForMerchant(amount);
        rekapPayment.setTotalAmountTransferred(amount);
        rekapPayment.setVendorFeeID(4);
        rekapPayment.setAdminFee(vendorfee - adminfee);
        rekapPayment.setPaymentFee(vendorfee*count);
        rekapPaymentdao.save(rekapPayment);
      }catch (IOException e) {
        System.err.println(e.getMessage());       
      }
      return "success";
  }


	public String getDetails(String settlementID) throws Exception{
    List<DetailModel> list=detailModeldao.findByColumn("SettlementID", settlementID);
		return objectMapper.writeValueAsString(list);
	}


	public String displayBankStatements() throws Exception{
    ObjectMapper objectMapper = new ObjectMapper();
    Iterable<RekapReconcilePaymentviewModel> usersList = rpv.findAll();
    System.out.println(objectMapper.writeValueAsString(usersList));
    return objectMapper.writeValueAsString(usersList);
  }

  public String detailDataTop(String settlementId) throws Exception{
    ObjectMapper objectMapper = new ObjectMapper();
    // Iterable<ReconcileDetailDataviewModel> usersList = rdd.findAll();

    List<ReconcileDetailDataviewModel> usersList = reconcileDetailDataviewModeldao.findByColumn("SettlementID", settlementId);
    System.out.println(objectMapper.writeValueAsString(usersList));
    return objectMapper.writeValueAsString(usersList);
  }
  
  public Map<String , Map<String, Object>> loanDataFromRPYMS(String VaNumber) throws Exception{
    final String baseUrl = "http://localhost:9093/user/getRepaymentData/"+VaNumber;
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class);
    JsonNode jn1 = objectMapper.readTree(responseEntity.getBody());



    JsonNode jnLoanData = jn1.get("CustomerLoanData");
    ReconcileCustomerLoanData rcld = new ReconcileCustomerLoanData();
    Map<String , Map<String, Object>> result = objectMapper.convertValue(jn1, new TypeReference<Map<String , Map<String, Object>>>(){});
    // rclp.setApplicantID(jnRepaymentData.get("ApplicantID").toString());
    rcld.setCreated(jnLoanData.get("created").toString());
    rcld.setUpdated(jnLoanData.get("updated").toString());
    rcld.setApplicantID(Integer.parseInt(jnLoanData.get("ApplicantID").toString().replace("\"", "")));
    rcld.setPromoCode(jnLoanData.get("PromoCode").toString());
    rcld.setLoanApplicationID(jnLoanData.get("LoanApplicationID").toString());
    rcld.setTrxNo(jnLoanData.get("TrxNo").toString());
    rcld.setLoanAmount(jnLoanData.get("LoanAmount").toString());
    rcld.setLoanDaysLength(jnLoanData.get("LoanDaysLength").toString());
    rcld.setLoanIndex(jnLoanData.get("loanIndex").toString());
    rcld.setLoanStartDatetime(jnLoanData.get("LoanStartDatetime").toString());
    rcld.setLoanDueDatetime(jnLoanData.get("LoanDueDatetime").toString());
    rcld.setLoanCountdownToDue(jnLoanData.get("LoanCountdownToDue").toString());
    rcld.setSendToDc(jnLoanData.get("SendToDc").toString());
    rcld.setLoanInterestRate(jnLoanData.get("LoanInterestRate").toString());
    rcld.setLoanInterestFee(jnLoanData.get("LoanInterestFee").toString());
    rcld.setLoanRepayAmount(jnLoanData.get("LoanRepayAmount").toString());
    rcld.setRepayManual(jnLoanData.get("RepayManual").toString());
    rcld.setStatusRepayManual(jnLoanData.get("StatusRepayManual").toString());
    rcld.setLoanPurpose(jnLoanData.get("LoanPurpose").toString());
    rcld.setLoanSmsForRepay(jnLoanData.get("LoanSmsForRepay").toString());
    rcld.setExtensionDay(jnLoanData.get("ExtensionDay").toString());
    rcld.setExtensionStatus(jnLoanData.get("ExtensionStatus").toString());
    rcld.setCusStatusID(jnLoanData.get("CusStatusID").toString());
    rcld.setStatus(jnLoanData.get("Status").toString());
    rcld.setNote(jnLoanData.get("Note").toString());
    reconcileCustomerLoanDatadao.save(rcld);
    System.out.println("result:"+ result.get("CustomerLoanData"));
    System.out.println("ReconcileCustomerLoanData: "+rcld);
    return result;




    JsonNode jnApplicationData = jn1.get("CustomerVaHistory");
    ReconcileCustomerVaHistory rcvh = new ReconcileCustomerVaHistory();
    Map<String , Map<String, Object>> result = objectMapper.convertValue(jn1, new TypeReference<Map<String , Map<String, Object>>>(){});
    // rclp.setApplicantID(jnRepaymentData.get("ApplicantID").toString());
    rcvh.setVaCreatedOutstandingAmt(jnApplicationData.get("vaCreatedOutstandingAmt").toString());
    rcvh.setVaTransMerchantID(jnApplicationData.get("vaTransMerchantID").toString());
    rcvh.setStatus(jnApplicationData.get("status").toString());
    rcvh.setVaNumber(jnApplicationData.get("vaNumber").toString());
    rcvh.setIsVaActive(jnApplicationData.get("isVaActive").toString());
    rcvh.setVaCreatedTime(jnApplicationData.get("vaCreatedTime").toString());
    rcvh.setPaymentTime(jnApplicationData.get("paymentTime").toString());
    rcvh.setNotifiedTime(jnApplicationData.get("notifiedTime").toString());
    rcvh.setLoanIndex(jnApplicationData.get("loanIndex").toString());
    rcvh.setPartialIndex(jnApplicationData.get("partialIndex").toString());
    rcvh.setPaymentMethod(jnApplicationData.get("paymentMethod").toString());
    rcvh.setTransactionID(jnApplicationData.get("TransactionID").toString());
    rcvh.setCustomerID(jnApplicationData.get("CustomerID").toString());
    rcvh.setApplicantID(jnApplicationData.get("ApplicantID").toString());
    rcvh.setAmountToPay(jnApplicationData.get("AmountToPay").toString());
    
    reconcileCustomerVaHistorydao.save(rcvh);
    System.out.println("result:"+ result.get("CustomerVaHistory"));
    System.out.println("ReconcileCustomerVaHistory: "+rcvh);
    return result;



    // //save to ApplicationData
    // JsonNode jnApplicationData = jn1.get("CustomerVaHistory");
    // ReconcileCustomerVaHistory rcvh = new ReconcileCustomerVaHistory();
    // Map<String , Map<String, Object>> result = objectMapper.convertValue(jn1, new TypeReference<Map<String , Map<String, Object>>>(){});
    // // rclp.setApplicantID(jnRepaymentData.get("ApplicantID").toString());
    // rcvh.setVaCreatedOutstandingAmt(jnApplicationData.get("vaCreatedOutstandingAmt").toString());
    // rcvh.setVaTransMerchantID(jnApplicationData.get("vaTransMerchantID").toString());
    // rcvh.setStatus(jnApplicationData.get("status").toString());
    // rcvh.setVaNumber(jnApplicationData.get("vaNumber").toString());
    // rcvh.setIsVaActive(jnApplicationData.get("isVaActive").toString());
    // rcvh.setVaCreatedTime(jnApplicationData.get("vaCreatedTime").toString());
    // rcvh.setPaymentTime(jnApplicationData.get("paymentTime").toString());
    // rcvh.setNotifiedTime(jnApplicationData.get("notifiedTime").toString());
    // rcvh.setLoanIndex(jnApplicationData.get("loanIndex").toString());
    // rcvh.setPartialIndex(jnApplicationData.get("partialIndex").toString());
    // rcvh.setPaymentMethod(jnApplicationData.get("paymentMethod").toString());
    // rcvh.setTransactionID(jnApplicationData.get("TransactionID").toString());
    // rcvh.setCustomerID(jnApplicationData.get("CustomerID").toString());
    // rcvh.setApplicantID(jnApplicationData.get("ApplicantID").toString());
    // rcvh.setAmountToPay(jnApplicationData.get("AmountToPay").toString());
    
    // reconcileCustomerVaHistorydao.save(rcvh);
    // System.out.println("result:"+ result.get("CustomerVaHistory"));
    // System.out.println("ReconcileCustomerVaHistory: "+rcvh);
    // return result;



    JsonNode jnRepaymentData = jn1.get("CustomerLoanRepayment");
    // ReconcileCustomerLoanRepayment rclp = objectMapper.convertValue(jnRepaymentData, ReconcileCustomerLoanRepayment.class);
    
    ReconcileCustomerLoanRepayment rclp = new ReconcileCustomerLoanRepayment();
    Map<String , Map<String, Object>> result = objectMapper.convertValue(jn1, new TypeReference<Map<String , Map<String, Object>>>(){});
    // rclp.setApplicantID(jnRepaymentData.get("ApplicantID").toString());
    rclp.setLoanApplicationID(jnRepaymentData.get("LoanApplicationID").toString());
    rclp.setVtransactionStatus(jnRepaymentData.get("vtransactionStatus").toString());
    rclp.setPartialStatus(jnRepaymentData.get("partialStatus").toString());
    rclp.setRepaymentFromBankName(jnRepaymentData.get("repaymentFromBankName").toString());
    rclp.setRepaymentFromBankAccount(jnRepaymentData.get("repaymentFromBankAccount").toString());
    rclp.setJariBulkStatusPaid(jnRepaymentData.get("jariBulkStatusPaid").toString());
    rclp.setRepaymentToBankId(jnRepaymentData.get("repaymentToBankId").toString());
    rclp.setClr2c2pStatus(jnRepaymentData.get("clr2c2pStatus").toString());
    rclp.setReffCode(jnRepaymentData.get("reffCode").toString());
    rclp.setClr2c2pFrom(jnRepaymentData.get("clr2c2pFrom").toString());
    rclp.setApplicantID(jnRepaymentData.get("ApplicantID").toString());
    rclp.setDueDate(jnRepaymentData.get("DueDate").toString());
    rclp.setRepaymentDate(jnRepaymentData.get("RepaymentDate").toString());
    rclp.setRepaymentAmount(jnRepaymentData.get("RepaymentAmount").toString());
    rclp.setRepaymentRemind(jnRepaymentData.get("RepaymentRemind").toString());
    rclp.setSmsRepaymentConfirmation(jnRepaymentData.get("SmsRepaymentConfirmation").toString());
    rclp.setRepaymentType(jnRepaymentData.get("RepaymentType").toString());
    rclp.setMcsId(jnRepaymentData.get("McsId").toString());
    rclp.setMctsId(jnRepaymentData.get("McsId").toString());
    reconcileCustomerLoanRepaymentdao.save(rclp);
    // ApplicationData apliData = objectMapper.convertValue(jnApplicationData, ApplicationData.class);
    // applicationDataDao.save(apliData);
    
    System.out.println("result:"+ result.get("CustomerLoanRepayment"));
    System.out.println("ReconcileCustomerLoanRepayment: "+rclp);
    // ReconcileCustomerLoanRepayment rclp1 = new ReconcileCustomerLoanRepayment();
    // rclp1.setApplicantID(jnApplicantData.get("ApplicantID").toString());
    // reconcileCustomerLoanRepaymentdao.save(rclp);
    return result;
  } 
  
    public boolean updateReconcileStatus(String settlementId){
      System.out.println("Entered Reconcile Process");
    ReconcileDetailDataviewModel rddvm = reconcileDetailDataviewModeldao.findByColumn("SettlementID", settlementId).get(0);
    System.out.println(rddvm);
    if (rddvm.getCredit().equals(rddvm.getTotalRepayment())){

      RekapReconcile rr = rekapReconciledao.findByColumn("SettlementID", settlementId).get(0);
      rr.setIsReconcile("Y");
      rekapReconciledao.save(rr);

      System.out.println("save completed");

    }


    return true;
  }

}