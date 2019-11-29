package ut.microservices.repaymentmicroservice.services;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import ut.microservices.repaymentmicroservice.dao.IGenericDAO;
import ut.microservices.repaymentmicroservice.models.ApplicantData;
import ut.microservices.repaymentmicroservice.models.ApplicationData;
import ut.microservices.repaymentmicroservice.models.CustomerLoanData;
import ut.microservices.repaymentmicroservice.models.CustomerLoanInstallmentRepayment;
import ut.microservices.repaymentmicroservice.models.CustomerLoanRepayment;
import ut.microservices.repaymentmicroservice.models.CustomerPrimaryData;
import ut.microservices.repaymentmicroservice.models.CustomerVaHistory;
import ut.microservices.repaymentmicroservice.models.LogDokuBca;

@Service
@Transactional
public class DatabaseService {

    IGenericDAO<ApplicantData> applicantDataDAO;
    IGenericDAO<ApplicationData> applicationDataDAO;
    IGenericDAO<CustomerVaHistory> customerVaHistoryDAO;
    IGenericDAO<CustomerLoanRepayment> custLoanRepaymentDAO;
    IGenericDAO<CustomerLoanInstallmentRepayment> clirDAO;
    IGenericDAO<LogDokuBca> logDokuBcaDAO;
    IGenericDAO<CustomerLoanData> custLoanDataDAO;
    IGenericDAO<CustomerPrimaryData> custPrimaryDataDAO;

    @Autowired
    public void setApplicantDataDAO(IGenericDAO<ApplicantData> applicantDataDAO){
        this.applicantDataDAO = applicantDataDAO;
        applicantDataDAO.setClazz(ApplicantData.class);
    }

    @Autowired
    public void setApplicationDataDAO(IGenericDAO<ApplicationData> applicationDataDAO){
        this.applicationDataDAO = applicationDataDAO;
        applicationDataDAO.setClazz(ApplicationData.class);
    }

    @Autowired
    public void setCustomerVaHistoryDAO(IGenericDAO<CustomerVaHistory> customerVaHistoryDAO) {
        this.customerVaHistoryDAO = customerVaHistoryDAO;
        customerVaHistoryDAO.setClazz(CustomerVaHistory.class);
    }

    @Autowired
    public void setCustLoanRepaymentDAO(IGenericDAO<CustomerLoanRepayment> custLoanRepaymentDAO) {
        this.custLoanRepaymentDAO = custLoanRepaymentDAO;
        custLoanRepaymentDAO.setClazz(CustomerLoanRepayment.class);
    }

    @Autowired
    public void setCustLoanInstallmentDAO(IGenericDAO<CustomerLoanInstallmentRepayment> clirDAO) {
        this.clirDAO = clirDAO;
        clirDAO.setClazz(CustomerLoanInstallmentRepayment.class);
    }

    @Autowired
    public void setLogDokuBcaDAO(IGenericDAO<LogDokuBca> logDokuBcaDAO) {
        this.logDokuBcaDAO = logDokuBcaDAO;
        logDokuBcaDAO.setClazz(LogDokuBca.class);
    }

    @Autowired
    public void setCustLoanDataDAO(IGenericDAO<CustomerLoanData> custLoanDataDAO) {
        this.custLoanDataDAO = custLoanDataDAO;
        custLoanDataDAO.setClazz(CustomerLoanData.class);
    }

    @Autowired
    public void setCustPrimaryDataDAO(IGenericDAO<CustomerPrimaryData> custPrimaryDataDAO) {
        this.custPrimaryDataDAO = custPrimaryDataDAO;
        custPrimaryDataDAO.setClazz(CustomerPrimaryData.class);
    }

    @Autowired
    private RepaymentService repaymentService;

    @Autowired
    private InstallmentService installmentService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // @RequestMapping(value = "/loanDisbursed", method = RequestMethod.POST)
    // public void loanDataToRepayment(Integer ApplicantID, String ApplicationID, String LoanApplicationID,Double Amount) throws Exception {
    //     HashMap<String, Object> map = new HashMap<>();
    //     map.put("ApplicantID", ApplicantID);
    //     map.put("ApplicationID", ApplicationID);
    //     map.put("LoanApplicationID", LoanApplicationID);
    //     map.put("RepaymentAmount", Amount);
    //     // Send data to repayment micro service
    //     kafkaTemplate.send("loanDisbursed", objectMapper.writeValueAsString(map));
    // }
    
    @KafkaListener(topics = "loanDisbursed", groupId = "myGroupId")
    public void loanDisbursedData(ConsumerRecord<String, String> data, Acknowledgment ack) throws Exception{  
        HashMap<String, String>  dataMap= new ObjectMapper().readValue(data.value(), new TypeReference<HashMap<String,String>>() {});  
        CustomerLoanRepayment clr = new CustomerLoanRepayment();
        clr.setApplicantID(dataMap.get("ApplicantID").toString());
        clr.setLoanApplicationID(dataMap.get("LoanApplicationID").toString());
        clr.setRepaymentAmount(Double.parseDouble(dataMap.get("RepaymentAmount").toString()));
        clr.setDueDate(new java.util.Date(Calendar.getInstance().getTime().getTime()));
        clr.setPartialStatus("N");
        custLoanRepaymentDAO.save(clr);

        this.postDisbusementSaveDetails(dataMap.get("LoanApplicationID").toString());

        ApplicationData apliData = applicationDataDAO.findValueByColumn("ApplicationID", dataMap.get("ApplicationID").toString()).get(0);       
        if(apliData.getIsInstallment().equals("Y")){        
            installmentService.insertInstallmentLoanRepayment(apliData, clr);
        }
    
        ack.acknowledge();
    }

    public void postDisbusementSaveDetails(String LoanApplicationID) throws Exception {

        Map<String , Map<String, Object>> lamsData= repaymentService.loanDataFromLAMS(LoanApplicationID);
        
        // save details to CustomerLoanData
        CustomerLoanData cld = new CustomerLoanData();
        cld.setApplicantID(Integer.parseInt(lamsData.get("ApplicationData").get("applicationApplicantID").toString()));
        cld.setLoanApplicationID(lamsData.get("ApplicationData").get("LoanApplicationID").toString());
        cld.setLoanDaysLength((Integer) lamsData.get("ApplicationData").get("LoanDaysLength"));
        cld.setLoanStartDatetime(new java.util.Date(Calendar.getInstance().getTime().getTime()));
        // cld.setLoanDueDatetime((Date) lamsData.get("ApplicationData").get("LoanDueDateTime"));
        cld.setLoanRepayAmount((Double) lamsData.get("ApplicationData").get("LoanAmount"));
        cld.setCreated(new java.util.Date(Calendar.getInstance().getTime().getTime()));
        cld.setCusStatusID(2);
        cld.setStatus("N");
        custLoanDataDAO.save(cld);

        // String brwId = Float.toHexString((float) lamsData.get("ApplicantData").get("PersonalIDNumber"));

        //save details to CustomerPrimaryData
        CustomerPrimaryData  cd= new CustomerPrimaryData();
        cd.setApplicantID((int) lamsData.get("ApplicantData").get("applicantID"));
        // cd.setBorrowerID(brwId);
        cd.setFullName(lamsData.get("ApplicantData").get("Fullname").toString());
        cd.setGender(lamsData.get("ApplicantData").get("Gender").toString());
        cd.setEmailAddress(lamsData.get("ApplicantData").get("EmailAddress").toString());
        cd.setPassword("testing");
        cd.setPersonalIDNumber(lamsData.get("ApplicantData").get("PersonalIDNumber").toString());
        cd.setMobileNumber(lamsData.get("ApplicantData").get("MobileNumber").toString());
        cd.setDomicileCountry(102);
        cd.setFamily1Country(102);
        cd.setEmployerCountry(102);
        custPrimaryDataDAO.save(cd);

    }
}