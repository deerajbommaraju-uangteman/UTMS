package ut.microservices.loanApplicationMicroService.service;

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

import ut.microservices.loanApplicationMicroService.model.ApplicantData;
import ut.microservices.loanApplicationMicroService.model.ApplicationData;
import ut.microservices.loanApplicationMicroService.model.TempApplicantDataModel;
import ut.microservices.loanApplicationMicroService.repository.IGenericDao;

@Service
public class LoanApplicationService {

    private static final InputStream Jsonparser = null;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    IGenericDao<TempApplicantDataModel> tempApplicantDao;
    IGenericDao<ApplicantData> applicantDao;
    IGenericDao<ApplicationData> applicationDao;

    @Autowired
    public void setApplicantDao(IGenericDao<ApplicantData> daoToSet) {
        applicantDao = daoToSet;
        applicantDao.setClazz(ApplicantData.class);
    }

    @Autowired
    public void setApplicationDao(IGenericDao<ApplicationData> daoToSet) {
        applicationDao = daoToSet;
        applicationDao.setClazz(ApplicationData.class);
    }

    @Autowired
    public void setTempDao(IGenericDao<TempApplicantDataModel> daoToSet) {
        tempApplicantDao = daoToSet;
        tempApplicantDao.setClazz(TempApplicantDataModel.class);
    }

    @Autowired
    private ObjectMapper objectMapper;

    public String newApplicationStarted(TempApplicantDataModel application) {
            System.out.println("application::"+application);
            String emailvaildate=emailValidate(application.getEmailAddress());
            String responseTempID = tempApplicantDao.save(application).toString();
            return responseTempID;
    }

    public String newApplicationEnded(TempApplicantDataModel application) {
        List<TempApplicantDataModel> applicantList = tempApplicantDao.findValueByColumn("ID", application.getID().toString());
        TempApplicantDataModel applicantData = applicantList.get(0);
        applicantData.setformData(application);
        tempApplicantDao.updateOne(applicantData);
        if(application.getFormID().equals("form9")){
        Boolean CRE_accept = this.CRECheck();
        if(CRE_accept){
            int applicantID=this.newApplicationReceived(applicantData);
            applicantData.setApplicantID(applicantID);
            tempApplicantDao.updateOne(applicantData);
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
            int applicantID =(int) applicantDao.save(applicantData); 
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
            String applicationID= applicationDao.save(applicationData).toString();
            return applicantID;
        }  catch (Exception e) {
            e.printStackTrace();
        }
		return 0;
    }
    

    public String getApplicationData(String ApplicationID) throws JsonProcessingException {
        HashMap<String, Object> data = new HashMap<String, Object>();
        ApplicationData applicationData = applicationDao.findValueByColumn("LoanApplicationID", ApplicationID).get(0);
        ApplicantData applicantData = applicantDao.findValueByColumn("ApplicantID", applicationData.getApplicationApplicantID().toString()).get(0);
        data.put("ApplicantData", applicantData);
        data.put("ApplicationData", applicationData);
        System.out.println("data::"+data);
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