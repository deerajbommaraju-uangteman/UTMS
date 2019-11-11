package ut.microservices.loanApplicationMicroService.controller;

import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ut.microservices.loanApplicationMicroService.dto.ApplicationDto;
import ut.microservices.loanApplicationMicroService.dto.ResponseDto;
import ut.microservices.loanApplicationMicroService.model.ApplicantData;
import ut.microservices.loanApplicationMicroService.service.DVServices;
import ut.microservices.loanApplicationMicroService.service.ManagerServices;
import ut.microservices.loanApplicationMicroService.service.StaffServices;

/**
 * ApplicationProcessingCtrl
 */
@RestController
@RequestMapping("/application-processing")
 public class ApplicationProcessingCtrl {
    
    @Autowired
    ManagerServices managerService;

    @Autowired
    DVServices dvService;

    @Autowired
    StaffServices staffService;
  
    @CrossOrigin
    @GetMapping(path = "/dvRecievedApplications")
    public @ResponseBody ResponseDto<ApplicationDto> dvApplicationRecieved() throws Exception {
        return dvService.getAvailableLoans();
    }

    @CrossOrigin
    @PostMapping(path = "/dvEditApplicationPersonalDetails")
    public @ResponseBody ApplicantData dvEditApplicationPersonalDetails(@RequestBody HashMap<String,String> ApplicationID) throws JsonProcessingException {
        return dvService.dvEditApplicationPersonalDetails(ApplicationID);
    }

    @CrossOrigin
    @PostMapping(path = "/dvSubmitApplicationPersonalDetails")
    public @ResponseBody String dvSubmitApplicationPersonalDetails(@RequestBody ApplicantData ApplicationID) throws JsonProcessingException {
        System.out.println(ApplicationID);
        return dvService.dvSubmitApplicationPersonalDetails(ApplicationID);
    }
    
    @CrossOrigin
    @PostMapping(path = "/dvEditLoanDetails")
    public @ResponseBody String dvEditLoanData(@RequestBody ApplicantData ApplicationID) throws JsonProcessingException {
        return dvService.dvEditLoanDetails(ApplicationID);
    }

    @CrossOrigin
    @PostMapping("/dvApprovedLoans")
    public void dvApprovedLoans(@RequestBody String ApplicationID) throws JsonProcessingException {
        dvService.approvedLoan(ApplicationID);
    }

    @CrossOrigin
    @PostMapping(path = "/dvRejectedLoans")
    public void dvRejectedLoans(@RequestBody String applicationID){
        dvService.rejectedLoan(applicationID);
    }

    @CrossOrigin
    @GetMapping(path = "/staffRecievedApplications")
    public @ResponseBody ResponseDto<ApplicationDto> staffApplicationRecieved() throws Exception {
        return staffService.getAvailableLoans();
    }

    @CrossOrigin
    @PostMapping("/staffApprovedLoans")
    public void staffApprovedLoans(@RequestBody String ApplicationID) throws JsonProcessingException {
        staffService.approvedLoan(ApplicationID);
    }

    @CrossOrigin
    @PostMapping(path = "/staffRejectedLoans")
    public void staffRejectedLoans(@RequestBody String applicationID){
        staffService.rejectedLoan(applicationID);
    }

    @CrossOrigin
    @GetMapping(path = "/managerRecievedApplications")
    public @ResponseBody ResponseDto<ApplicationDto> managerApplicationRecieved() throws Exception {
        return managerService.getAvailableLoans();
    }

    @CrossOrigin
    @PostMapping("/managerApprovedLoans")
    public void managerApprovedLoans(@RequestBody String ApplicationID) throws JsonProcessingException {
        managerService.approvedLoan(ApplicationID);
    }

    @CrossOrigin
    @PostMapping(path = "/managerRejectedLoans")
    public void managerRejectedLoans(@RequestBody String applicationID){
        managerService.rejectedLoan(applicationID);
    }

    
}