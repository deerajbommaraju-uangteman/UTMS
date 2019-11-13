package ut.microservices.investormicroservice.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import ut.microservices.investormicroservice.dto.AvailableLoansDTO;
import ut.microservices.investormicroservice.dto.ButtonDTO;
import ut.microservices.investormicroservice.dto.ColumnDTO;
import ut.microservices.investormicroservice.dto.ResponseDTO;
import ut.microservices.investormicroservice.model.LoanInvestment;
import ut.microservices.investormicroservice.repository.IGenericDAO;

@Service
@Transactional 
public class InvestorDashboardService {

  IGenericDAO<LoanInvestment> loanInvestmentDAO;

  @Autowired
   KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  DatabaseService databaseService;


  @Autowired
  public void setLoanInvestmentDAO(IGenericDAO<LoanInvestment> loanInvestmentDAO) {
    this.loanInvestmentDAO = loanInvestmentDAO;
    this.loanInvestmentDAO.setClazz(LoanInvestment.class);
  }

  public @ResponseBody ResponseDTO<AvailableLoansDTO> getAvailableLoans() throws Exception{
    List<LoanInvestment> fundingLoansList = loanInvestmentDAO.findBy("State","N");
    return getResponseBody(fundingLoansList);
  }

  public void fundLoan(String loanAppID) {
    //UPDATING STATE TO W FOR CORRESPONDING FUNDED LOANAPPID
    LoanInvestment loanInvestment=loanInvestmentDAO.findBy("loanAppID",loanAppID).get(0);
    loanInvestment.setState("W");
    loanInvestmentDAO.update(loanInvestment);   
  }

  public void rejectLoan(String loanAppID) {
    //UPDATING STATE TO R FOR CORRESPONDING REJECTED LOANAPPID
    LoanInvestment loanInvestment=loanInvestmentDAO.findBy("loanAppID",loanAppID).get(0);
    loanInvestment.setState("R");
    loanInvestmentDAO.update(loanInvestment);
  }

  // public String approveLoan(String loanAppID,Double loanAmount,Integer ApplicationID,Integer loanTenor) throws Exception{
  //   HashMap<String,Object> map=new HashMap<String,Object>();
  //   map.put("loanAppID", loanAppID);
  //   map.put("loanAmount",loanAmount);
  //   map.put("ApplicationID",ApplicationID);
  //   map.put("loanTenor",loanTenor);
  //   kafkaTemplate.send("loanApproved",objectMapper.writeValueAsString(map));
  //   return "Loan Approved";
  // }

  @KafkaListener(topics = "loanApproved")
  public void loanApproved(String param) throws Exception{
    HashMap<String,Object> loanData=objectMapper.readValue(param,HashMap.class);
    databaseService.insertRecordToLoanInvestment(loanData);
  }

  private ResponseDTO<AvailableLoansDTO> getResponseBody(List<LoanInvestment> fundingLoansList) {
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
    response.setRows(rows);

    //Preparing Column data
    HashMap<String,String> tableColumns=new HashMap<String,String>();
    tableColumns.put("id", "ID");
    tableColumns.put("loanAppID", "Loan ID");
    tableColumns.put("loanAmount", "Loan Amount");
    tableColumns.put("loanTenor", "Loan Tenor");
    tableColumns.put("applicationID", "Application ID");
    tableColumns.put("Action","Action");
    List<ColumnDTO> columns=new LinkedList<ColumnDTO>();
    for(Map.Entry<String,String> entry : tableColumns.entrySet()){
      ColumnDTO columnDTO=new ColumnDTO();
      columnDTO.setKey(entry.getKey());
      columnDTO.setTitle(entry.getValue());
      columnDTO.setDataIndex(entry.getKey());
      columns.add(columnDTO);
    }
    response.setColumns(columns);

    //Preparing Button data
    List<ButtonDTO> buttons=new LinkedList<ButtonDTO>();
    ButtonDTO buttonDTO=new ButtonDTO();
    buttonDTO.setTitle("Action");
    buttonDTO.setKey("Action");
    buttonDTO.setAction("Action");
    buttons.add(buttonDTO);
    response.setButton(buttons);
    return response;
  }

}