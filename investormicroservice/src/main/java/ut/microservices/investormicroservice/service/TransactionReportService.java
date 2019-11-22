package ut.microservices.investormicroservice.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.microservices.investormicroservice.dto.DetailedTransactionReportDTO;
import ut.microservices.investormicroservice.dto.ResponseDTO;
import ut.microservices.investormicroservice.dto.TransactionReportDTO;
import ut.microservices.investormicroservice.model.InvestorFundingHistory;
import ut.microservices.investormicroservice.model.InvestorVAHistory;
import ut.microservices.investormicroservice.repository.IGenericDAO;

@Service
public class TransactionReportService{

    IGenericDAO<InvestorFundingHistory> investorFundingHistoryDAO;
    IGenericDAO<InvestorVAHistory> investorVAHistoryDAO;

    @Autowired
    ResponseBodyService responseBodyService;
    

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


	public ResponseDTO<TransactionReportDTO> transactionReport(String investorID) {
        List<InvestorFundingHistory> fundingHistoryList=investorFundingHistoryDAO.findBy("investorID", investorID);
		return responseBodyService.getTransactionReportResponseBody(fundingHistoryList);
	}


	public ResponseDTO<DetailedTransactionReportDTO> detailedTransactionReport(String vaNumber) {
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
}