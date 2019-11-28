package ut.microservices.investormicroservice.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.microservices.investormicroservice.model.ApplicantData;
import ut.microservices.investormicroservice.model.DigisignAgreement;
import ut.microservices.investormicroservice.model.InvestorFundingHistory;
import ut.microservices.investormicroservice.model.InvestorVAHistory;
import ut.microservices.investormicroservice.model.LoanInvestment;
import ut.microservices.investormicroservice.model.LogsCIMBNiaga;
import ut.microservices.investormicroservice.model.MrBankList;
import ut.microservices.investormicroservice.repository.IGenericDAO;

@Service
@Transactional 
public class DatabaseService {
    IGenericDAO<LoanInvestment> loanInvestmentDAO;
    IGenericDAO<InvestorVAHistory> investorVAHistoryDAO;
    IGenericDAO<InvestorFundingHistory> investorFundingHistoryDAO;
    IGenericDAO<DigisignAgreement> digisignAgreementDAO;
    IGenericDAO<LogsCIMBNiaga> logsCIMBNiagaDAO;
    IGenericDAO<MrBankList> mrBankListDAO;


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

    @Autowired
    public void setLogsCIMBNiagaDAO(IGenericDAO<LogsCIMBNiaga> logsCIMBNiagaDAO) {
      this.logsCIMBNiagaDAO = logsCIMBNiagaDAO;
      this.logsCIMBNiagaDAO.setClazz(LogsCIMBNiaga.class);
    }

    @Autowired
    public void setMrBankListDAO(IGenericDAO<MrBankList> mrBankListDAO) {
      this.mrBankListDAO = mrBankListDAO;
      this.mrBankListDAO.setClazz(MrBankList.class);
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
        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        investorFundingHistory.setFundTxnNumber("INVF"+investorID+dateFormat.format(new Date()));
        investorFundingHistory.setTxnStatus(0);
        investorFundingHistoryDAO.save(investorFundingHistory);
      }
      void insertRecordToLoanInvestment(HashMap<String, Object> loanData) {
        LoanInvestment loanInvestment=new LoanInvestment();
        loanInvestment.setLoanAmount((Double)loanData.get("loanAmount"));
        loanInvestment.setLoanAppID((String)loanData.get("loanAppID"));
        loanInvestment.setApplicationID((Integer)loanData.get("ApplicationID"));
        loanInvestment.setApplicantID((Integer)loanData.get("ApplicantID"));
        loanInvestment.setLoanTenor((Integer)loanData.get("loanTenor"));
        loanInvestment.setState("N");
        loanInvestment.setInvestorID(01);
        loanInvestment.setCreatedBy(01);
        loanInvestment.setUpdatedBy(01);
        loanInvestmentDAO.save(loanInvestment);
      }

	public void insertRecordToDigisignAgreement(Integer applicationID, Integer investorID,String loanAppID) {
        //TODO
        //loanAppID is used in future purpose
        //Calling LAMS endpoint to get applicant data
        LoanInvestment loanInvestment=loanInvestmentDAO.findBy("loanAppID",loanAppID).get(0);
        DigisignAgreement digisignAgreement=new DigisignAgreement();
        digisignAgreement.setApplicationID(applicationID);
        digisignAgreement.setInvestorID(investorID);
        digisignAgreement.setDocumentID(applicationID+""+001);
        digisignAgreement.setDocumentLenderID(applicationID+""+investorID);
        digisignAgreement.setApplicantID(loanInvestment.getApplicantID());
        digisignAgreement.setDuLenderEmailUser("lenderemail@gmail.com");//For now....Assuming lenderemail
        digisignAgreement.setStatusAgreement("D");//Always Document initial status is D
        digisignAgreement.setStatusLenderAgreement("D");//Always Document initial status is D
        digisignAgreementDAO.save(digisignAgreement);
	}

	public void insertRecordToLogsCIMBNiaga(ApplicantData applicantData, LoanInvestment loan, DigisignAgreement digisignAgreement) {
    LogsCIMBNiaga logsCIMBNiaga=new LogsCIMBNiaga();
    logsCIMBNiaga.setBankAccountName(applicantData.getBankUsername());
    logsCIMBNiaga.setBankAccountNumber(applicantData.getBankNumber());
    logsCIMBNiaga.setLoanAppID(loan.getLoanAppID());
    logsCIMBNiaga.setLoanAmount(loan.getLoanAmount());
    logsCIMBNiaga.setStatus("PENDING");
    //For now UTBankID is 4....In further value has to be fetched from Config
    logsCIMBNiaga.setUtBankID(Integer.valueOf(4));
    MrBankList bankDetails=mrBankListDAO.findBy("ID", Integer.toString(applicantData.getBankNameID())).get(0);
    logsCIMBNiaga.setBankCode(bankDetails.getCode());
    //TODO
    //For now value is stored.....In future it has to be encrypted before storing
    logsCIMBNiaga.setKeyChecking(loan.getLoanAppID()+"PENDING");
    logsCIMBNiagaDAO.save(logsCIMBNiaga);
	}
}