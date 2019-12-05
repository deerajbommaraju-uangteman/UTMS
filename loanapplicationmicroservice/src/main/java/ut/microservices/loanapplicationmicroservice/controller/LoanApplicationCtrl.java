package ut.microservices.loanapplicationmicroservice.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.assertj.core.util.Arrays;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ut.microservices.loanapplicationmicroservice.dto.ApplicationStatusDTO;
import ut.microservices.loanapplicationmicroservice.dto.TestUrlDTO;
import ut.microservices.loanapplicationmicroservice.model.*;
import ut.microservices.loanapplicationmicroservice.service.*;

@RestController
@RequestMapping("/application-form")
public class LoanApplicationCtrl {

  @Autowired
  LoanApplicationService loanApplicationService;

  @CrossOrigin
  @GetMapping(path = "/testurl")
  public List<TestUrlDTO> testUrl() {
    TestUrlDTO test=new TestUrlDTO();
    List<TestUrlDTO> testList = new LinkedList<TestUrlDTO>();
    test.setTestUrlData("input", "text", "username","required");
    testList.add(test);
    System.out.println(testList.get(0).getLabel());
    test=new TestUrlDTO();
    test.setTestUrlData("input", "password", "password","required");
    testList.add(test);
    System.out.println(testList);
    return testList;
  }

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

    return loanApplicationService.getApplicationData(ApplicationID); 
  }
  
  @CrossOrigin
  @PostMapping(path = "/saveApplicationStatusData")
  public @ResponseBody String PostApplicationStatusData(@RequestBody String ApplicationID) throws IOException {
    System.out.println("kdhsadshha"+ApplicationID);
    ObjectMapper obj = new ObjectMapper();
    ApplicationStatusDTO aps=obj.readValue(ApplicationID, ApplicationStatusDTO.class);
    
    return ApplicationID;//loanApplicationService.getApplicationData(ApplicationID); 
  }
  
  @CrossOrigin
  @GetMapping(path = "/getDistrictData")
  public @ResponseBody String getDistrictData() throws JsonProcessingException {

    //System.out.println(loanApplicationService.getDistrictData());
    return "success"; 
  }
  

}