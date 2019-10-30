package ut.microservices.investorMicroService.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ut.microservices.investorMicroService.service.InvestorService;


@RestController
@RequestMapping("/ut/investor")
public class InvestorController {

    @Autowired
    InvestorService service;

    @CrossOrigin
    @GetMapping("/login")
    public String investorLogin(@RequestBody String param) {
        return service.investorLogin(param);    
    }

    @CrossOrigin
    @GetMapping("/available-loan")
    public @ResponseBody String getAvailableLoans() throws Exception{
      return service.getAvailableLoans();
    }

    @CrossOrigin
    @PostMapping("fundLoan")
    public void fundLoan(@RequestBody String loanAppID){
        service.fundLoan(loanAppID);
    }

    @CrossOrigin
    @PostMapping("rejectLoan")
    public void rejectLoan(@RequestBody String loanAppID){
        service.rejectLoan(loanAppID);
    }  
    
    @CrossOrigin
    @GetMapping("confirmation-funding")
    public @ResponseBody String confirmationFunding() throws Exception{
        return service.confirmationFunding();
    }

    @CrossOrigin
    @PostMapping("payment-done")
    public @ResponseBody String paymentDone(@RequestBody String fundedLoans) throws Exception{
        return service.paymentDone(fundedLoans);
    }

}