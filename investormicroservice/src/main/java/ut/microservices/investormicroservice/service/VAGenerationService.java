package ut.microservices.investormicroservice.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import ut.microservices.investormicroservice.dto.InvestorFundedLoansDTO;
import ut.microservices.investormicroservice.dto.ResponseDTO;
import ut.microservices.investormicroservice.model.InvestorFundingHistory;
import ut.microservices.investormicroservice.model.InvestorVAHistory;
import ut.microservices.investormicroservice.model.LoanInvestment;
import ut.microservices.investormicroservice.repository.IGenericDAO;

@Service
@Transactional
public class VAGenerationService {

  IGenericDAO<LoanInvestment> loanInvestmentDAO;
  IGenericDAO<InvestorVAHistory> investorVAHistoryDAO;
  IGenericDAO<InvestorFundingHistory> investorFundingHistoryDAO;

  @Autowired
  KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  DatabaseService databaseService;

  @Autowired
  ResponseBodyService responseBodyService;

  @Autowired
  public void setLoanInvestmentDAO(IGenericDAO<LoanInvestment> loanInvestmentDAO) {
    this.loanInvestmentDAO = loanInvestmentDAO;
    this.loanInvestmentDAO.setClazz(LoanInvestment.class);
  }

  @Autowired
  public void setInvestorVAHistoryDAO(IGenericDAO<InvestorVAHistory> investorVAHistoryDAO) {
    this.investorVAHistoryDAO = investorVAHistoryDAO;
    this.investorVAHistoryDAO.setClazz(InvestorVAHistory.class);
  }

  @Autowired
  public void setInvestorFundingHistoryDAO(IGenericDAO<InvestorFundingHistory> investorFundingHistoryDAO) {
    this.investorFundingHistoryDAO = investorFundingHistoryDAO;
    this.investorFundingHistoryDAO.setClazz(InvestorFundingHistory.class);
  }

  public ResponseDTO<InvestorFundedLoansDTO> confirmationFunding() throws Exception {
    //For now Investor ID is considered as 1
    int investorID=1;
    ArrayList<LoanInvestment> fundedLoansList = (ArrayList<LoanInvestment>) loanInvestmentDAO.findBy("investorID",Integer.toString(investorID),"State","W");
    if(fundedLoansList.isEmpty()){
      return new ResponseDTO<InvestorFundedLoansDTO>();
    }
    Integer vaNumber = 0;
    List<InvestorFundingHistory> investorFundingHsitoryList=investorFundingHistoryDAO.findBy("investorID",Integer.toString(investorID),"txnStatus","0");
    List<InvestorVAHistory> investorVAHistoryList=investorVAHistoryDAO.findBy("investorID",Integer.toString(investorID),"status","0");
    Iterator<LoanInvestment> iterator=fundedLoansList.iterator();
    if(investorFundingHsitoryList.isEmpty()){
      if(investorVAHistoryList.isEmpty()){
        //Payment not done
        //VA not generated
        Random random=new Random();
        int min=1000;
        int max=9999;
        vaNumber=random.nextInt((max - min) + 1) + min;
        while(iterator.hasNext()){
          LoanInvestment loan=iterator.next();
          databaseService.insertRecordToInvestorVAHistory(loan,vaNumber);
        }
        databaseService.insertRecordToInvestorFundingHistory(investorID,vaNumber);
        System.out.println("Payment not done and VA not generated");
      }
      else{
        //Payment Done 
        //Receipt not uploaded
        vaNumber=investorVAHistoryList.get(0).getVaNumber();
        System.out.println("Payment Done and Receipt not uploaded");
      }
    }
    else{
      //Payment not done but VA Generated
      vaNumber=Integer.parseInt(investorFundingHsitoryList.get(0).getInvestorVaNumber());
      while(iterator.hasNext()){
        LoanInvestment loan=iterator.next();
        if(investorVAHistoryDAO.findBy("loanAppID",loan.getLoanAppID()).isEmpty()){
          databaseService.insertRecordToInvestorVAHistory(loan,vaNumber);
        };
      }
      System.out.println("Payment not done but VA Generated");
    }
    return responseBodyService.getFundedLoansResponseBody(fundedLoansList,vaNumber);
  }
}