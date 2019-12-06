package ut.microservices.loanapplicationmicroservice.service;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.internal.function.text.Concatenate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import ut.microservices.loanapplicationmicroservice.dto.*;
import ut.microservices.loanapplicationmicroservice.model.*;
import ut.microservices.loanapplicationmicroservice.repository.*;


@Service
public class LoanApplicationService {

    private static final InputStream Jsonparser = null;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    IGenericDAO<TempApplicantDataModel> tempApplicantDAO;
    IGenericDAO<ApplicantData> applicantDAO;
    IGenericDAO<ApplicationData> applicationDAO;
    IGenericDAO<CreFeatureSetModel> creFeatureSetDAO;
    IGenericDAO<ApplicationStatusModel> applicationStatusDAO;

    @Autowired
    public void setApplicantDAO(IGenericDAO<ApplicantData> DAOToSet) {
        applicantDAO = DAOToSet;
        applicantDAO.setClazz(ApplicantData.class);
    }

    @Autowired
    public void setApplicationStatusDAO(IGenericDAO<ApplicationStatusModel> DAOToSet) {
        applicationStatusDAO = DAOToSet;
        applicationStatusDAO.setClazz(ApplicationStatusModel.class);
    }

    @Autowired
    public void setApplicationDAO(IGenericDAO<ApplicationData> DAOToSet) {
        applicationDAO = DAOToSet;
        applicationDAO.setClazz(ApplicationData.class);
    }

    @Autowired
    public void setTempDAO(IGenericDAO<TempApplicantDataModel> DAOToSet) {
        tempApplicantDAO = DAOToSet;
        tempApplicantDAO.setClazz(TempApplicantDataModel.class);
    }

    @Autowired
    public void setcreFeatureSetDAO(IGenericDAO<CreFeatureSetModel> DAOToSet) {
        creFeatureSetDAO = DAOToSet;
        creFeatureSetDAO.setClazz(CreFeatureSetModel.class);
    }

    @Autowired
    private ObjectMapper objectMapper;

    public String newApplicationStarted(TempApplicantDataModel application) {
        String emailvaildate=emailValidate(application.getEmailAddress());
        String checkData = this.isDataRecordInRPMS(application.getEmailAddress(), application.getMobileNumber(), application.getPersonalIDNumber());
        if(!checkData.equals("failed")){
            return "Data Already Exists";
        }    
        String responseTempID = tempApplicantDAO.save(application).toString();
        return responseTempID;
    }

    public String newApplicationEnded(TempApplicantDataModel application) {
        List<TempApplicantDataModel> applicantList = tempApplicantDAO.findValueByColumn("ID", application.getID().toString());
        TempApplicantDataModel applicantData = applicantList.get(0);
        applicantData.setformData(application);
        tempApplicantDAO.updateOne(applicantData);
        if(application.getFormID().equals("form10")){
        //Boolean CRE_accept = this.CRECheck();
        // if(CRE_accept){
            int applicantID=this.newApplicationReceived(applicantData);
            applicantData.setApplicantID(applicantID);
            tempApplicantDAO.updateOne(applicantData);
            return "Application Saved Successfully";
            // }

        }
        return application.getID().toString();
    }
    
    public int newApplicationReceived(TempApplicantDataModel application) {
        //System.out.println("before object mapper"+ application);
        try {
            
            ApplicantData applicantData = objectMapper.readValue(objectMapper.writeValueAsString(application), ApplicantData.class);
            applicantData.setApplicantIDTemp(application.getID());
            int applicantID =(int) applicantDAO.save(applicantData); 
            String LoanIdEncrypt=applicantData.getPersonalIDNumber();
            String year=String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            ApplicationData applicationData=new ApplicationData();
            //applicationData.setApplicantID(applicantID);
            applicationData.setLoanAmount(1000.00);
            applicationData.setLoanApplicationID(LoanIdEncrypt+"-"+year);
            applicationData.setLoanDaysLength(30);
            applicationData.setApplicationApplicantID(applicantID);
            applicationData.setLoanStartDateTime(Calendar.getInstance().getTime());
            applicationData.setLoanDueDateTime(Calendar.getInstance().getTime());
            applicationData.setStatus("D");
            String applicationID= applicationDAO.save(applicationData).toString();
            ApplicationStatusModel applicationStatus=new ApplicationStatusModel();
            applicationStatus.setApplicationStatusData(Integer.parseInt(applicationID), applicationData.getLoanApplicationID(), 0, 0, "", 1, "D");
            applicationStatusDAO.save(applicationStatus);
            Boolean creCheck = this.CRECheck(application,applicantID); 
            if(creCheck){
                applicationData.setStatus("DV");
                applicationDAO.save(applicationData);
                applicationStatus=new ApplicationStatusModel();
                applicationStatus.setApplicationStatusData(Integer.parseInt(applicationID), applicationData.getLoanApplicationID(), 0, 1, "D", 2, "DV");
                applicationStatusDAO.save(applicationStatus);
            
            }else{
                applicationData.setStatus("R");
                applicationDAO.save(applicationData);
                applicationStatus=new ApplicationStatusModel();
                applicationStatus.setApplicationStatusData(Integer.parseInt(applicationID), applicationData.getLoanApplicationID(), 0, 1, "D", 91, "R");
                applicationStatusDAO.save(applicationStatus);
            
            }
            return applicantID;
        }  catch (Exception e) {
            e.printStackTrace();
        }
		return 0;
    }
    
    public boolean CRECheck(TempApplicantDataModel applicantData,int applicantID) {
        List<CreFeatureSetModel> creFeatureSetList= creFeatureSetDAO.findValueByColumn("ApplicantID", String.valueOf(applicantID));
        if(creFeatureSetList.isEmpty()) {
            int gender = applicantData.getGender().equals("M")?0:1;
            int maritalStatus=0;
            switch (applicantData.getMaritalStatus().toString()){
                case "married":
                maritalStatus = 0;
                break;
            case "single":
                maritalStatus = 1;
                break;
            case "divorced":
                maritalStatus = 2;
                break;
            case "widowed":
                maritalStatus = 3;
                break;
            }
            CreFeatureSetModel creFeatureSet=new CreFeatureSetModel();
            creFeatureSet.setApplicantID(applicantData.getApplicantID());
            creFeatureSet.setAge(applicantData.getAge());
            creFeatureSet.setSex(gender);
            creFeatureSet.setMaritalStatus(maritalStatus);
            creFeatureSet.setEducation(applicantData.getEducation());
            creFeatureSet.setNetIncome(applicantData.getMonthlyIncome());
            creFeatureSet.setDependents(Integer.parseInt(applicantData.getDependents()));
            creFeatureSetDAO.save(creFeatureSet);
        }
        Random r=new Random();
        int value=r.nextInt(5);
        if(value>=3){
            return true;
        }else{
            return false;
        }
    }

    public String getApplicationData(String ApplicationID) throws JsonProcessingException {
        HashMap<String, Object> data = new HashMap<String, Object>();
        ApplicationData applicationData = applicationDAO.findValueByColumn("LoanApplicationID", ApplicationID).get(0);
        ApplicantData applicantData = applicantDAO.findValueByColumn("ApplicantID", applicationData.getApplicationApplicantID().toString()).get(0);
        data.put("ApplicantData", applicantData);
        data.put("ApplicationData", applicationData);
        // System.out.println("data::"+data);
        return objectMapper.writeValueAsString(data);
    }
    
    public String emailValidate(String email_ID){
        String response="success";
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email_ID);
        if(!matcher.matches()){
            return "failed";
        }
    return response;
    }

    public String getDistrictData(){
    return "success";
    }
    public String isDataRecordInRPMS(String emailId, String mobileNo, String ktpNo){
        // final String baseUrl = "http://localhost:9090/application-processing/testcurl";
        // RestTemplate restTemplate = new RestTemplate();
        // ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class);
        // String result = responseEntity.getBody();
        // System.out.println(result);
        //save to ApplicantData
        return "failed";
    }

    public String PostApplicationStatusData(ApplicationStatusDTO ApplicationStatus) throws JsonProcessingException {
        
        return "success";
    }

}