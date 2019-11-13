package ut.microservices.investormicroservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
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

    public void paymentDone(String fundedLoans) throws Exception {
        //InvestorVAHistory investorVAHistory=new InvestorVAHistory();
      Map<String,Object> map=objectMapper.readValue(fundedLoans,Map.class);
      ArrayList<HashMap> loansList=(ArrayList)map.get("loanAppID");
      Iterator<HashMap> iterator = loansList.iterator();
      HashMap loan=null;
      Integer vaNumber=null;
      while (iterator.hasNext()) { 
        loan=iterator.next();
        String loanAppID=loan.get("loanAppID").toString();
        InvestorVAHistory investorVAHistory=investorVAHistoryDAO.findBy("loanAppID",loanAppID).get(0);
        investorVAHistory.setStatus(1);
        investorVAHistoryDAO.update(investorVAHistory);
        LoanInvestment loanInvestment=loanInvestmentDAO.findBy("loanAppID",loanAppID).get(0);
        loanInvestment.setState("P");
        loanInvestmentDAO.update(loanInvestment);
        digisignService.sendDocuments(investorVAHistory.getApplicationID(),investorVAHistory.getInvestorID(),loanAppID);
        vaNumber=investorVAHistory.getVaNumber();
      }
      InvestorFundingHistory investorFundingHistory=investorFundingHistoryDAO.findBy("investorVaNumber",Integer.toString(vaNumber)).get(0);
      investorFundingHistory.setTxnStatus(1);
      investorFundingHistoryDAO.update(investorFundingHistory);
    }
}