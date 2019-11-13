package ut.microservices.investormicroservice.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ut.microservices.investormicroservice.dto.AvailableLoansDTO;
import ut.microservices.investormicroservice.dto.DigisignDocumentsDTO;
import ut.microservices.investormicroservice.dto.InvestorFundedLoansDTO;
import ut.microservices.investormicroservice.dto.ResponseDTO;
import ut.microservices.investormicroservice.service.DigisignService;
import ut.microservices.investormicroservice.service.InvestorDashboardService;
import ut.microservices.investormicroservice.service.PaymentService;
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
    VAGenerationService vaGenerationService;

    @CrossOrigin
    @GetMapping("/login")
    public String investorLogin(@RequestBody String param) {
        return null;
    }

    // @CrossOrigin
    // @PostMapping("/approveLoan")
    // public String approveLoan(String loanAppID, Double loanAmount, Integer ApplicationID, Integer loanTenor)
    //         throws Exception {
    //     return investorDashboardservice.approveLoan(loanAppID, loanAmount, ApplicationID, loanTenor);
    // }

    @CrossOrigin
    @GetMapping("/available-loan")
    public @ResponseBody ResponseDTO<AvailableLoansDTO> getAvailableLoans() throws Exception{
      return investorDashboardservice.getAvailableLoans();
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
    @PostMapping("payment-done")
    public void paymentDone(@RequestBody String fundedLoans) throws Exception{
        paymentService.paymentDone(fundedLoans);
    }

    @CrossOrigin
    @GetMapping("investor-documents")
    public @ResponseBody ResponseDTO<DigisignDocumentsDTO> digisignDocuments() throws Exception{
        String investorID="1";
        return digisignService.digisignDocuments(investorID);
    }

    @CrossOrigin
    @PostMapping("lender-signed-Document")
    public void lenderSignedDocument(@RequestBody HashMap<String,String> documentID) throws Exception{
        digisignService.lenderSignedDocument(documentID);
    }

    @CrossOrigin
    @PostMapping("customer-signed-Document")
    public void customerSignedDocument(String documentID) throws Exception{
        digisignService.customerSignedDocument(documentID);
    }
}