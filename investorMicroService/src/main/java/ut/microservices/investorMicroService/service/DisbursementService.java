package ut.microservices.investormicroservice.service;

import java.util.HashMap;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import ut.microservices.investormicroservice.model.DigisignAgreement;
import ut.microservices.investormicroservice.model.LoanInvestment;
import ut.microservices.investormicroservice.repository.IGenericDAO;


@Service
@Transactional
public class DisbursementService {

    //private final String baseUrl = "http://localhost:9090/application-form/getApplicationData/";

    IGenericDAO<LoanInvestment> loanInvestmentDAO;

    @Autowired
    DatabaseService databaseService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public void setLoanInvestmentDAO(IGenericDAO<LoanInvestment> loanInvestmentDAO) {
        this.loanInvestmentDAO = loanInvestmentDAO;
        this.loanInvestmentDAO.setClazz(LoanInvestment.class);
    }

    public void disburseLoan(DigisignAgreement digisignAgreement) throws Exception {
      //Getting Data From LAMS
          // String url=baseUrl+digisignAgreement.getApplicationID();
          // RestTemplate restTemplate = new RestTemplate();
          // ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
          // JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());
          // JsonNode jnApplicationData = jsonNode.get("ApplicantData");
          // ApplicantData aplicantData =  objectMapper.readValue(jnApplicationData.toString(),ApplicantData.class);
          //   //insert record into cimbniaga
          // databaseService.insertRecordToLogsCIMBNiaga();

      // Send data to repayment micro service
      HashMap<String, Object> map = new HashMap<>();
      map.put("ApplicantID", digisignAgreement.getApplicantID());
      map.put("ApplicationID", digisignAgreement.getApplicationID());
      LoanInvestment loan=loanInvestmentDAO.findBy("applicationID", Integer.toString(digisignAgreement.getApplicationID())).get(0);
      map.put("LoanApplicationID",loan.getLoanAppID() );
      map.put("RepaymentAmount", loan.getLoanAmount());
      kafkaTemplate.send("loanDisbursed", objectMapper.writeValueAsString(map));
    }
}