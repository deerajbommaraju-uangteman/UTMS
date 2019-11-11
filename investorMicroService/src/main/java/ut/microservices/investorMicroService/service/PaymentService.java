package ut.microservices.investorMicroService.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.microservices.investorMicroService.model.InvestorFundingHistory;
import ut.microservices.investorMicroService.model.InvestorVAHistory;
import ut.microservices.investorMicroService.model.LoanInvestment;
import ut.microservices.investorMicroService.repository.IGenericDao;

@Service
@Transactional
public class PaymentService {

    IGenericDao<LoanInvestment> loanInvestmentDao;
    IGenericDao<InvestorVAHistory> investorVAHistoryDao;
    IGenericDao<InvestorFundingHistory> investorFundingHistoryDao;

    

    @Autowired
    public void setinvestorFundingHistoryDao(IGenericDao<InvestorFundingHistory> daoToSet) {
      investorFundingHistoryDao = daoToSet;
      investorFundingHistoryDao.setClazz(InvestorFundingHistory.class);
    }

    
    @Autowired
    public void setInvestorVAHistoryDao(IGenericDao<InvestorVAHistory> daoToSet2) {
      investorVAHistoryDao = daoToSet2;
      investorVAHistoryDao.setClazz(InvestorVAHistory.class);
    }

    @Autowired
    public void setLoanInvestmentDao(IGenericDao<LoanInvestment> loanInvestmentDao) {
      this.loanInvestmentDao = loanInvestmentDao;
      this.loanInvestmentDao.setClazz(LoanInvestment.class);
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
        InvestorVAHistory investorVAHistory=investorVAHistoryDao.findBy("loanAppID",loanAppID).get(0);
        investorVAHistory.setStatus(1);
        investorVAHistoryDao.update(investorVAHistory);
        LoanInvestment loanInvestment=loanInvestmentDao.findBy("loanAppID",loanAppID).get(0);
        loanInvestment.setState("P");
        loanInvestmentDao.update(loanInvestment);
        digisignService.sendDocuments(investorVAHistory.getApplicationID(),investorVAHistory.getInvestorID(),loanAppID);
        vaNumber=investorVAHistory.getVaNumber();
      }
      InvestorFundingHistory investorFundingHistory=investorFundingHistoryDao.findBy("investorVaNumber",Integer.toString(vaNumber)).get(0);
      investorFundingHistory.setTxnStatus(1);
      investorFundingHistoryDao.update(investorFundingHistory);
    }
}