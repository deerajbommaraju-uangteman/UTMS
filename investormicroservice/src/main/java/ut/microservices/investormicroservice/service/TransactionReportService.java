package ut.microservices.investormicroservice.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.microservices.investormicroservice.dto.DetailedTransactionReportDTO;
import ut.microservices.investormicroservice.dto.ResponseDTO;
import ut.microservices.investormicroservice.dto.TransactionReportDTO;
import ut.microservices.investormicroservice.model.DigisignAgreement;
import ut.microservices.investormicroservice.model.InvestorFundingHistory;
import ut.microservices.investormicroservice.model.InvestorVAHistory;
import ut.microservices.investormicroservice.model.LoanInvestment;
import ut.microservices.investormicroservice.repository.IGenericDAO;

@Service
public class TransactionReportService{

    IGenericDAO<InvestorFundingHistory> investorFundingHistoryDAO;
    IGenericDAO<InvestorVAHistory> investorVAHistoryDAO;
    IGenericDAO<LoanInvestment> loanInvestmentDAO;
    IGenericDAO<DigisignAgreement> digisignAgreementDAO;

    @Autowired
    ResponseBodyService responseBodyService;

    @Autowired
    DigisignService digisignService;
    

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
    public void setDigisignAgreementDAO(IGenericDAO<DigisignAgreement> digisignAgreementDAO) {
      this.digisignAgreementDAO = digisignAgreementDAO;
      this.digisignAgreementDAO.setClazz(DigisignAgreement.class);
    }


	public ResponseDTO<TransactionReportDTO> transactionReport(String investorID) {
        List<InvestorFundingHistory> fundingHistoryList=investorFundingHistoryDAO.findBy("investorID", investorID);
		return responseBodyService.getTransactionReportResponseBody(fundingHistoryList);
	}


	public ResponseDTO<DetailedTransactionReportDTO> detailedTransactionReport(String vaNumber) throws Exception {
        List<InvestorVAHistory> investorVAHistoryList=investorVAHistoryDAO.findBy("vaNumber", vaNumber);
        return responseBodyService.getDetailedTransactionReportResponseBody(investorVAHistoryList);
    }
    
    public Double calculateTotalAmount(String VANumber){
        List<InvestorVAHistory> vaHistoryList=investorVAHistoryDAO.findBy("vaNumber",VANumber);
        Iterator<InvestorVAHistory> iterator=vaHistoryList.iterator();
        Double amount=0.0;
        while(iterator.hasNext()){
            InvestorVAHistory investorVAHistory=iterator.next();
            amount+=investorVAHistory.getLoanAmount();
        }
        return amount;
    }

	public int calculateTotalCustomerCount(String VANumber) {
        return investorVAHistoryDAO.findBy("vaNumber",VANumber).size();
	}

	public HashMap<String, String> getLoanStatus(InvestorVAHistory investorVAHistory) {
        LoanInvestment loanInvestment=loanInvestmentDAO.findBy("loanAppID",investorVAHistory.getLoanAppID()).get(0);
        List<DigisignAgreement> digisignAgreements=digisignAgreementDAO.findBy("ApplicationID",Integer.toString(loanInvestment.getApplicationID()));
        HashMap<String,String> loanStatus=new HashMap<String,String>();
        if(digisignAgreements.isEmpty()){
            if(investorVAHistory.getStatus()==0){
                loanStatus.put("status","Not Processed");
                loanStatus.put("operation",null);
                loanStatus.put("remarks","Receipt Not uploaded");
            }
            else{
                loanStatus.put("status","Failed");
                loanStatus.put("operation","sendAgain");
                loanStatus.put("remarks","Documents not generated");
            }
        }
        else{
            DigisignAgreement digisignAgreement=digisignAgreements.get(0);
            if(digisignAgreement.getStatusAgreement().equalsIgnoreCase("F") || digisignAgreement.getStatusLenderAgreement().equalsIgnoreCase("F") || digisignAgreement.getServerLenderFilePath().isEmpty() || digisignAgreement.getServerFilePath().isEmpty()){
                loanStatus.put("status","Failed");
                loanStatus.put("operation","sendAgain");
                loanStatus.put("remarks","Document Failed to send");
            }
            else if(digisignService.isDocumentExpired(digisignAgreement)){
                loanStatus.put("status","Expired");
                loanStatus.put("operation",null);
                loanStatus.put("remarks","Document Expired");
            }
            else{
                loanStatus.put("status","In process");
                loanStatus.put("operation",null);
                if(digisignAgreement.getLenderSignedAt()==null){
                    loanStatus.put("remarks","Waiting For Lender Sign");
                }
                else if(digisignAgreement.getUserSignedAt()==null){
                    loanStatus.put("remarks","Waiting For Borrower Sign");
                }
                else{
                    loanStatus.put("remarks","All users signed Document");
                }
            }
        }
		return loanStatus;
	}
}