package ut.microservices.investorMicroService.service;

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

import ut.microservices.investorMicroService.dto.ButtonDto;
import ut.microservices.investorMicroService.dto.ColumnDto;
import ut.microservices.investorMicroService.dto.DigisignDocumentsDto;
import ut.microservices.investorMicroService.dto.ResponseDto;
import ut.microservices.investorMicroService.model.DigisignAgreement;
import ut.microservices.investorMicroService.model.InvestorFundingHistory;
import ut.microservices.investorMicroService.model.InvestorVAHistory;
import ut.microservices.investorMicroService.model.LoanInvestment;
import ut.microservices.investorMicroService.repository.IGenericDao;

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

  IGenericDao<LoanInvestment> loanInvestmentDao;
  IGenericDao<InvestorVAHistory> investorVAHistoryDao;
  IGenericDao<InvestorFundingHistory> investorFundingHistoryDao;
  IGenericDao<DigisignAgreement> digisignAgreementDao;

  @Autowired
  public void setLoanInvestmentDao(IGenericDao<LoanInvestment> daoToSet) {
    loanInvestmentDao = daoToSet;
    loanInvestmentDao.setClazz(LoanInvestment.class);
  }

  @Autowired
  public void setDigisignAgreementtDao(IGenericDao<DigisignAgreement> daoToSet) {
    digisignAgreementDao = daoToSet;
    digisignAgreementDao.setClazz(DigisignAgreement.class);
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
    
  void sendDocuments(Integer applicationID, Integer investorID,String loanAppID) {
    //Send applicationID,investorID to SendingDocumentsMS 
    databaseService.insertRecordToDigisignAgreement(applicationID,investorID,loanAppID);
  }

  public ResponseDto<DigisignDocumentsDto> digisignDocuments(String investorID) throws Exception{
    List<DigisignAgreement> documentsList=digisignAgreementDao.findBy("investorID",investorID);
    return getDigisignDocumentsResponseBody(documentsList);
  }

  public void lenderSignedDocument(HashMap<String,String> documentID) throws Exception {
    boolean disburse=false;
    List<DigisignAgreement> digisignAgreementList=null;
    String type="Lender-UT";
    digisignAgreementList=digisignAgreementDao.findBy("documentLenderID",documentID.get("documentID"));
    if(digisignAgreementList.isEmpty()){
     type="Lender-Borrower";
     digisignAgreementList=digisignAgreementDao.findBy("documentID",documentID.get("documentID"));
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
      digisignAgreementDao.update(digisignAgreement);
      if(disburse){
        disbursementService.disburseLoan(digisignAgreement);
      }
    }
  }
  public void customerSignedDocument(String documentID) throws Exception {
    boolean disburse=false;
    DigisignAgreement digisignAgreement=digisignAgreementDao.findBy("documentID",documentID).get(0);
    digisignAgreement.setUserSignedAt(new Date());
    if(digisignAgreement.getStatusAgreement().equalsIgnoreCase("L")){
      digisignAgreement.setStatusAgreement("S");
      if(digisignAgreement.getStatusLenderAgreement()=="S"){
        disburse=true;
      }
    }
    digisignAgreementDao.update(digisignAgreement);
    if(disburse){
      //Disburse Loan
      disbursementService.disburseLoan(digisignAgreement);
    }
  }

  private ResponseDto<DigisignDocumentsDto> getDigisignDocumentsResponseBody(List<DigisignAgreement> documentsList) {
    Iterator<DigisignAgreement> iterator=documentsList.iterator();
    ResponseDto<DigisignDocumentsDto> response=new ResponseDto<DigisignDocumentsDto>();
    List<DigisignDocumentsDto> rows=new LinkedList<DigisignDocumentsDto>();
    int key=0;
    while(iterator.hasNext()){
      key++;
      DigisignAgreement document=iterator.next();
      DigisignDocumentsDto digisignDocumentsDto=new DigisignDocumentsDto();
      digisignDocumentsDto.setKey(Integer.toString(key));
      digisignDocumentsDto.setApplicationID(Integer.toString(document.getApplicationID()));
      digisignDocumentsDto.setDocumentLenderID(document.getDocumentLenderID());
      if(document.getStatusLenderAgreement().equalsIgnoreCase("S")){
        digisignDocumentsDto.setLenderAgreementStatus("Signed");
      }
      else{
        digisignDocumentsDto.setLenderAgreementStatus("UnSigned");
      }
      rows.add(digisignDocumentsDto);
    }

    response.setRows(rows);
    HashMap<String,String> tableColumns=new HashMap<String,String>();
    tableColumns.put("applicationID", "Application ID");
    tableColumns.put("documentLenderID", "Lender Document ID");
    tableColumns.put("lenderAgreementStatus", "Lender Document Status");
    List<ColumnDto> columns=new LinkedList<ColumnDto>();
    for(Map.Entry<String,String> entry : tableColumns.entrySet()){
      ColumnDto columnDto=new ColumnDto();
      columnDto.setKey(entry.getKey());
      columnDto.setTitle(entry.getValue());
      columnDto.setDataIndex(entry.getKey());
      columns.add(columnDto);
    }
    response.setColumns(columns);
    List<ButtonDto> buttons=new LinkedList<ButtonDto>();
    ButtonDto buttonDto=new ButtonDto();
    buttons.add(buttonDto);
    response.setButton(buttons);
    return response;
  }
}