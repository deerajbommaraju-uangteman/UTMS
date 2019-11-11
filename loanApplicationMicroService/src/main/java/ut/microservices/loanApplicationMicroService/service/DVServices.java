package ut.microservices.loanApplicationMicroService.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import ut.microservices.loanApplicationMicroService.model.ApplicantData;
import ut.microservices.loanApplicationMicroService.model.ApplicationData;
import ut.microservices.loanApplicationMicroService.repository.IGenericDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import ut.microservices.loanApplicationMicroService.dto.ApplicationDto;
import ut.microservices.loanApplicationMicroService.dto.ButtonDto;
import ut.microservices.loanApplicationMicroService.dto.ColumnDto;
import ut.microservices.loanApplicationMicroService.dto.ResponseDto;
/**
 * DVServices
 */
@Service
public class DVServices {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

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
    private ObjectMapper objectMapper;

    public @ResponseBody ResponseDto<ApplicationDto> getAvailableLoans() throws Exception {
        List<ApplicationData> LoanApplicationsList = applicationDao.findValueByColumn("Status", "DV");
        return getResponseBody(LoanApplicationsList);
    }
    
    public @ResponseBody ApplicantData dvEditApplicationPersonalDetails(HashMap<String, String> ApplicationID) throws JsonProcessingException {
        HashMap<String, Object> data = new HashMap<String, Object>();
        // Available Loan state in LoanInvestment table is 'N'
        ApplicationData applicationData = applicationDao.findValueByColumn("ApplicationID", ApplicationID.get("id")).get(0);
        ApplicantData applicantData=applicantDao.findValueByColumn("ApplicantID",applicationData.getApplicationApplicantID().toString()).get(0);
        return applicantData;
    }

    public @ResponseBody String dvSubmitApplicationPersonalDetails(ApplicantData applicantData){
        applicantDao.updateOne(applicantData);
        return "Updated Succesfully";
    }

    public @ResponseBody String dvEditLoanDetails(ApplicantData ApplicationID) throws JsonProcessingException {
        // System.out.println("app::"+ApplicationID);
        // HashMap<String, Object> data = new HashMap<String, Object>();
        // // Available Loan state in LoanInvestment table is 'N'
        // ApplicantData fundingLoansList = applicantDao.findValueByColumn("ApplicantID", ApplicationID).get(0);
        // System.out.println("data::"+fundingLoansList);
        // data.put("applicantLoanData", objectMapper.writeValueAsString(fundingLoansList));
        // return objectMapper.writeValueAsString(data);
        return "success";
    }
    
    public void approvedLoan(String ApplicationID) throws JsonProcessingException {
        ApplicationData applicationData=applicationDao.findValueByColumn("ApplicationID",ApplicationID).get(0);
        applicationData.setStatus("ST");
        applicationDao.updateOne(applicationData);
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("loanAppID", applicationData.getLoanApplicationID());
        map.put("loanAmount",applicationData.getLoanAmount());
        map.put("ApplicationID",applicationData.getApplicationID());
        map.put("LoanTenor",applicationData.getLoanDaysLength());
        //kafkaTemplate.send("loanApproved",objectMapper.writeValueAsString(map));
    }
    
    public void rejectedLoan(String ApplicationID) {
        ApplicationData applicationData=applicationDao.findValueByColumn("ApplicationID",ApplicationID).get(0);
        applicationData.setStatus("T");
        applicationDao.updateOne(applicationData);
    }

    public ResponseDto<ApplicationDto> getResponseBody(List<ApplicationData> applicationList) {
        Iterator<ApplicationData> iterator=applicationList.iterator();
        ResponseDto<ApplicationDto> response=new ResponseDto<ApplicationDto>();
        List<ApplicationDto> rows=new LinkedList<ApplicationDto>();
        int key=0;
        while(iterator.hasNext()){
          key++;
          ApplicationData application=iterator.next();
          ApplicationDto availableLoansDto=new ApplicationDto();
          availableLoansDto.setApplicationApplicantID(application.getApplicationApplicantID());
          availableLoansDto.setApplicationID(application.getApplicationID());
          availableLoansDto.setLoanAmount(application.getLoanAmount());
          availableLoansDto.setLoanApplicationID(application.getLoanApplicationID());
          availableLoansDto.setLoanDaysLength(application.getLoanDaysLength());          
          rows.add(availableLoansDto);
        }
        response.setRows(rows);
        HashMap<String,String> tableColumns=new HashMap<String,String>();
        tableColumns.put("applicationApplicantID", "Applicant ID");
        tableColumns.put("loanApplicationID", "Loan ID");
        tableColumns.put("loanAmount", "Loan Amount");
        tableColumns.put("loanDaysLength", "Loan Tenor");
        tableColumns.put("applicationID", "Action");
        List<ColumnDto> columns=new LinkedList<ColumnDto>();
        for(Map.Entry<String,String> entry : tableColumns.entrySet()){
          ColumnDto columnDto=new ColumnDto();
          columnDto.setKey(entry.getKey());
          columnDto.setTitle(entry.getValue());
          columnDto.setDataIndex(entry.getKey());
          columns.add(columnDto);
        }
        List<ButtonDto> buttons=new LinkedList<ButtonDto>();
        ButtonDto buttonDto=new ButtonDto();
        buttonDto.setTitle("Action");
        buttonDto.setKey("applicationID");
        buttonDto.setAction("approveLoan");
        buttons.add(buttonDto);
        response.setColumns(columns);
        response.setButton(buttons);
        return response;
      }
}