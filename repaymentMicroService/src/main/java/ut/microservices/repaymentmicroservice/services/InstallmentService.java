package ut.microservices.repaymentmicroservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ut.microservices.repaymentmicroservice.dao.IGenericDAO;
import ut.microservices.repaymentmicroservice.models.*;

@Component
@Service
@Transactional
public class InstallmentService {

    IGenericDAO<ApplicantData> applicantDataDAO;
    IGenericDAO<ApplicationData> applicationDataDAO;
    IGenericDAO<CustomerVaHistory> customerVaHistoryDAO;
    IGenericDAO<CustomerLoanRepayment> custLoanRepaymentDAO;
    IGenericDAO<CustomerLoanInstallmentRepayment> clirDAO;
    IGenericDAO<LogDokuBca> logDokuBcaDAO;

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
    private DokuPaymentService dokuPaymentService;

    @Autowired
    private ObjectMapper objectMapper;

    public void insertInstallmentLoanRepayment(){
        CustomerLoanInstallmentRepayment clir = new CustomerLoanInstallmentRepayment();
        // clir.setCustomerLoanRepaymentID();
        // clir.setIndexOfInstallment(1)
        // clir.setDueDate();
        clirDAO.save(clir);
        
    } 

}