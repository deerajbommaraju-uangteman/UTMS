package ut.microservices.investorMicroService.service;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.microservices.investorMicroService.model.LoanInvestment;
import ut.microservices.investorMicroService.model.InvestorVAHistory;
import ut.microservices.investorMicroService.repository.IGenericDao;

@Service
@Transactional 
public class InvestorService {

    IGenericDao<LoanInvestment> loanInvestmentDao;
    IGenericDao<InvestorVAHistory> investorVAHistoryDao;

    @Autowired
    public void setLoanInvestmentDao(IGenericDao<LoanInvestment> daoToSet) {
      loanInvestmentDao = daoToSet;
      loanInvestmentDao.setClazz(LoanInvestment.class);
    }

    @Autowired
    public void setInvestorVAHistoryDao(IGenericDao<InvestorVAHistory> daoToSet2) {
      investorVAHistoryDao = daoToSet2;
      investorVAHistoryDao.setClazz(InvestorVAHistory.class);
    }

    @Autowired
    ObjectMapper objectMapper;


	public String investorLogin(String param) {
		return null;
	}


  public @ResponseBody String getAvailableLoans() throws Exception{
    HashMap<String,Object> data=new HashMap<String,Object>();
    //Available Loan state in LoanInvestment table is 'N'
    List<LoanInvestment> fundingLoansList = loanInvestmentDao.findByLoanState("N");
    data.put("fundedLoansCount",loanInvestmentDao.findByLoanState("W").size());
    data.put("fundingLoans",objectMapper.writeValueAsString(fundingLoansList));
    return objectMapper.writeValueAsString(data);
  }

    
  public void fundLoan(String loanAppID) {
  //UPDATING STATE TO W FOR CORRESPONDING FUNDED LOANAPPID
  LoanInvestment loanInvestment=loanInvestmentDao.findByLoanAppID(loanAppID).get(0);
  loanInvestment.setState("W");
  loanInvestmentDao.update(loanInvestment);   
}

public void rejectLoan(String loanAppID) {
  //UPDATING STATE TO R FOR CORRESPONDING REJECTED LOANAPPID
  LoanInvestment loanInvestment=loanInvestmentDao.findByLoanAppID(loanAppID).get(0);
  loanInvestment.setState("R");
  loanInvestmentDao.update(loanInvestment);
}

public String confirmationFunding() throws Exception{
  HashMap<String,Object> data=new HashMap<String,Object>();
  //Based on Loan State 'W'...loans are retrieved
  List<LoanInvestment> fundedLoansList = loanInvestmentDao.findByLoanState("W");
  //For now Investor ID is considered as 1
  int investorID=1;
  List<InvestorVAHistory> investorVAHistory=investorVAHistoryDao.findVANumberByInvestorID(investorID);
  if(investorVAHistory.isEmpty()){
    Random random=new Random();
    data.put("VANumber",random.nextInt());
    //Insert loan records into InvestorVAHistory
    //Record VANumber in InvestorFundingHistory
  }
  else{
    //If investor funds new loans then insert new record in InvestorVAHistory(TO DO)
    data.put("VANumber",investorVAHistory.get(0).getVaNumber());
  }
  data.put("fundingLoans",objectMapper.writeValueAsString(fundedLoansList));
  return objectMapper.writeValueAsString(data);
}

public String paymentDone(String fundedLoans) throws Exception {
  //InvestorVAHistory investorVAHistory=new InvestorVAHistory();
  Map<String,Object> map=objectMapper.readValue(fundedLoans,Map.class);
  ArrayList<HashMap> loansList=(ArrayList)map.get("loans");
  Iterator<HashMap> iterator = loansList.iterator();
  HashMap loan=null;
  while (iterator.hasNext()) { 
    loan=iterator.next();
    System.out.println(loan.get("loanAppID"));
  }
  
	return null;
}
}