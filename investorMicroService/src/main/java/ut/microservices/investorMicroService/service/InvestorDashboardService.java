package ut.microservices.investorMicroService.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import ut.microservices.investorMicroService.dto.AvailableLoansDto;
import ut.microservices.investorMicroService.dto.ButtonDto;
import ut.microservices.investorMicroService.dto.ColumnDto;
import ut.microservices.investorMicroService.dto.ResponseDto;
import ut.microservices.investorMicroService.model.LoanInvestment;
import ut.microservices.investorMicroService.repository.IGenericDao;

@Service
@Transactional 
public class InvestorDashboardService {

  IGenericDao<LoanInvestment> loanInvestmentDao;

  @Autowired
   KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  DatabaseService databaseService;


  @Autowired
  public void setLoanInvestmentDao(IGenericDao<LoanInvestment> daoToSet) {
     loanInvestmentDao = daoToSet;
    loanInvestmentDao.setClazz(LoanInvestment.class);
  }

  public @ResponseBody ResponseDto<AvailableLoansDto> getAvailableLoans() throws Exception{
    List<LoanInvestment> fundingLoansList = loanInvestmentDao.findBy("State","N");
    return getResponseBody(fundingLoansList);
  }

  public void fundLoan(String loanAppID) {
    //UPDATING STATE TO W FOR CORRESPONDING FUNDED LOANAPPID
    LoanInvestment loanInvestment=loanInvestmentDao.findBy("loanAppID",loanAppID).get(0);
    loanInvestment.setState("W");
    loanInvestmentDao.update(loanInvestment);   
  }

  public void rejectLoan(String loanAppID) {
    //UPDATING STATE TO R FOR CORRESPONDING REJECTED LOANAPPID
    LoanInvestment loanInvestment=loanInvestmentDao.findBy("loanAppID",loanAppID).get(0);
    loanInvestment.setState("R");
    loanInvestmentDao.update(loanInvestment);
  }

  public String approveLoan(String loanAppID,Double loanAmount,Integer ApplicationID,Integer loanTenor) throws Exception{
    HashMap<String,Object> map=new HashMap<String,Object>();
    map.put("loanAppID", loanAppID);
    map.put("loanAmount",loanAmount);
    map.put("ApplicationID",ApplicationID);
    map.put("loanTenor",loanTenor);
    kafkaTemplate.send("loanApproved",objectMapper.writeValueAsString(map));
    return "Loan Approved";
  }

  @KafkaListener(topics = "loanApproved")
  public void loanApproved(String param) throws Exception{
    HashMap<String,Object> loanData=objectMapper.readValue(param,HashMap.class);
    databaseService.insertRecordToLoanInvestment(loanData);
  }

  private ResponseDto<AvailableLoansDto> getResponseBody(List<LoanInvestment> fundingLoansList) {
    Iterator<LoanInvestment> iterator=fundingLoansList.iterator();
    ResponseDto<AvailableLoansDto> response=new ResponseDto<AvailableLoansDto>();
    List<AvailableLoansDto> rows=new LinkedList<AvailableLoansDto>();
    int key=0;

    //Preparing Rows Data
    while(iterator.hasNext()){
      key++;
      LoanInvestment loan=iterator.next();
      AvailableLoansDto availableLoansDto=new AvailableLoansDto();
      availableLoansDto.setKey(Integer.toString(key));
      availableLoansDto.setApplicationID(Integer.toString(loan.getApplicationID()));
      availableLoansDto.setID(Long.toString(loan.getID()));
      availableLoansDto.setLoanAmount(Double.toString(loan.getLoanAmount()));
      availableLoansDto.setLoanAppID(loan.getLoanAppID());
      availableLoansDto.setLoanTenor(Integer.toString(loan.getLoanTenor()));
      rows.add(availableLoansDto);
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
    List<ColumnDto> columns=new LinkedList<ColumnDto>();
    for(Map.Entry<String,String> entry : tableColumns.entrySet()){
      ColumnDto columnDto=new ColumnDto();
      columnDto.setKey(entry.getKey());
      columnDto.setTitle(entry.getValue());
      columnDto.setDataIndex(entry.getKey());
      columns.add(columnDto);
    }
    response.setColumns(columns);

    //Preparing Button data
    List<ButtonDto> buttons=new LinkedList<ButtonDto>();
    ButtonDto buttonDto=new ButtonDto();
    buttonDto.setTitle("Action");
    buttonDto.setKey("Action");
    buttonDto.setAction("Action");
    buttons.add(buttonDto);
    response.setButton(buttons);
    return response;
  }

}