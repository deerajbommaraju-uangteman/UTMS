package ut.microservices.repaymentmicroservice.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ut.microservices.repaymentmicroservice.services.CallbackService;

@RestController
@RequestMapping("/repayment")
public class CallbackController {

    @Autowired
    private CallbackService callbackService;

    @PostMapping(value = "/callback", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = { "application/xml" , "text/plain" })
    public ResponseEntity<?> getCallbackInquiryData(@RequestParam HashMap<String, String>  vendor) throws Exception {
      HashMap<String, Object> resp = callbackService.getCallbackInquiryData(vendor);
       return new ResponseEntity<>(resp.get("message"), HttpStatus.valueOf(resp.get("status").toString()));
    }

    @PostMapping(value = "/callback/notify", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> getCallbackNotifyData(@RequestParam HashMap<String, String>  vendor) throws Exception {
       return new ResponseEntity<>(callbackService.getCallbackNotifyData(vendor), HttpStatus.OK);
    }


   //  @PostMapping(value = "/cimb/virtual-account", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
   //  public ResponseEntity<?> getCIMBInquiryData(@RequestParam HashMap<String, String>  data) throws Exception {
   //     return new ResponseEntity<>(callbackService.getCIMBInquiryData(data), HttpStatus.OK);
   //  }
}
