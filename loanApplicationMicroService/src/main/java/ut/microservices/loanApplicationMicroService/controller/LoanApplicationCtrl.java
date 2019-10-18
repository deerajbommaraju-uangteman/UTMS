package ut.microservices.loanApplicationMicroService.controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ut.microservices.loanApplicationMicroService.service.LoanApplicationService;

@RestController
@RequestMapping("/application-form")
public class LoanApplicationCtrl {
    @Autowired
    LoanApplicationService loanApplicationService;

    @CrossOrigin
    @PostMapping("/received")
    public String newApplicationReceived(@RequestBody HashMap<String, String> application) throws IOException {
    return loanApplicationService.newApplicationReceived(application);
  }


  @CrossOrigin
  @GetMapping(path = "/getApplicationData")
  public @ResponseBody String getApplicationData(String status) {
    return loanApplicationService.getApplicationData(status);
  }

    @CrossOrigin
    @GetMapping(path = "/example")
    public @ResponseBody String getExample() {
    return "hello";
    }
}