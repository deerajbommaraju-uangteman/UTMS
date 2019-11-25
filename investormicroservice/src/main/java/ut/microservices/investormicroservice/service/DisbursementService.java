package ut.microservices.investormicroservice.service;


import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ut.microservices.investormicroservice.model.ApplicantData;
import ut.microservices.investormicroservice.model.DigisignAgreement;
import ut.microservices.investormicroservice.model.LoanInvestment;
import ut.microservices.investormicroservice.repository.IGenericDAO;


@Service
@Transactional
public class DisbursementService {

    private final String baseUrl = "http://localhost:9090/application-form/getApplicationData/";

    IGenericDAO<LoanInvestment> loanInvestmentDAO;
    IGenericDAO<ApplicantData> applicantDataDAO;

    @Autowired
    DatabaseService databaseService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CronjobService cronJobService;    

    @Autowired
    public void setLoanInvestmentDAO(IGenericDAO<LoanInvestment> loanInvestmentDAO) {
        this.loanInvestmentDAO = loanInvestmentDAO;
        this.loanInvestmentDAO.setClazz(LoanInvestment.class);
    }

    @Autowired
    public void setApplicantDataDAO(IGenericDAO<ApplicantData> applicantDataDAO) {
      this.applicantDataDAO = applicantDataDAO;
      this.applicantDataDAO.setClazz(ApplicantData.class);
    }

    public void disburseLoan(DigisignAgreement digisignAgreement) throws Exception {
        LoanInvestment loan=loanInvestmentDAO.findBy("applicationID", Integer.toString(digisignAgreement.getApplicationID())).get(0);
        String url=baseUrl+loan.getLoanAppID();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());
        JsonNode jnApplicantData = jsonNode.get("ApplicantData");
        ApplicantData applicantData =  objectMapper.readValue(jnApplicantData.toString(),ApplicantData.class);
        applicantDataDAO.save(applicantData);

        databaseService.insertRecordToLogsCIMBNiaga(applicantData,loan,digisignAgreement);

        //TODO
        //For now We are invoking CronJob CallBack from here
        //Need to Uncomment following method Call in production
        cronJobService.loanDisbursed(loan.getLoanAppID(),loan.getLoanAmount());
    }
}