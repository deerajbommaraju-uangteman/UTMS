package ut.microservices.loanapplicationmicroservice.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import ut.microservices.loanapplicationmicroservice.dto.*;
import ut.microservices.loanapplicationmicroservice.model.*;
import ut.microservices.loanapplicationmicroservice.repository.*;
import ut.microservices.loanapplicationmicroservice.service.*;
/**
 * DVServices
 */
@Service
public class DVServices {

    @Value("${application.emailValidator}")
    private Boolean emailVaildate;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    IGenericDAO<ApplicantData> applicantDAO;
    IGenericDAO<ApplicationData> applicationDAO;
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

    IGenericDAO<ApplicationNotesModel> applicationNotesDAO;
    @Autowired
    public void setApplicationNotesDAO(IGenericDAO<ApplicationNotesModel> DAOToSet) {
        applicationNotesDAO = DAOToSet;
        applicationNotesDAO.setClazz(ApplicationNotesModel.class);
    }

    @Autowired
    private ObjectMapper objectMapper;

    public @ResponseBody ResponseDTO<ApplicationDTO> getAvailableLoans() throws Exception {
        //System.out.println("test:::"+this.emailVaildate);
        List<ApplicationData> LoanApplicationsList = applicationDAO.findValueByColumn("Status", "DV");
        return getApplicationResponseBody(LoanApplicationsList);
    }
    
    public String dvAddNotes(ApplicationNotesModel applicationNotes) throws Exception {
        String responseTempID = applicationNotesDAO.save(applicationNotes).toString();
        System.out.println(responseTempID);
        return responseTempID;
    }

    public ResponseDTO<ApplicationNotesDTO> dvViewNotes(String applicationID) throws Exception {
        //String val=applicationID.getApplicationID().toString();
        List<ApplicationNotesModel> ApplicationNotesList = applicationNotesDAO.findValueByColumn("ApplicationID", applicationID);
        System.out.println(ApplicationNotesList.size());
        return getApplicationNotesResponseBody(ApplicationNotesList);
    }

    public ResponseDTO<ApplicationNotesDTO> getApplicationNotesResponseBody(List<ApplicationNotesModel> applicationList) {
        Iterator<ApplicationNotesModel> iterator=applicationList.iterator();
        ResponseDTO<ApplicationNotesDTO> response=new ResponseDTO<ApplicationNotesDTO>();
        List<ApplicationNotesDTO> rows=new LinkedList<ApplicationNotesDTO>();
        int key=0;
        while(iterator.hasNext()){
          key++;
          ApplicationNotesModel application=iterator.next();
          System.out.println(application);
          ApplicationNotesDTO availableLoansDTO=new ApplicationNotesDTO();
          availableLoansDTO.setApplicationID(application.getApplicationID());
          availableLoansDTO.setApplicationNotesID(application.getApplicationNotesID());
          availableLoansDTO.setTypeNote(application.getTypeNote());
          availableLoansDTO.setNote(application.getNote());
          availableLoansDTO.setInputBy(application.getInputBy());          
          rows.add(availableLoansDTO);
        }
        response.setRows(rows);
        HashMap<String,String> tableColumns=new HashMap<String,String>();
        tableColumns.put("typeNote", "Note Type");
        tableColumns.put("note", "Note");
        tableColumns.put("inputBy", "Input By");
        List<ColumnDTO> columns=new LinkedList<ColumnDTO>();
        for(Map.Entry<String,String> entry : tableColumns.entrySet()){
          ColumnDTO columnDTO=new ColumnDTO();
          columnDTO.setKey(entry.getKey());
          columnDTO.setTitle(entry.getValue());
          columnDTO.setDataIndex(entry.getKey());
          columns.add(columnDTO);
        }
        response.setColumns(columns);
        System.out.println(response);
        return response;
      }

    public @ResponseBody ApplicantData dvEditApplicationPersonalDetails(HashMap<String, String> ApplicationID) throws JsonProcessingException {
        HashMap<String, Object> data = new HashMap<String, Object>();
        // Available Loan state in LoanInvestment table is 'N'
        ApplicationData applicationData = applicationDAO.findValueByColumn("ApplicationID", ApplicationID.get("id")).get(0);
        ApplicantData applicantData=applicantDAO.findValueByColumn("ApplicantID",applicationData.getApplicationApplicantID().toString()).get(0);
        return applicantData;
    }

    public @ResponseBody String dvSubmitApplicationPersonalDetails(ApplicantData applicantData){
        applicantDAO.updateOne(applicantData);
        return "Updated Succesfully";
    }

    public @ResponseBody String dvEditLoanDetails(ApplicantData ApplicationID) throws JsonProcessingException {
        // System.out.println("app::"+ApplicationID);
        // HashMap<String, Object> data = new HashMap<String, Object>();
        // // Available Loan state in LoanInvestment table is 'N'
        // ApplicantData fundingLoansList = applicantDAO.findValueByColumn("ApplicantID", ApplicationID).get(0);
        // System.out.println("data::"+fundingLoansList);
        // data.put("applicantLoanData", objectMapper.writeValueAsString(fundingLoansList));
        // return objectMapper.writeValueAsString(data);
        return "success";
    }
    
    public void approvedLoan(String ApplicationID) throws JsonProcessingException {
        ApplicationData applicationData=applicationDAO.findValueByColumn("ApplicationID",ApplicationID).get(0);
        applicationData.setStatus("ST");
        applicationDAO.updateOne(applicationData);
        ApplicationStatusModel applicationStatus=new ApplicationStatusModel();
        applicationStatus.setApplicationStatusData(Integer.parseInt(ApplicationID), applicationData.getLoanApplicationID(), 0, 3, "DV", 3, "ST");
        applicationStatusDAO.save(applicationStatus);
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("loanAppID", applicationData.getLoanApplicationID());
        map.put("loanAmount",applicationData.getLoanAmount());
        map.put("ApplicationID",applicationData.getApplicationID());
        map.put("LoanTenor",applicationData.getLoanDaysLength());
        //kafkaTemplate.send("loanApproved",objectMapper.writeValueAsString(map));
    }
    
    public void rejectedLoan(String ApplicationID) {
        ApplicationData applicationData=applicationDAO.findValueByColumn("ApplicationID",ApplicationID).get(0);
        applicationData.setStatus("T");
        applicationDAO.updateOne(applicationData);
        ApplicationStatusModel applicationStatus=new ApplicationStatusModel();
        applicationStatus.setApplicationStatusData(Integer.parseInt(ApplicationID), applicationData.getLoanApplicationID(), 0, 3, "DV", 92, "T");
        applicationStatusDAO.save(applicationStatus);
        
    }

    public ResponseDTO<ApplicationDTO> getApplicationResponseBody(List<ApplicationData> applicationList) {
        Iterator<ApplicationData> iterator=applicationList.iterator();
        ResponseDTO<ApplicationDTO> response=new ResponseDTO<ApplicationDTO>();
        List<ApplicationDTO> rows=new LinkedList<ApplicationDTO>();
        int key=0;
        while(iterator.hasNext()){
          key++;
          ApplicationData application=iterator.next();
          ApplicationDTO availableLoansDTO=new ApplicationDTO();
          availableLoansDTO.setApplicationApplicantID(application.getApplicationApplicantID());
          availableLoansDTO.setApplicationID(application.getApplicationID());
          availableLoansDTO.setLoanAmount(application.getLoanAmount());
          availableLoansDTO.setLoanApplicationID(application.getLoanApplicationID());
          availableLoansDTO.setLoanDaysLength(application.getLoanDaysLength());          
          rows.add(availableLoansDTO);
        }
        response.setRows(rows);
        HashMap<String,String> tableColumns=new HashMap<String,String>();
        tableColumns.put("applicationApplicantID", "Applicant ID");
        tableColumns.put("loanApplicationID", "Loan ID");
        tableColumns.put("loanAmount", "Loan Amount");
        tableColumns.put("loanDaysLength", "Loan Tenor");
        tableColumns.put("applicationID", "Action");
        List<ColumnDTO> columns=new LinkedList<ColumnDTO>();
        for(Map.Entry<String,String> entry : tableColumns.entrySet()){
          ColumnDTO columnDTO=new ColumnDTO();
          columnDTO.setKey(entry.getKey());
          columnDTO.setTitle(entry.getValue());
          columnDTO.setDataIndex(entry.getKey());
          columns.add(columnDTO);
        }
        List<ButtonDTO> buttons=new LinkedList<ButtonDTO>();
        ButtonDTO buttonDTO=new ButtonDTO();
        buttonDTO.setTitle("Action");
        buttonDTO.setKey("applicationID");
        buttonDTO.setAction("approveLoan");
        buttons.add(buttonDTO);
        response.setColumns(columns);
        response.setButton(buttons);
        return response;
      }

      
      
}