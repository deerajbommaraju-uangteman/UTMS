package ut.microservices.investormicroservice.service;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.microservices.investormicroservice.dto.ApplicationDataDTO;
import ut.microservices.investormicroservice.dto.AvailableLoansDTO;
import ut.microservices.investormicroservice.dto.DetailedTransactionReportDTO;
import ut.microservices.investormicroservice.dto.DigisignDocumentsDTO;
import ut.microservices.investormicroservice.dto.InvestorFundedLoansDTO;
import ut.microservices.investormicroservice.dto.LenderDocumentsDTO;
import ut.microservices.investormicroservice.dto.LoansDTO;
import ut.microservices.investormicroservice.dto.ResponseDTO;
import ut.microservices.investormicroservice.dto.TransactionReportDTO;
import ut.microservices.investormicroservice.model.ApplicantData;
import ut.microservices.investormicroservice.model.DigisignAgreement;
import ut.microservices.investormicroservice.model.InvestorFundingHistory;
import ut.microservices.investormicroservice.model.InvestorVAHistory;
import ut.microservices.investormicroservice.model.LoanInvestment;
import ut.microservices.investormicroservice.repository.IGenericDAO;

@Service
public class ResponseBodyService {

  IGenericDAO<InvestorVAHistory> investorVAHistoryDAO;

    @Autowired
    TransactionReportService transactionReportService;

    @Autowired
    DisbursementService disbursementService;

    @Autowired
    DigisignService digisignService;

    @Autowired
  public void setInvestorVAHistoryDAO(IGenericDAO<InvestorVAHistory> investorVAHistoryDAO) {
    this.investorVAHistoryDAO = investorVAHistoryDAO;
    this.investorVAHistoryDAO.setClazz(InvestorVAHistory.class);
  }

    public ResponseDTO<LoansDTO> getLoanResponseBody(List<LoanInvestment> loansList) {
        Iterator<LoanInvestment> iterator=loansList.iterator();
        ResponseDTO<LoansDTO> response=new ResponseDTO<LoansDTO>();
        List<LoansDTO> rows=new LinkedList<LoansDTO>();
        int key=0;
    
        //Preparing Rows Data
        while(iterator.hasNext()){
          key++;
          LoanInvestment loan=iterator.next();
          LoansDTO loansDTO=new LoansDTO();
          loansDTO.setKey(Integer.toString(key));
          loansDTO.setApplicationID(Integer.toString(loan.getApplicationID()));
          loansDTO.setID(Long.toString(loan.getID()));
          loansDTO.setLoanAmount(Double.toString(loan.getLoanAmount()));
          loansDTO.setLoanAppID(loan.getLoanAppID());
          loansDTO.setLoanTenor(Integer.toString(loan.getLoanTenor()));
          String state=loan.getState();
          switch(state){
            case "R":loansDTO.setStatus("Loan Rejected");
                      break;
            case "N":loansDTO.setStatus("Loan Ready to fund");
                    break;
            case "W":loansDTO.setStatus("Loan Funded");
                    break;
            case "B":loansDTO.setStatus("Loan Disbursed");
                    break;
            case "P":loansDTO.setStatus("Loan is in Process");
                    break;
          }
          rows.add(loansDTO);
        }
        response.setData(rows);        
        return response;
      }

      public ResponseDTO<AvailableLoansDTO> getAvailableLoanResponseBody(List<LoanInvestment> fundingLoansList) {
        Iterator<LoanInvestment> iterator=fundingLoansList.iterator();
        ResponseDTO<AvailableLoansDTO> response=new ResponseDTO<AvailableLoansDTO>();
        List<AvailableLoansDTO> rows=new LinkedList<AvailableLoansDTO>();
        int key=0;
    
        //Preparing Rows Data
        while(iterator.hasNext()){
          key++;
          LoanInvestment loan=iterator.next();
          AvailableLoansDTO availableLoansDTO=new AvailableLoansDTO();
          availableLoansDTO.setKey(Integer.toString(key));
          availableLoansDTO.setApplicationID(Integer.toString(loan.getApplicationID()));
          availableLoansDTO.setID(Long.toString(loan.getID()));
          availableLoansDTO.setLoanAmount(Double.toString(loan.getLoanAmount()));
          availableLoansDTO.setLoanAppID(loan.getLoanAppID());
          availableLoansDTO.setLoanTenor(Integer.toString(loan.getLoanTenor()));
          rows.add(availableLoansDTO);
        }
        response.setData(rows);
    
        
        return response;
      }

      public ResponseDTO<InvestorFundedLoansDTO> getFundedLoansResponseBody(List<LoanInvestment> fundedLoansList, Integer vaNumber) {
        Iterator<LoanInvestment> iterator=fundedLoansList.iterator();
        ResponseDTO<InvestorFundedLoansDTO> response=new ResponseDTO<InvestorFundedLoansDTO>();
        List<InvestorFundedLoansDTO> rows=new LinkedList<InvestorFundedLoansDTO>();
        int key=0;
    
        //Preparing Rows Data
        Double totalAmount=0.0;
        while(iterator.hasNext()){
          key++;
          LoanInvestment loan=iterator.next();
          InvestorFundedLoansDTO investorFundedLoansDTO=new InvestorFundedLoansDTO();
          investorFundedLoansDTO.setKey(Integer.toString(key));
          investorFundedLoansDTO.setApplicationID(Integer.toString(loan.getApplicationID()));
          investorFundedLoansDTO.setID(Long.toString(loan.getID()));
          investorFundedLoansDTO.setLoanAmount(Double.toString(loan.getLoanAmount()));
          investorFundedLoansDTO.setLoanAppID(loan.getLoanAppID());
          investorFundedLoansDTO.setLoanTenor(Integer.toString(loan.getLoanTenor()));
          totalAmount+=loan.getLoanAmount();
          rows.add(investorFundedLoansDTO);
        }
        response.setData(rows);
    
        
        //Preparing Additional Data
        HashMap<String,String> data=new HashMap<String,String>();
        data.put("vaNumber",Integer.toString(vaNumber));
        data.put("totalAmount",Double.toString(totalAmount));
        response.setAdditionalData(data);
        return response;
      }

	public ResponseDTO<TransactionReportDTO> getTransactionReportResponseBody(List<InvestorFundingHistory> fundingHistoryList) {
        Iterator<InvestorFundingHistory> iterator=fundingHistoryList.iterator();
        ResponseDTO<TransactionReportDTO> response=new ResponseDTO<TransactionReportDTO>();
        List<TransactionReportDTO> rows=new LinkedList<TransactionReportDTO>();
        while(iterator.hasNext()){
          InvestorFundingHistory transaction=iterator.next();
          TransactionReportDTO transactionReportDTO=new TransactionReportDTO();
          transactionReportDTO.setFundTxnNumber(transaction.getFundTxnNumber());
          transactionReportDTO.setVaNumber(transaction.getInvestorVaNumber());
          transactionReportDTO.setTransactionDate(transaction.getUpdatedAt().toString());
          transactionReportDTO.setTotalInvestment(transactionReportService.calculateTotalAmount(transaction.getInvestorVaNumber()));
          transactionReportDTO.setTotalCustomers(transactionReportService.calculateTotalCustomerCount(transaction.getInvestorVaNumber()));
          if(transaction.getTxnStatus()==0){
            transactionReportDTO.setTxnStatus("Receipt Not Uploaded");
          }
          else{
            transactionReportDTO.setTxnStatus("Receipt Uploaded");
          }
          rows.add(transactionReportDTO);
        }
        response.setData(rows);
        return response;
	}

	public ResponseDTO<DetailedTransactionReportDTO> getDetailedTransactionReportResponseBody(List<InvestorVAHistory> investorVAHistoryList) throws Exception {
    Iterator<InvestorVAHistory> iterator=investorVAHistoryList.iterator();
    ResponseDTO<DetailedTransactionReportDTO> response=new ResponseDTO<DetailedTransactionReportDTO>();
    List<DetailedTransactionReportDTO> rows=new LinkedList<DetailedTransactionReportDTO>();
    while(iterator.hasNext()){
      InvestorVAHistory investorVAHistory=iterator.next();
      //ApplicantData applicantData=disbursementService.getApplicantData(investorVAHistory.getLoanAppID());
      //ApplicationDataDTO applicationDataDTO=disbursementService.getApplicationDataDTO(investorVAHistory.getLoanAppID());

      DetailedTransactionReportDTO detailedTransactionReportDTO=new DetailedTransactionReportDTO();
      detailedTransactionReportDTO.setLoanAppID(investorVAHistory.getLoanAppID());
      detailedTransactionReportDTO.setLoanAmount(investorVAHistory.getLoanAmount());
      detailedTransactionReportDTO.setApplicantName("ABC");
      //TODO
      detailedTransactionReportDTO.setLoanDuration(Integer.valueOf(30));
      if("Y".equalsIgnoreCase("Y")){
        detailedTransactionReportDTO.setLoanType("Installment Loan");
      }
      else{
        detailedTransactionReportDTO.setLoanType("PayDay Loan");
      }
      HashMap<String,String> loanStatus=transactionReportService.getLoanStatus(investorVAHistory);
      detailedTransactionReportDTO.setOperation(loanStatus.get("operation"));
      detailedTransactionReportDTO.setRemarks(loanStatus.get("remarks"));
      detailedTransactionReportDTO.setStatus(loanStatus.get("status"));
      System.out.println(detailedTransactionReportDTO);
      rows.add(detailedTransactionReportDTO);
    }
    response.setData(rows);
    return response;
  }
  
  public ResponseDTO<DigisignDocumentsDTO> getDigisignDocumentsResponseBody(List<DigisignAgreement> documentsList) {
    Iterator<DigisignAgreement> iterator=documentsList.iterator();
    ResponseDTO<DigisignDocumentsDTO> response=new ResponseDTO<DigisignDocumentsDTO>();
    List<DigisignDocumentsDTO> rows=new LinkedList<DigisignDocumentsDTO>();
    int key=0;
    while(iterator.hasNext()){
      key++;
      DigisignAgreement document=iterator.next();
      DigisignDocumentsDTO digisignDocumentsDTO=new DigisignDocumentsDTO();
      digisignDocumentsDTO.setKey(Integer.toString(key));
      digisignDocumentsDTO.setApplicationID(Integer.toString(document.getApplicationID()));
      digisignDocumentsDTO.setDocumentLenderID(document.getDocumentLenderID());
      if(document.getStatusLenderAgreement().equalsIgnoreCase("S")){
        digisignDocumentsDTO.setLenderAgreementStatus("Signed");
      }
      else{
        digisignDocumentsDTO.setLenderAgreementStatus("UnSigned");
      }
      rows.add(digisignDocumentsDTO);
    }

    response.setData(rows);
    //TODO
    //Need to insert additional data like VA Number and Total customer count in reponse
    return response;
  }

public ResponseDTO<LenderDocumentsDTO> getLenderDocumentsResponseBody(
	List<InvestorFundingHistory> investorFundingHistory) {
  Iterator<InvestorFundingHistory> iterator=investorFundingHistory.iterator();
  ResponseDTO<LenderDocumentsDTO> response=new ResponseDTO<LenderDocumentsDTO>();
  List<LenderDocumentsDTO> rows=new LinkedList<LenderDocumentsDTO>();
  while(iterator.hasNext()){
    InvestorFundingHistory fundingTransaction=iterator.next();
    List<InvestorVAHistory> investorVAHistoryList=investorVAHistoryDAO.findBy("vaNumber", fundingTransaction.getInvestorVaNumber(),"status","1");
    if(investorVAHistoryList.isEmpty()){
      continue;
    }
    LenderDocumentsDTO lenderDocumentsDTO=new LenderDocumentsDTO();
    lenderDocumentsDTO.setTransactionID(fundingTransaction.getFundTxnNumber());
    lenderDocumentsDTO.setVaNumber(fundingTransaction.getInvestorVaNumber());
    lenderDocumentsDTO.setTransactionDate(fundingTransaction.getUpdatedAt());;
    HashMap<String,Object> transactionStatus=digisignService.getTransactionStatus(investorVAHistoryList);
    String lenderUTStatus="";
    String lenderBorrowerStatus="";
    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
    lenderDocumentsDTO.setFundingAmount(nf.format((Double)transactionStatus.get("totalFundAmount")));
    lenderDocumentsDTO.setTotalLoans((Integer)transactionStatus.get("totalLoans"));
    if((Integer)transactionStatus.get("agreementCreationCount")==0){
      lenderUTStatus+="Agreements not created";
      lenderBorrowerStatus+="Agreements not created";
    }
    else{
      if((Integer)transactionStatus.get("totalLoans")!=(Integer)transactionStatus.get("agreementCreationCount")){
        Integer temp=(Integer)transactionStatus.get("totalLoans")-(Integer)transactionStatus.get("agreementCreationCount");
        lenderUTStatus=lenderUTStatus+temp+" agreements not created ";
        lenderBorrowerStatus=lenderBorrowerStatus+temp+" agreements not created ";
      }
      if((Integer)transactionStatus.get("expiredDocumentCount")>0){
        Integer temp=(Integer)transactionStatus.get("expiredDocumentCount");
        lenderUTStatus=lenderUTStatus+" Expired Documents : "+temp;
        lenderBorrowerStatus=lenderBorrowerStatus+" Expired Documents : "+temp;
      }
      if((Integer)transactionStatus.get("lenderUTSignedCount")>0){
        Integer temp=(Integer)transactionStatus.get("lenderUTSignedCount");
        lenderUTStatus=lenderUTStatus+" Signed Documents : "+temp;
      }
      if((Integer)transactionStatus.get("lenderBorrowerSignedCount")>0){
        Integer temp=(Integer)transactionStatus.get("lenderBorrowerSignedCount");
        lenderBorrowerStatus=lenderBorrowerStatus+" Signed Documents : "+temp;
      }
      Integer needToSignLenderBorrower=(Integer)transactionStatus.get("agreementCreationCount")-(Integer)transactionStatus.get("expiredDocumentCount")-(Integer)transactionStatus.get("lenderBorrowerSignedCount");
      Integer needToSignLenderUT=(Integer)transactionStatus.get("agreementCreationCount")-(Integer)transactionStatus.get("expiredDocumentCount")-(Integer)transactionStatus.get("lenderUTSignedCount");
      if(needToSignLenderBorrower>0){
        lenderDocumentsDTO.setShowLenderBorrowerSign(true);
        lenderBorrowerStatus=lenderBorrowerStatus+" Need to sign : "+needToSignLenderBorrower;
      }
      else{
        lenderDocumentsDTO.setShowLenderBorrowerSign(false);
      }
      if(needToSignLenderUT>0){
        lenderDocumentsDTO.setShowLenderUTSign(true);
        lenderUTStatus=lenderUTStatus+" Need to sign : "+needToSignLenderUT;
      }
      else{
        lenderDocumentsDTO.setShowLenderUTSign(false);
      }
    }
    lenderDocumentsDTO.setLenderUTAgreementsStatus(lenderUTStatus);
    lenderDocumentsDTO.setLenderBorrrowerAgreementsStatus(lenderBorrowerStatus);
    rows.add(lenderDocumentsDTO);
  }
  response.setData(rows);
	return response;
}
    
}