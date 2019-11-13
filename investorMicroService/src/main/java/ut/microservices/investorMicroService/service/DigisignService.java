package ut.microservices.investormicroservice.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.microservices.investormicroservice.dto.ButtonDTO;
import ut.microservices.investormicroservice.dto.ColumnDTO;
import ut.microservices.investormicroservice.dto.DigisignDocumentsDTO;
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
    
  void sendDocuments(Integer applicationID, Integer investorID,String loanAppID) {
    //Send applicationID,investorID to SendingDocumentsMS 
    databaseService.insertRecordToDigisignAgreement(applicationID,investorID,loanAppID);
  }

  public ResponseDTO<DigisignDocumentsDTO> digisignDocuments(String investorID) throws Exception{
    List<DigisignAgreement> documentsList=digisignAgreementDAO.findBy("investorID",investorID);
    return getDigisignDocumentsResponseBody(documentsList);
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
      //for now we are checking all sign
      if(!disburse){
        disbursementService.disburseLoan(digisignAgreement);
      }
    }
  }
  public void customerSignedDocument(String documentID) throws Exception {
    boolean disburse=false;
    DigisignAgreement digisignAgreement=digisignAgreementDAO.findBy("documentID",documentID).get(0);
    digisignAgreement.setUserSignedAt(new Date());
    if(digisignAgreement.getStatusAgreement().equalsIgnoreCase("L")){
      digisignAgreement.setStatusAgreement("S");
      if(digisignAgreement.getStatusLenderAgreement()=="S"){
        disburse=true;
      }
    }
    digisignAgreementDAO.update(digisignAgreement);
    //for now we are checking all sign
    if(!disburse){
      //Disburse Loan
      disbursementService.disburseLoan(digisignAgreement);
    }
  }

  private ResponseDTO<DigisignDocumentsDTO> getDigisignDocumentsResponseBody(List<DigisignAgreement> documentsList) {
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

    response.setRows(rows);
    HashMap<String,String> tableColumns=new HashMap<String,String>();
    tableColumns.put("applicationID", "Application ID");
    tableColumns.put("documentLenderID", "Lender Document ID");
    tableColumns.put("lenderAgreementStatus", "Lender Document Status");
    List<ColumnDTO> columns=new LinkedList<ColumnDTO>();
    for(Map.Entry<String,String> entry : tableColumns.entrySet()){
      ColumnDTO columnDTO=new ColumnDTO();
      columnDTO.setKey(entry.getKey());
      columnDTO.setTitle(entry.getValue());
      columnDTO.setDataIndex(entry.getKey());
      columns.add(columnDTO);
    }
    response.setColumns(columns);
    List<ButtonDTO> buttons=new LinkedList<ButtonDTO>();
    ButtonDTO buttonDTO=new ButtonDTO();
    buttons.add(buttonDTO);
    response.setButton(buttons);
    return response;
  }
}