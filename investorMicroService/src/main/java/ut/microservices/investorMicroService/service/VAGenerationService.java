package ut.microservices.investormicroservice.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import ut.microservices.investormicroservice.dto.ColumnDTO;
import ut.microservices.investormicroservice.dto.InvestorFundedLoansDTO;
import ut.microservices.investormicroservice.dto.ResponseDTO;
import ut.microservices.investormicroservice.model.InvestorVAHistory;
import ut.microservices.investormicroservice.model.LoanInvestment;
import ut.microservices.investormicroservice.repository.IGenericDAO;

@Service
@Transactional
public class VAGenerationService {

  IGenericDAO<LoanInvestment> loanInvestmentDAO;
  IGenericDAO<InvestorVAHistory> investorVAHistoryDAO;

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

  @Autowired
  public void setInvestorVAHistoryDAO(IGenericDAO<InvestorVAHistory> investorVAHistoryDAO) {
    this.investorVAHistoryDAO = investorVAHistoryDAO;
    this.investorVAHistoryDAO.setClazz(InvestorVAHistory.class);
  }

  public ResponseDTO<InvestorFundedLoansDTO> confirmationFunding() throws Exception {
    //Based on Loan State 'W'...loans are retrieved
    List<LoanInvestment> fundedLoansList = loanInvestmentDAO.findBy("State","W");
    //For now Investor ID is considered as 1
    if(fundedLoansList.isEmpty()){
      return new ResponseDTO<InvestorFundedLoansDTO>();
    }
    int investorID=1;
    Integer vaNumber = null;
    List<InvestorVAHistory> investorVAHistory=investorVAHistoryDAO.findVANumberByInvestorID(investorID);
    Iterator<LoanInvestment> iterator=fundedLoansList.iterator();
    if(!fundedLoansList.isEmpty()){
      if(investorVAHistory.isEmpty()){
        Random random=new Random();
        vaNumber=random.nextInt();
        while(iterator.hasNext()){
          LoanInvestment loan=iterator.next();
          databaseService.insertRecordToInvestorVAHistory(loan,vaNumber);
        }
        databaseService.insertRecordToInvestorFundingHistory(investorID,vaNumber);
      }
      else{
        vaNumber=investorVAHistory.get(0).getVaNumber();
        while(iterator.hasNext()){
          LoanInvestment loan=iterator.next();
          if(investorVAHistoryDAO.findBy("loanAppID",loan.getLoanAppID()).isEmpty()){
            databaseService.insertRecordToInvestorVAHistory(loan, vaNumber);
          };
        }
      }
    }
    return getResponseBody(fundedLoansList,vaNumber);
  }

  private ResponseDTO<InvestorFundedLoansDTO> getResponseBody(List<LoanInvestment> fundedLoansList, Integer vaNumber) {
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
    response.setRows(rows);

    //Preparing Columns Data
    HashMap<String,String> tableColumns=new HashMap<String,String>();
    tableColumns.put("id", "ID");
    tableColumns.put("loanAppID", "Loan ID");
    tableColumns.put("loanAmount", "Loan Amount");
    tableColumns.put("loanTenor", "Loan Tenor");
    tableColumns.put("applicationID", "Application ID");
    List<ColumnDTO> columns=new LinkedList<ColumnDTO>();
    for(Map.Entry<String,String> entry : tableColumns.entrySet()){
      ColumnDTO columnDTO=new ColumnDTO();
      columnDTO.setKey(entry.getKey());
      columnDTO.setTitle(entry.getValue());
      columnDTO.setDataIndex(entry.getKey());
      columns.add(columnDTO);
    }
    response.setColumns(columns);
    
    //Preparing Additional Data
    HashMap<String,String> data=new HashMap<String,String>();
    data.put("vaNumber",Integer.toString(vaNumber));
    data.put("totalAmount",Double.toString(totalAmount));
    response.setAdditionalData(data);
    return response;
  }
}