package ut.microservices.investormicroservice.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.microservices.investormicroservice.dto.LenderDocumentsDTO;
import ut.microservices.investormicroservice.dto.ResponseDTO;
import ut.microservices.investormicroservice.model.DigisignAgreement;
import ut.microservices.investormicroservice.model.InvestorFundingHistory;
import ut.microservices.investormicroservice.model.InvestorVAHistory;
import ut.microservices.investormicroservice.model.LoanInvestment;
import ut.microservices.investormicroservice.repository.IGenericDAO;

@Service
@Transactional
public class DigisignService {

  @Autowired
  DatabaseService databaseService;

  @Autowired
  DisbursementService disbursementService;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  NotificationService notificationService;

  @Autowired
  ResponseBodyService responseBodyService;

  IGenericDAO<LoanInvestment> loanInvestmentDAO;
  IGenericDAO<InvestorVAHistory> investorVAHistoryDAO;
  IGenericDAO<InvestorFundingHistory> investorFundingHistoryDAO;
  IGenericDAO<DigisignAgreement> digisignAgreementDAO;

  @Autowired
  public void setLoanInvestmentDAO(IGenericDAO<LoanInvestment> loanInvestmentDAO) {
    this.loanInvestmentDAO = loanInvestmentDAO;
    this.loanInvestmentDAO.setClazz(LoanInvestment.class);
  }

  @Autowired
  public void setDigisignAgreementtDAO(IGenericDAO<DigisignAgreement> digisignAgreementDAO) {
    this.digisignAgreementDAO = digisignAgreementDAO;
    this.digisignAgreementDAO.setClazz(DigisignAgreement.class);
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
    
  boolean sendDocuments(String loanAppID,int investorID) {
    //TODO
    //Send applicationID,investorID to SendingDocumentsMS
    //returns true if document sent successfully
    return true; 
  }

  public ResponseDTO<LenderDocumentsDTO> getAllLenderDocuments(String investorID) throws Exception{
    List<InvestorFundingHistory> investorFundingHistory=investorFundingHistoryDAO.findBy("investorID", investorID, "txnStatus", "1");
    return responseBodyService.getLenderDocumentsResponseBody(investorFundingHistory);
  }

  public void lenderSignedDocument(HashMap<String,String> documentID) throws Exception {
    boolean disburse=false;
    List<DigisignAgreement> digisignAgreementList=null;
    String type="Lender-UT";
    digisignAgreementList=digisignAgreementDAO.findBy("documentLenderID",documentID.get("documentID"));
    if(digisignAgreementList.isEmpty()){
     type="Lender-Borrower";
     digisignAgreementList=digisignAgreementDAO.findBy("documentID",documentID.get("documentID"));
    }
    if(!digisignAgreementList.isEmpty()){
      DigisignAgreement digisignAgreement=digisignAgreementList.get(0);
      if(type.equalsIgnoreCase("Lender-UT")){
        digisignAgreement.setLenderSignedAt(new Date());
        digisignAgreement.setUtSignedAt(new Date());
        digisignAgreement.setStatusLenderAgreement("S");
        if(digisignAgreement.getStatusAgreement()=="S"){
          disburse=true;
        }
      }
      else{
        if(digisignAgreement.getUserSignedAt()==null){
          digisignAgreement.setStatusAgreement("L");
          //notify customer to sign Document
          notificationService.notifyCustomerToSign(digisignAgreement.getDocumentID());
        }
        else{
          digisignAgreement.setStatusAgreement("S");
          if(digisignAgreement.getStatusLenderAgreement()=="S"){
            disburse=true;
          }
        }  
      }
      digisignAgreementDAO.update(digisignAgreement);
      //TODO
      //for now we are checking all sign
      if(!disburse){
        disbursementService.disburseLoan(digisignAgreement);
      }
    }
    else{
      //TODO
      //Return Exception Response
      //Document with specified ID is not available in DB
    }
  }
  public void customerSignedDocument(String documentID) throws Exception {
    boolean disburse=false;
    if(isDocumentGenerated(documentID)){
      DigisignAgreement digisignAgreement=digisignAgreementDAO.findBy("documentID",documentID).get(0);
      digisignAgreement.setUserSignedAt(new Date());
      if(digisignAgreement.getStatusAgreement().equalsIgnoreCase("L")){
        digisignAgreement.setStatusAgreement("S");
        if(digisignAgreement.getStatusLenderAgreement()=="S"){
          disburse=true;
        }
      }
      digisignAgreementDAO.update(digisignAgreement);
      //for now we are not checking all sign
      if(!disburse){
        //Disburse Loan
        disbursementService.disburseLoan(digisignAgreement);
      }
    }
    else{
      //TODO
      //Return Exception Response
      //Document with specified ID is not available in DB
    }
  }

  public HashMap<String, Object> getTransactionStatus(List<InvestorVAHistory> investorVAHistoryList) {
    HashMap<String,Object> transactionStatus=new HashMap<String,Object>();
    int agreementCreationCount=0;
    int lenderUTSignedCount=0;
    int lenderBorrowerSignedCount=0;
    int expiredDocumentCount=0;
    Double fundedAmount=0.0;
    Iterator<InvestorVAHistory> iterator=investorVAHistoryList.iterator();
    
    while(iterator.hasNext()){
      InvestorVAHistory vaHistory=iterator.next();
      fundedAmount+=vaHistory.getLoanAmount();
      if(isDocumentGenerated(vaHistory.getApplicationID())){
        DigisignAgreement agreement=digisignAgreementDAO.findBy("applicationID", Integer.toString(vaHistory.getApplicationID()), "investorID", Integer.toString(vaHistory.getInvestorID())).get(0);
        agreementCreationCount++;

        //TODO
        //If one Document is Expired then both documents are considered as Expired
        if(isDocumentExpired(agreement)){
          expiredDocumentCount++;
        }
        else{
          if(agreement.getStatusAgreement().equalsIgnoreCase("L") || agreement.getStatusAgreement().equalsIgnoreCase("S")){
            lenderBorrowerSignedCount++;
          }
          if(agreement.getLenderSignedAt()!=null || agreement.getStatusLenderAgreement().equalsIgnoreCase("S")){
            lenderUTSignedCount++;
          }
        }
      }
    }
    transactionStatus.put("totalLoans",investorVAHistoryList.size());
    transactionStatus.put("totalFundAmount",fundedAmount);
    transactionStatus.put("agreementCreationCount",agreementCreationCount);
    transactionStatus.put("lenderBorrowerSignedCount",lenderBorrowerSignedCount);
    transactionStatus.put("lenderUTSignedCount",lenderUTSignedCount);
    transactionStatus.put("expiredDocumentCount",expiredDocumentCount);
    return transactionStatus;
  }


// 

  public boolean isDocumentExpired(DigisignAgreement digisignAgreement) {
    if(digisignAgreement.getExpiredAt().before(new Date()) && digisignAgreement.getUserSignedAt()==null){
      return true;
    }
    return false;
  }
  public boolean isDocumentGenerated(Integer applicationID){
    List<DigisignAgreement> agreementList=digisignAgreementDAO.findBy("applicationID", Integer.toString(applicationID));
    if(agreementList.isEmpty()){
      return false;
    }
    DigisignAgreement agreement=agreementList.get(0);
    if(agreement.getStatusAgreement().equalsIgnoreCase("F") && agreement.getStatusLenderAgreement().equalsIgnoreCase("F")){
      return false;
    }
    return true;
  }

  public boolean isDocumentGenerated(String documentID){
    List<DigisignAgreement> agreementList=digisignAgreementDAO.findBy("documentID", documentID);
    if(agreementList.isEmpty()){
      agreementList=digisignAgreementDAO.findBy("documentLenderID", documentID);
      if(agreementList.isEmpty()){
        return false;
      }
    }
    DigisignAgreement agreement=agreementList.get(0);
    if(agreement.getStatusAgreement().equalsIgnoreCase("F") && agreement.getStatusLenderAgreement().equalsIgnoreCase("F")){
      return false;
    }
    return true;
  }
 
}