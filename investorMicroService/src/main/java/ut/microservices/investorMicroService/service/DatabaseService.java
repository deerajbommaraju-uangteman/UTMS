package ut.microservices.investormicroservice.service;

import java.util.HashMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.microservices.investormicroservice.model.DigisignAgreement;
import ut.microservices.investormicroservice.model.InvestorFundingHistory;
import ut.microservices.investormicroservice.model.InvestorVAHistory;
import ut.microservices.investormicroservice.model.LoanInvestment;
import ut.microservices.investormicroservice.repository.IGenericDAO;

@Service
@Transactional 
public class DatabaseService {
    IGenericDAO<LoanInvestment> loanInvestmentDAO;
    IGenericDAO<InvestorVAHistory> investorVAHistoryDAO;
    IGenericDAO<InvestorFundingHistory> investorFundingHistoryDAO;
    IGenericDAO<DigisignAgreement> digisignAgreementDAO;

    @Autowired
    public void setDigisignAgreementDAO(IGenericDAO<DigisignAgreement> digisignAgreementDAO) {
      this.digisignAgreementDAO = digisignAgreementDAO;
      this.digisignAgreementDAO.setClazz(DigisignAgreement.class);
    }

    @Autowired
    public void setLoanInvestmentDAO(IGenericDAO<LoanInvestment> loanInvestmentDAO) {
      this.loanInvestmentDAO = loanInvestmentDAO;
      this.loanInvestmentDAO.setClazz(LoanInvestment.class);
    }

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
        investorVAHistoryDAO.save(investorVAHistory);
      }
      void insertRecordToInvestorFundingHistory(Integer investorID,Integer vaNumber) {
        InvestorFundingHistory investorFundingHistory=new InvestorFundingHistory();
        investorFundingHistory.setInvestorID(investorID);
        investorFundingHistory.setInvestorVaNumber(Integer.toString(vaNumber));
        investorFundingHistory.setFundTxnNumber("FundingTxtNumber");
        investorFundingHistory.setTxnStatus(0);
        investorFundingHistoryDAO.save(investorFundingHistory);
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
        loanInvestmentDAO.save(loanInvestment);
      }

	public void insertRecordToDigisignAgreement(Integer applicationID, Integer investorID,String loanAppID) {
        //loanAppID is used in future purpose
        //Calling LAMS endpoint to get applicant data
        DigisignAgreement digisignAgreement=new DigisignAgreement();
        digisignAgreement.setApplicationID(applicationID);
        digisignAgreement.setInvestorID(investorID);
        digisignAgreement.setDocumentID(applicationID+""+001);
        digisignAgreement.setDocumentLenderID(applicationID+""+investorID);
        digisignAgreement.setApplicantID(Long.valueOf(001));//For now..Assuming ApplicantID is received from applicantData
        digisignAgreement.setDuLenderEmailUser("lenderemail@gmail.com");//For now....Assuming lenderemail  is received from Lender 
        digisignAgreement.setStatusAgreement("D");//Always Document initial status is D
        digisignAgreement.setStatusLenderAgreement("D");//Always Document initial status is D
        digisignAgreementDAO.save(digisignAgreement);
	}

	public void insertRecordToLogsCIMBNiaga() {
        //New loan record is inserted to LogsCIMBNiaga table
	}
}