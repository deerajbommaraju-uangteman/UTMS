package ut.microservices.loanApplicationMicroService.service;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import ut.microservices.loanApplicationMicroService.model.ApplicantData;
import ut.microservices.loanApplicationMicroService.repository.ApplicantDataRepository;

@Service
public class LoanApplicationService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ApplicantDataRepository applicantDataRepository;

    public String newApplicationReceived(HashMap<String, String> application) {
        System.out.println("before object mapper"+ application);
        try {
            ApplicantData applicantData = objectMapper.readValue(objectMapper.writeValueAsString(application), ApplicantData.class);
            applicantData.setFamily1Country(1);
            applicantData.setDomicileCountry(1);
            applicantData.setEmployerCountry(1);
            String status=emailValidator(applicantData);
            System.out.println(applicantData);
            System.out.println("after object mapper" + application);
            if(status=="reject"){
                applicantData.setBanned("B");
                applicantDataRepository.save(applicantData);
                return status;
            }
            else{
                applicantData.setBanned("U");
                HashMap<String, Object> map = new HashMap<String, Object>();
                HashMap<String, String> valueMap = new HashMap<String, String>();
                map.put("email_id", applicantData.getEmailAddress());
                valueMap.put("fullname", applicantData.getFullName());
                valueMap.put("loan_id","loan_ID");
                map.put("id", "4");
                String param = "";
                try {
                    param = objectMapper.writeValueAsString(valueMap);
                    map.put("values",param);
                    param = objectMapper.writeValueAsString(map);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                kafkaTemplate.send("sendMail", param);
                applicantDataRepository.save(applicantData); 
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
		return "Success";
	}

	private String emailValidator(ApplicantData applicantData) {
        List<ApplicantData> email = (List<ApplicantData>) applicantDataRepository.findByEmail(applicantData.getEmailAddress());
        if(email.size() == 0){
            return "success";
        }else{
            return "reject";
        }
    }

    public String getApplicationData(String status) {
		return null;
	}

}