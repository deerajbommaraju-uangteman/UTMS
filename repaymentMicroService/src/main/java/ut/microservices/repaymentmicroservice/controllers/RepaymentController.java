package ut.microservices.repaymentmicroservice.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ut.microservices.repaymentmicroservice.dao.IGenericDAO;
import ut.microservices.repaymentmicroservice.dto.BorrowerLoanDetailsDTO;
import ut.microservices.repaymentmicroservice.dto.CustomerRepaymentHomePageDTO;
import ut.microservices.repaymentmicroservice.dto.GenerateVaDTO;
import ut.microservices.repaymentmicroservice.models.ApplicantData;
import ut.microservices.repaymentmicroservice.models.ApplicationData;
import ut.microservices.repaymentmicroservice.services.BorrowerService;
import ut.microservices.repaymentmicroservice.services.CimbSoapService;
import ut.microservices.repaymentmicroservice.services.RepaymentService;


@RestController
@RequestMapping("/user")
public class RepaymentController {

    @Autowired
    private RepaymentService repaymentService;

    @Autowired 
    private CimbSoapService cimbSoapService;
    
    @Autowired
    private BorrowerService borrowerService;

    IGenericDAO<ApplicationData> applicationDataDAO;
    
    @Autowired
    public void setApplicationDataDAO(IGenericDAO<ApplicationData> applicationDataDAO){
        this.applicationDataDAO = applicationDataDAO;
        applicationDataDAO.setClazz(ApplicationData.class);
    }
           
	@CrossOrigin
    @PostMapping(value = "/postLoginDetails")
    public @ResponseBody CustomerRepaymentHomePageDTO postCustomerLogin(@RequestBody HashMap<String, String> param) throws Exception{
        return repaymentService.postCustomerLogin(param);    
	}

    @CrossOrigin
    @PostMapping("/makeLoanRepayment")
    public @ResponseBody GenerateVaDTO makeLoanRepayment(@RequestBody HashMap<String, String> userdata) throws Exception{
         return repaymentService.makeLoanRepayment(userdata);
    }

    @CrossOrigin
    @GetMapping(path = "/getRepaymentData/{VaNumber}")
    public @ResponseBody String loanDataForReconcile(@PathVariable String VaNumber) throws JsonProcessingException {
        return repaymentService.loanDataForReconcile(VaNumber);
    }
    
    @CrossOrigin
    @PostMapping(value = "/postCustomerLogin")
    public @ResponseBody BorrowerLoanDetailsDTO getVAPaymentDetails(@RequestParam HashMap<String, String> param) throws Exception{
        return borrowerService.getVAPaymentDetails(param.get("LoanApplicationID"));
    }

    @CrossOrigin
    @PostMapping(value = "/testdata1")
    public @ResponseBody Date getCimbInquiryResponse(){
        return cimbSoapService.getCimbInquiryResponse();
    }

}