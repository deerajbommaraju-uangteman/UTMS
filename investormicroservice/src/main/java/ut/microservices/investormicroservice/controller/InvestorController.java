package ut.microservices.investormicroservice.controller;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import scala.annotation.meta.param;
import ut.microservices.investormicroservice.dto.AvailableLoansDTO;
import ut.microservices.investormicroservice.dto.DetailedTransactionReportDTO;
import ut.microservices.investormicroservice.dto.InvestorFundedLoansDTO;
import ut.microservices.investormicroservice.dto.LenderDocumentsDTO;
import ut.microservices.investormicroservice.dto.LoansDTO;
import ut.microservices.investormicroservice.dto.ResponseDTO;
import ut.microservices.investormicroservice.dto.TransactionReportDTO;
import ut.microservices.investormicroservice.service.BulkSignService;
import ut.microservices.investormicroservice.service.CronjobService;
import ut.microservices.investormicroservice.service.DigisignService;
import ut.microservices.investormicroservice.service.InvestorDashboardService;
import ut.microservices.investormicroservice.service.PaymentService;
import ut.microservices.investormicroservice.service.TransactionReportService;
import ut.microservices.investormicroservice.service.VAGenerationService;

@RestController
@RequestMapping("/ut/investor")
public class InvestorController {

    @Autowired
    InvestorDashboardService investorDashboardservice;

    @Autowired
    PaymentService paymentService;

    @Autowired
    DigisignService digisignService;

    @Autowired
    BulkSignService bulkSignService;

    @Autowired
    TransactionReportService transactionReportService;

    @Autowired
    VAGenerationService vaGenerationService;

    @Autowired
    CronjobService cronjobService;

    @Autowired
    ObjectMapper objectMapper;

    @CrossOrigin
    @GetMapping("/test")
    public String investorLogin() {
        return "Testing";
    }


    /*
    Investor Home Page
    */


    // @CrossOrigin
    // @PostMapping("/approveLoan")
    // public String approveLoan(@RequestBody String param) throws Exception {
    //     HashMap<String,String> map=objectMapper.readValue(param,HashMap.class);
    //     return investorDashboardservice.approveLoan(map.get("loanAppID"), Double.parseDouble(map.get("loanAmount")), Integer.parseInt(map.get("ApplicationID")), Integer.parseInt(map.get("loanTenor")));
    // }

    @CrossOrigin
    @GetMapping("/all-loans")
    public @ResponseBody ResponseDTO<LoansDTO> getAllLoans() throws Exception{
      return investorDashboardservice.getAllLoans();
    }

    @CrossOrigin
    @GetMapping("/available-loans")
    public @ResponseBody ResponseDTO<AvailableLoansDTO> getAvailableLoans() throws Exception{
      return investorDashboardservice.getAvailableLoans();
    }

    @CrossOrigin
    @PostMapping("fundAllLoan")
    public void fundAllLoan(){
        investorDashboardservice.fundAllLoan();
    }

    @CrossOrigin
    @PostMapping("fundLoan")
    public void fundLoan(@RequestBody HashMap<String,String> param){
        investorDashboardservice.fundLoan(param.get("loanAppID"));
    }

    @CrossOrigin
    @PostMapping("rejectLoan")
    public void rejectLoan(@RequestBody HashMap<String,String> param){
        investorDashboardservice.rejectLoan(param.get("loanAppID"));
    }  
    
    @CrossOrigin
    @GetMapping("confirmation-funding")
    public @ResponseBody ResponseDTO<InvestorFundedLoansDTO> confirmationFunding() throws Exception{
        return vaGenerationService.confirmationFunding();
    }

    @CrossOrigin
    @GetMapping("generate-VA")
    public @ResponseBody String generateVA(@RequestBody String param) throws Exception{
        return vaGenerationService.generateVA(param);
    }

    /*
    Investor Funding Page
    */

    @CrossOrigin
    @PostMapping("receipt-uploaded")
    public void receiptUploaded(@RequestBody String param) throws Exception{
        paymentService.receiptUploaded(param);
    }

    @CrossOrigin
    @PostMapping("payment-received")
    public void paymentReceivedFromBank(@RequestBody String param) throws Exception{
        paymentService.paymentReceivedFromBank(param);
    }

    /*
    Investor Documents
    */

    @CrossOrigin
    @PostMapping("lender-signed-document")
    public void lenderSignedDocument(@RequestBody HashMap<String,String> documentID) throws Exception{
        digisignService.lenderSignedDocument(documentID);
    }

    @CrossOrigin
    @PostMapping("customer-signed-document")
    public void customerSignedDocument(String documentID) throws Exception{
        digisignService.customerSignedDocument(documentID);
    }

    @CrossOrigin
    @PostMapping("lender-UT-bulkSign")
    public void lenderUTDocumentBulkSign(@RequestBody HashMap<String,String> param) throws Exception{
        bulkSignService.lenderUTDocumentBulkSign(param.get("vaNumber"));
    }

    @CrossOrigin
    @PostMapping("lender-Borrower-bulkSign")
    public void lenderBorrowerDocumentBulkSign(@RequestBody HashMap<String,String> param) throws Exception{
        bulkSignService.lenderBorrowerDocumentBulkSign(param.get("vaNumber"));
    }

    @CrossOrigin
    @GetMapping("bulkSign-page")
    public @ResponseBody ResponseDTO<LenderDocumentsDTO> getAllLenderDocuments() throws Exception{
        String investorID="1";
        return digisignService.getAllLenderDocuments(investorID);
    }

    

    /*
    Transaction Report
    */
    @CrossOrigin
    @GetMapping("transaction-report")
    public @ResponseBody ResponseDTO<TransactionReportDTO> transactionReport() throws Exception{
        String investorID="1";
        return transactionReportService.transactionReport(investorID);
    }

    @CrossOrigin
    @PostMapping("detailed-transaction-report")
    public @ResponseBody ResponseDTO<DetailedTransactionReportDTO> detailedTransactionReport(@RequestBody HashMap<String,String> param) throws Exception{
       return transactionReportService.detailedTransactionReport(param.get("vaNumber"));
    }

}