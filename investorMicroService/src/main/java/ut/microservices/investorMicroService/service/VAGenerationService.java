package ut.microservices.investorMicroService.service;

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

import ut.microservices.investorMicroService.dto.ColumnDto;
import ut.microservices.investorMicroService.dto.InvestorFundedLoansDto;
import ut.microservices.investorMicroService.dto.ResponseDto;
import ut.microservices.investorMicroService.model.InvestorVAHistory;
import ut.microservices.investorMicroService.model.LoanInvestment;
import ut.microservices.investorMicroService.repository.IGenericDao;

@Service
@Transactional
public class VAGenerationService {

  IGenericDao<LoanInvestment> loanInvestmentDao;
  IGenericDao<InvestorVAHistory> investorVAHistoryDao;

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

  @Autowired
  public void setInvestorVAHistoryDao(IGenericDao<InvestorVAHistory> daoToSet2) {
    investorVAHistoryDao = daoToSet2;
    investorVAHistoryDao.setClazz(InvestorVAHistory.class);
  }

  public ResponseDto<InvestorFundedLoansDto> confirmationFunding() throws Exception {
    //Based on Loan State 'W'...loans are retrieved
    List<LoanInvestment> fundedLoansList = loanInvestmentDao.findBy("State","W");
    //For now Investor ID is considered as 1
    if(fundedLoansList.isEmpty()){
      return new ResponseDto<InvestorFundedLoansDto>();
    }
    int investorID=1;
    Integer vaNumber = null;
    List<InvestorVAHistory> investorVAHistory=investorVAHistoryDao.findVANumberByInvestorID(investorID);
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
          if(investorVAHistoryDao.findBy("loanAppID",loan.getLoanAppID()).isEmpty()){
            databaseService.insertRecordToInvestorVAHistory(loan, vaNumber);
          };
        }
      }
    }
    return getResponseBody(fundedLoansList,vaNumber);
  }

  private ResponseDto<InvestorFundedLoansDto> getResponseBody(List<LoanInvestment> fundedLoansList, Integer vaNumber) {
    Iterator<LoanInvestment> iterator=fundedLoansList.iterator();
    ResponseDto<InvestorFundedLoansDto> response=new ResponseDto<InvestorFundedLoansDto>();
    List<InvestorFundedLoansDto> rows=new LinkedList<InvestorFundedLoansDto>();
    int key=0;

    //Preparing Rows Data
    Double totalAmount=0.0;
    while(iterator.hasNext()){
      key++;
      LoanInvestment loan=iterator.next();
      InvestorFundedLoansDto investorFundedLoansDto=new InvestorFundedLoansDto();
      investorFundedLoansDto.setKey(Integer.toString(key));
      investorFundedLoansDto.setApplicationID(Integer.toString(loan.getApplicationID()));
      investorFundedLoansDto.setID(Long.toString(loan.getID()));
      investorFundedLoansDto.setLoanAmount(Double.toString(loan.getLoanAmount()));
      investorFundedLoansDto.setLoanAppID(loan.getLoanAppID());
      investorFundedLoansDto.setLoanTenor(Integer.toString(loan.getLoanTenor()));
      totalAmount+=loan.getLoanAmount();
      rows.add(investorFundedLoansDto);
    }
    response.setRows(rows);

    //Preparing Columns Data
    HashMap<String,String> tableColumns=new HashMap<String,String>();
    tableColumns.put("id", "ID");
    tableColumns.put("loanAppID", "Loan ID");
    tableColumns.put("loanAmount", "Loan Amount");
    tableColumns.put("loanTenor", "Loan Tenor");
    tableColumns.put("applicationID", "Application ID");
    List<ColumnDto> columns=new LinkedList<ColumnDto>();
    for(Map.Entry<String,String> entry : tableColumns.entrySet()){
      ColumnDto columnDto=new ColumnDto();
      columnDto.setKey(entry.getKey());
      columnDto.setTitle(entry.getValue());
      columnDto.setDataIndex(entry.getKey());
      columns.add(columnDto);
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