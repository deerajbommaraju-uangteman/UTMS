package ut.microservices.repaymentmicroservice.controllers;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ut.microservices.repaymentmicroservice.dto.ArtajasaDTO;
import ut.microservices.repaymentmicroservice.services.CallbackService;

@RestController
@RequestMapping("/repayment")
public class CallbackController {

   @Autowired
   private CallbackService callbackService;

   @Autowired
   private ObjectMapper objectMapper;

   @PostMapping(value = "/callback", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE , MediaType.APPLICATION_XML_VALUE}, produces = { MediaType.APPLICATION_XML_VALUE , MediaType.TEXT_PLAIN_VALUE })
   public ResponseEntity<?> getCallbackInquiryData(@RequestParam HashMap<String, String>  vendor) throws Exception {
   HashMap<String, Object> resp = callbackService.getCallbackInquiryData(vendor);
   return new ResponseEntity<>(resp.get("message"), HttpStatus.OK);
   }

   @SuppressWarnings("unchecked")
   @PostMapping(value = "/callback/Artajasa", consumes = MediaType.APPLICATION_XML_VALUE, produces = { "application/xml" , "text/plain" })
   public ResponseEntity<?> getArtajasaCallbackInquiryData(@RequestBody ArtajasaDTO requestdata) throws Exception {
      HashMap<String, String> data = objectMapper.convertValue(requestdata, HashMap.class);
      data.put("vendor","Artajasa");
      return new ResponseEntity<>(callbackService.getCallbackInquiryData(data).get("message"), HttpStatus.OK);
   }

   @PostMapping(value = "/callback/notify", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
   public ResponseEntity<?> getCallbackNotifyData(@RequestParam HashMap<String, String>  vendor) throws Exception {
   return new ResponseEntity<>(callbackService.getCallbackNotifyData(vendor), HttpStatus.OK);
   }

   @SuppressWarnings("unchecked")
   @PostMapping(value = "/callback/notify/Artajasa", consumes = MediaType.APPLICATION_XML_VALUE, produces = { "application/xml" , "text/plain" })
   public ResponseEntity<?> getArtajasaCallbackNotifyData(@RequestBody ArtajasaDTO requestdata) throws Exception {
      HashMap<String, String> data = objectMapper.convertValue(requestdata, HashMap.class);
      data.put("vendor","Artajasa");
      return new ResponseEntity<>(callbackService.getCallbackNotifyData(data), HttpStatus.OK);
   }   


}
