package ut.microservices.investormicroservice.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import ut.microservices.investormicroservice.dto.AvailableLoansDTO;
import ut.microservices.investormicroservice.dto.LoansDTO;
import ut.microservices.investormicroservice.dto.ResponseDTO;
import ut.microservices.investormicroservice.model.InvestorFundingHistory;
import ut.microservices.investormicroservice.model.InvestorVAHistory;
import ut.microservices.investormicroservice.model.LoanInvestment;
import ut.microservices.investormicroservice.repository.IGenericDAO;

@Service
@Transactional 
public class InvestorDashboardService {

  IGenericDAO<LoanInvestment> loanInvestmentDAO;
  IGenericDAO<InvestorFundingHistory> investorFundingHistoryDAO;
  IGenericDAO<InvestorVAHistory> investorVAHistoryDAO;

  @Autowired
   KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  ResponseBodyService responseBodyService;

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

  public ResponseDTO<LoansDTO> getAllLoans() {
    List<LoanInvestment> fundingLoansList = loanInvestmentDAO.findAll();
    List<LoanInvestment> loansList = sortWithInvestorPreference(fundingLoansList);
    return responseBodyService.getLoanResponseBody(loansList);
  }

  public @ResponseBody ResponseDTO<AvailableLoansDTO> getAvailableLoans() throws Exception {
    List<LoanInvestment> fundingLoansList = loanInvestmentDAO.findBy("State","N");
    List<LoanInvestment> loansList = sortWithInvestorPreference(fundingLoansList);
    return responseBodyService.getAvailableLoanResponseBody(loansList);
  }

  private List<LoanInvestment> sortWithInvestorPreference(List<LoanInvestment> fundingLoansList) {
    //TODO
    //Short list the loans according to Investor Preferences
    return fundingLoansList;
  }

  public void fundLoan(String loanAppID) {
    //True indicates payment is not done
    if(checkFundingStatus()){
      LoanInvestment loanInvestment=loanInvestmentDAO.findBy("loanAppID",loanAppID).get(0);
      loanInvestment.setState("W");
      loanInvestmentDAO.update(loanInvestment);
    }
    else{
      //TODO
      //Please complete previous funding
    }    
  }

  public boolean checkFundingStatus() {
    //For now InvestorID is static
    int investorID=1;
    List<InvestorFundingHistory> investorFundingHistoryList=investorFundingHistoryDAO.findBy("investorID",Integer.toString(investorID),"txnStatus","0");
    if(investorFundingHistoryList.size()==0){
      List<InvestorVAHistory> investorVAHistoryList=investorVAHistoryDAO.findBy("investorID", Integer.toString(investorID), "status", "0");
      if(investorVAHistoryList.size()!=0){
        return false;
      }  
    }
    return true;
  }

  public void rejectLoan(String loanAppID) {
    //UPDATING STATE TO R FOR CORRESPONDING REJECTED LOANAPPID
    LoanInvestment loanInvestment=loanInvestmentDAO.findBy("loanAppID",loanAppID).get(0);
    loanInvestment.setState("R");
    loanInvestmentDAO.update(loanInvestment);
  }

  public String approveLoan(String loanAppID,Double loanAmount,Integer ApplicationID,Integer loanTenor) throws Exception{
    HashMap<String,Object> map=new HashMap<String,Object>();
    map.put("loanAppID", loanAppID);
    map.put("loanAmount",loanAmount);
    map.put("ApplicationID",ApplicationID);
    map.put("loanTenor",loanTenor);
    kafkaTemplate.send("loanApproved",objectMapper.writeValueAsString(map));
    return "Loan Approved";
  }

  @KafkaListener(topics = "loanApproved")
  public void loanApproved(String param) throws Exception{
    HashMap<String,Object> loanData=objectMapper.readValue(param,HashMap.class);
    databaseService.insertRecordToLoanInvestment(loanData);
  }

public void fundAllLoan() {
  List<LoanInvestment> fundingLoansList = loanInvestmentDAO.findBy("State","N");
  List<LoanInvestment> loansList = sortWithInvestorPreference(fundingLoansList);
  Iterator<LoanInvestment> iterator=loansList.iterator();
  while(iterator.hasNext()){
    kafkaTemplate.send("fundLoans",iterator.next().getLoanAppID());
  }
}

@KafkaListener(topics = "fundLoans")
  public void fundLoans(String param){
    //Not funding all loans for now
    //Uncomment fundLoan(param) method call to fund all loans 
    System.out.println("Funding Loan : "+param);
    //this.fundLoan(param);
  }

}