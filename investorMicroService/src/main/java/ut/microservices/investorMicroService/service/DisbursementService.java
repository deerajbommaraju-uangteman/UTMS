package ut.microservices.investorMicroService.service;

import java.util.HashMap;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ut.microservices.investorMicroService.model.ApplicantData;
import ut.microservices.investorMicroService.model.DigisignAgreement;
import ut.microservices.investorMicroService.model.LoanInvestment;
import ut.microservices.investorMicroService.repository.IGenericDao;

@Service
@Transactional
public class DisbursementService {

    private final String baseUrl = "http://localhost:9090/application-form/getApplicationData/";

    IGenericDao<LoanInvestment> loanInvestmentDao;

    @Autowired
    DatabaseService databaseService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public void setLoanInvestmentDao(IGenericDao<LoanInvestment> daoToSet) {
        loanInvestmentDao = daoToSet;
        loanInvestmentDao.setClazz(LoanInvestment.class);
    }

    public void disburseLoan(DigisignAgreement digisignAgreement) throws Exception {
      if(true){
        System.out.println("Loan Disbursed");
        return;
      }
      String url=baseUrl+digisignAgreement.getApplicationID();
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
      JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());
      JsonNode jnApplicationData = jsonNode.get("ApplicantData");
      ApplicantData aplicantData =  objectMapper.readValue(jnApplicationData.toString(),ApplicantData.class);
        //insert record into cimbniaga
      databaseService.insertRecordToLogsCIMBNiaga();
      // Send data to repayment micro service
      HashMap<String, Object> map = new HashMap<>();
      map.put("ApplicantID", digisignAgreement.getApplicantID());
      map.put("ApplicationID", digisignAgreement.getApplicationID());
      LoanInvestment loan=loanInvestmentDao.findBy("applicationID", Integer.toString(digisignAgreement.getApplicationID())).get(0);
      map.put("LoanApplicationID",loan.getLoanAppID() );
      map.put("RepaymentAmount", loan.getLoanAmount());
      kafkaTemplate.send("loanDisbursed", objectMapper.writeValueAsString(map));
    }

}