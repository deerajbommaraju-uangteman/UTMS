package ut.microservices.repaymentMicroService.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ut.microservices.repaymentMicroService.dto.CustomerRepaymentHomePageDto;
import ut.microservices.repaymentMicroService.dto.GenerateVaDto;
import ut.microservices.repaymentMicroService.services.RepaymentService;


@RestController
@RequestMapping("/user")
public class RepaymentCtrl {

    @Autowired
	private RepaymentService repaymentService;

	@CrossOrigin
    @PostMapping(value = "/postLoginDetails")
    public @ResponseBody CustomerRepaymentHomePageDto postCustomerLogin(@RequestBody HashMap<String, String> param) throws Exception{
        return repaymentService.postCustomerLogin(param);    
	}

    @CrossOrigin
    @PostMapping("/makeLoanRepayment")
    public @ResponseBody GenerateVaDto makeLoanRepayment(@RequestBody HashMap<String, String> userdata) throws Exception{
		 return repaymentService.makeLoanRepayment(userdata);
  }

  @CrossOrigin
  @GetMapping(path = "/getRepaymentData/{VaNumber}")
  public @ResponseBody String loanDataForReconcile(@PathVariable String VaNumber) throws Exception {
    return repaymentService.loanDataForReconcile(VaNumber);
  }



}