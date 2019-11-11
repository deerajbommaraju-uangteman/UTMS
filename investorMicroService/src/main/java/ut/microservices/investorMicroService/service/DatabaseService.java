package ut.microservices.investorMicroService.service;

import java.util.HashMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.microservices.investorMicroService.model.DigisignAgreement;
import ut.microservices.investorMicroService.model.InvestorFundingHistory;
import ut.microservices.investorMicroService.model.InvestorVAHistory;
import ut.microservices.investorMicroService.model.LoanInvestment;
import ut.microservices.investorMicroService.repository.IGenericDao;

@Service
@Transactional 
public class DatabaseService {
    IGenericDao<LoanInvestment> loanInvestmentDao;
    IGenericDao<InvestorVAHistory> investorVAHistoryDao;
    IGenericDao<InvestorFundingHistory> investorFundingHistoryDao;
    IGenericDao<DigisignAgreement> digisignAgreementDao;

    @Autowired
    public void setDigisignAgreementtDao(IGenericDao<DigisignAgreement> daoToSet) {
      digisignAgreementDao = daoToSet;
      digisignAgreementDao.setClazz(DigisignAgreement.class);
    }

    @Autowired
    public void setLoanInvestmentDao(IGenericDao<LoanInvestment> daoToSet) {
      loanInvestmentDao = daoToSet;
      loanInvestmentDao.setClazz(LoanInvestment.class);
    }

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
    
    void insertRecordToInvestorVAHistory(LoanInvestment loan,Integer vaNumber) {
        InvestorVAHistory investorVAHistory=new InvestorVAHistory();
        investorVAHistory.setLoanAppID(loan.getLoanAppID());
        investorVAHistory.setLoanAmount(loan.getLoanAmount());
        investorVAHistory.setInvestorID(loan.getInvestorID());
        investorVAHistory.setStatus(0);
        investorVAHistory.setVaNumber(vaNumber);
        investorVAHistory.setUpdatedBy(loan.getInvestorID());
        investorVAHistory.setBankID(01);
        investorVAHistory.setApplicationID(loan.getApplicationID());
        investorVAHistoryDao.save(investorVAHistory);
      }
      void insertRecordToInvestorFundingHistory(Integer investorID,Integer vaNumber) {
        InvestorFundingHistory investorFundingHistory=new InvestorFundingHistory();
        investorFundingHistory.setInvestorID(investorID);
        investorFundingHistory.setInvestorVaNumber(Integer.toString(vaNumber));
        investorFundingHistory.setFundTxnNumber("FundingTxtNumber");
        investorFundingHistory.setTxnStatus(0);
        investorFundingHistoryDao.save(investorFundingHistory);
      }
      void insertRecordToLoanInvestment(HashMap<String, Object> loanData) {
        LoanInvestment loanInvestment=new LoanInvestment();
        loanInvestment.setLoanAmount((Double)loanData.get("loanAmount"));
        loanInvestment.setLoanAppID((String)loanData.get("loanAppID"));
        loanInvestment.setApplicationID((Integer)loanData.get("ApplicationID"));
        loanInvestment.setLoanTenor((Integer)loanData.get("loanTenor"));
        loanInvestment.setState("N");
        loanInvestment.setInvestorID(01);
        loanInvestment.setCreatedBy(01);
        loanInvestment.setUpdatedBy(01);
        loanInvestmentDao.save(loanInvestment);
      }

	public void insertRecordToDigisignAgreement(Integer applicationID, Integer investorID,String loanAppID) {
        //Calling LAMS endpoint to get applicant data
        DigisignAgreement digisignAgreement=new DigisignAgreement();
        digisignAgreement.setApplicationID(applicationID);
        digisignAgreement.setInvestorID(investorID);
        digisignAgreement.setDocumentID(applicationID+""+001);
        digisignAgreement.setDocumentLenderID(applicationID+""+investorID);
        digisignAgreement.setApplicantID(new Long(001));//For now..Assuming ApplicantID is received from applicantData
        digisignAgreement.setDuLenderEmailUser("lenderemail@gmail.com");//For now....Assuming lenderemail  is received from Lender 
        digisignAgreement.setStatusAgreement("D");//Always Document initial status is D
        digisignAgreement.setStatusLenderAgreement("D");//Always Document initial status is D
        digisignAgreementDao.save(digisignAgreement);
	}

	public void insertRecordToLogsCIMBNiaga() {
        //New loan record is inserted to LogsCIMBNiaga table
	}
}