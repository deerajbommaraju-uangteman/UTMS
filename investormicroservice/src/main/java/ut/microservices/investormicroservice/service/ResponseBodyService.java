package ut.microservices.investormicroservice.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.microservices.investormicroservice.dto.AvailableLoansDTO;
import ut.microservices.investormicroservice.dto.ButtonDTO;
import ut.microservices.investormicroservice.dto.ColumnDTO;
import ut.microservices.investormicroservice.dto.DetailedTransactionReportDTO;
import ut.microservices.investormicroservice.dto.InvestorFundedLoansDTO;
import ut.microservices.investormicroservice.dto.LoansDTO;
import ut.microservices.investormicroservice.dto.ResponseDTO;
import ut.microservices.investormicroservice.dto.TransactionReportDTO;
import ut.microservices.investormicroservice.model.InvestorFundingHistory;
import ut.microservices.investormicroservice.model.InvestorVAHistory;
import ut.microservices.investormicroservice.model.LoanInvestment;

@Service
public class ResponseBodyService {

    @Autowired
    TransactionReportService transactionReportService;

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

	public ResponseDTO<TransactionReportDTO> getTransactionReportResponseBody(List<InvestorFundingHistory> fundingHistoryList) {
        //TODO
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
        response.setRows(rows);
        HashMap<String,String> tableColumns=new HashMap<String,String>();
        tableColumns.put("fundTxnNumber", "Txn ID");
        tableColumns.put("vaNumber", "VA Number");
        tableColumns.put("transactionDate", "Transaction Date");
        tableColumns.put("totalCustomers", "Total Customers");
        tableColumns.put("totalInvestment", "Total Investment");
        tableColumns.put("txnStatus", "Status");
        List<ColumnDTO> columns=new LinkedList<ColumnDTO>();
        for(Map.Entry<String,String> entry : tableColumns.entrySet()){
          ColumnDTO columnDTO=new ColumnDTO();
          columnDTO.setKey(entry.getKey());
          columnDTO.setTitle(entry.getValue());
          columnDTO.setDataIndex(entry.getKey());
          columns.add(columnDTO);
        }
        response.setColumns(columns);
        return response;
	}

	public ResponseDTO<DetailedTransactionReportDTO> getDetailedTransactionReportResponseBody(List<InvestorVAHistory> investorVAHistoryList) {
    //TODO 
		return null;
	}
    
}