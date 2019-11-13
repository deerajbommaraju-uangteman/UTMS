package ut.microservices.loanapplicationmicroservice.controller;

import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ut.microservices.loanapplicationmicroservice.model.*;
import ut.microservices.loanapplicationmicroservice.service.*;

@RestController
@RequestMapping("/application-form")
public class LoanApplicationCtrl {

  @Autowired
  LoanApplicationService loanApplicationService;

  @CrossOrigin
  @PostMapping(path = "/received")
  public String newApplicationReceived(@RequestBody TempApplicantDataModel application) {
    // //System.out.println(application);
    if(application.getID() != null) {
      return loanApplicationService.newApplicationEnded(application);
    }
    return loanApplicationService.newApplicationStarted(application);
  }

  @CrossOrigin
  @GetMapping(path = "/getApplicationData/{ApplicationID}")
  public @ResponseBody String getApplicationData(@PathVariable String ApplicationID) throws JsonProcessingException {

    //System.out.println(loanApplicationService.getApplicationData(ApplicationID));
    return "ass"; 
  }
  
  @CrossOrigin
  @GetMapping(path = "/getDistrictData")
  public @ResponseBody String getDistrictData() throws JsonProcessingException {

    //System.out.println(loanApplicationService.getDistrictData());
    return "ass"; 
  }
  

}