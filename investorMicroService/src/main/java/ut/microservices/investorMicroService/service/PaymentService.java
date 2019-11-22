package ut.microservices.investormicroservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.transaction.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import ut.microservices.investormicroservice.model.InvestorFundingHistory;
import ut.microservices.investormicroservice.model.InvestorVAHistory;
import ut.microservices.investormicroservice.model.LoanInvestment;
import ut.microservices.investormicroservice.repository.IGenericDAO;

@Service
@Transactional
public class PaymentService {

  IGenericDAO<LoanInvestment> loanInvestmentDAO;
  IGenericDAO<InvestorVAHistory> investorVAHistoryDAO;
  IGenericDAO<InvestorFundingHistory> investorFundingHistoryDAO;

  @Autowired
  KafkaTemplate kafkaTemplate;

  @Autowired
  DatabaseService databaseService;

  @Autowired
  public void setinvestorFundingHistoryDAO(IGenericDAO<InvestorFundingHistory> investorFundingHistoryDAO) {
    this.investorFundingHistoryDAO = investorFundingHistoryDAO;
    this.investorFundingHistoryDAO.setClazz(InvestorFundingHistory.class);
  }

  @Autowired
  public void setInvestorVAHistoryDAO(IGenericDAO<InvestorVAHistory> investorVAHistoryDAO) {
    this.investorVAHistoryDAO = investorVAHistoryDAO;
    this.investorVAHistoryDAO.setClazz(InvestorVAHistory.class);
  }

  @Autowired
  public void setLoanInvestmentDAO(IGenericDAO<LoanInvestment> loanInvestmentDAO) {
    this.loanInvestmentDAO = loanInvestmentDAO;
    this.loanInvestmentDAO.setClazz(LoanInvestment.class);
  }

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  DigisignService digisignService;

  public void receiptUploaded(String param) throws Exception {
    HashMap<String, String> data = objectMapper.readValue(param, HashMap.class);
    String vaNumber = data.get("vaNumber");
    int investorID = 1;
    // True represents payment is Done
    if (checkPaymentStatus(vaNumber)) {
      ArrayList<InvestorVAHistory> investorVAHistoryList = (ArrayList<InvestorVAHistory>) investorVAHistoryDAO
          .findBy("vaNumber", vaNumber);
      Iterator<InvestorVAHistory> iterator = investorVAHistoryList.iterator();
      while (iterator.hasNext()) {
        InvestorVAHistory investorVAHistory = iterator.next();
        String loanAppID = investorVAHistory.getLoanAppID();
        investorVAHistory.setStatus(1);
        investorVAHistoryDAO.update(investorVAHistory);
        LoanInvestment loanInvestment = loanInvestmentDAO.findBy("loanAppID", loanAppID).get(0);
        loanInvestment.setState("P");
        loanInvestmentDAO.update(loanInvestment);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("loanAppID", loanAppID);
        map.put("investorID", investorID);
        kafkaTemplate.send("sendDocument", objectMapper.writeValueAsString(map));
      }
    } else {
      // TODO
      // Need to return Payment Pending status
    }
  }

  @KafkaListener(topics = "sendDocument")
  public void sendDocument(String param) throws Exception {
    HashMap<String, Object> data = objectMapper.readValue(param, HashMap.class);
    String loanAppID = (String) data.get("loanAppID");
    int investorID = (int) data.get("investorID");
    if (digisignService.sendDocuments(loanAppID, investorID)) {
      InvestorVAHistory investorVAHistory = investorVAHistoryDAO.findBy("loanAppID", loanAppID).get(0);
      Integer applicationID = investorVAHistory.getApplicationID();
      databaseService.insertRecordToDigisignAgreement(applicationID, investorID, loanAppID);
    }
  }

  public boolean checkPaymentStatus(String vaNumber) {
    InvestorFundingHistory investorFundingHistory = investorFundingHistoryDAO.findBy("investorVaNumber", vaNumber)
        .get(0);
    if (investorFundingHistory.getTxnStatus() == 1) {
      return true;
    } else {
      // TODO
      // Need to change to false in production
      return true;
    }

  }

  public String paymentReceivedFromBank(String param) throws Exception {
    HashMap<String,String> data= objectMapper.readValue(param, HashMap.class);
    String vaNumber=data.get("vaNumber");
    InvestorFundingHistory investorFundingHistory=investorFundingHistoryDAO.findBy("investorVaNumber",vaNumber).get(0);
    investorFundingHistory.setTxnStatus(1);
    investorFundingHistoryDAO.update(investorFundingHistory);
    //TODO
    // Need to return Success status
      return null;
    }
}