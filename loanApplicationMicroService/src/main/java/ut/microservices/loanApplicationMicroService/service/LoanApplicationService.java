package ut.microservices.loanApplicationMicroService.service;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.microservices.loanApplicationMicroService.model.ApplicantData;
import ut.microservices.loanApplicationMicroService.repository.ApplicantDataRepository;

@Service
public class LoanApplicationService {
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