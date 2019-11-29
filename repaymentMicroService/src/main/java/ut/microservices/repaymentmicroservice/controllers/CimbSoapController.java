package ut.microservices.repaymentmicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import cimb3rdparty.billpaymentws.InquiryRq;
import cimb3rdparty.billpaymentws.InquiryRs;
import ut.microservices.repaymentmicroservice.services.CimbSoapService;


@Endpoint
public class CimbSoapController {

    @Autowired
    private CimbSoapService cimbsoapService;
    
    // @PostMapping(value = "/cimb/virtual-account", consumes = MediaType.APPLICATION_XML_VALUE)
    @PayloadRoot(namespace = "http://CIMB3rdParty/BillPaymentWS", localPart = "InquiryRq")
    @ResponsePayload
    public InquiryRs getCIMBInquiryData(@RequestPayload InquiryRq requestData){
       InquiryRs response = new InquiryRs();
       response.setAmount(1000);
       response.setPaidAmount(1000);
       return response;
    //   callbackService.getCIMBInquiryData(requestData);
    }

}