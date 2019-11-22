package ut.microservices.investormicroservice.service;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import ut.microservices.investormicroservice.model.DigisignAgreement;
import ut.microservices.investormicroservice.model.LogsCIMBNiaga;
import ut.microservices.investormicroservice.repository.IGenericDAO;

@Service
public class CronjobService {
        IGenericDAO<LogsCIMBNiaga> logsCIMBNiagaDAO;
        IGenericDAO<DigisignAgreement> digisignAgreementDAO;

        @Autowired
        KafkaTemplate<String, String> kafkaTemplate;

        @Autowired
        ObjectMapper objectMapper;

        @Autowired
        public void setLogsCIMBNiagaDAO(IGenericDAO<LogsCIMBNiaga> logsCIMBNiagaDAO) {
                this.logsCIMBNiagaDAO = logsCIMBNiagaDAO;
                this.logsCIMBNiagaDAO.setClazz(LogsCIMBNiaga.class);
        }

        @Autowired
        public void setDigisignAgreementDAO(IGenericDAO<DigisignAgreement> digisignAgreementDAO) {
                this.digisignAgreementDAO = digisignAgreementDAO;
                this.digisignAgreementDAO.setClazz(DigisignAgreement.class);
        }


        public void loanDisbursed(String loanAppID,Double loanAmount) throws Exception {
           //CallBack from cronJob after disbursing loan
           LogsCIMBNiaga logsCIMBNiaga=logsCIMBNiagaDAO.findBy("loanAppID", loanAppID, "status", "PENDING").get(0);
           logsCIMBNiaga.setStatus("SUCCESS");
           DigisignAgreement loanDetails=digisignAgreementDAO.findBy("loanAppID", loanAppID).get(0);
           String ApplicationID=Integer.toString(loanDetails.getApplicationID());
           String ApplicantID=Double.toString(loanDetails.getApplicantID());
           HashMap<String,String> data=new HashMap<String,String>();
           data.put("loanAmount", Double.toString(loanAmount));
           data.put("loanAppID", loanAppID);
           data.put("ApplicantID", ApplicantID);
           data.put("ApplicationID", ApplicationID);
           kafkaTemplate.send("loanDisbursed",objectMapper.writeValueAsString(data));
	}


    
}