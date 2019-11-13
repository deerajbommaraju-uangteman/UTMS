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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.internal.function.text.Concatenate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    public void setApplicantDAO(IGenericDAO<ApplicantData> DAOToSet) {
        applicantDAO = DAOToSet;
        applicantDAO.setClazz(ApplicantData.class);
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
    private ObjectMapper objectMapper;

    public String newApplicationStarted(TempApplicantDataModel application) {
            // System.out.println("application::"+application);
            String emailvaildate=emailValidate(application.getEmailAddress());
            String responseTempID = tempApplicantDAO.save(application).toString();
            return responseTempID;
    }

    public String newApplicationEnded(TempApplicantDataModel application) {
        List<TempApplicantDataModel> applicantList = tempApplicantDAO.findValueByColumn("ID", application.getID().toString());
        TempApplicantDataModel applicantData = applicantList.get(0);
        applicantData.setformData(application);
        tempApplicantDAO.updateOne(applicantData);
        if(application.getFormID().equals("form9")){
        Boolean CRE_accept = this.CRECheck();
        if(CRE_accept){
            int applicantID=this.newApplicationReceived(applicantData);
            applicantData.setApplicantID(applicantID);
            tempApplicantDAO.updateOne(applicantData);
            return "Application Saved Successfully";
            }
        }
        return application.getID().toString();
    }
    public boolean CRECheck(){
        Random r=new Random();
        int value=r.nextInt(5);
        if(value>=3){
            return true;
        }else{
            return false;
        }
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
            applicationData.setStatus("DV");
            String applicationID= applicationDAO.save(applicationData).toString();
            return applicantID;
        }  catch (Exception e) {
            e.printStackTrace();
        }
		return 0;
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
        String response="seccuss";
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
    

}