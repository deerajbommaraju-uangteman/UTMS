package ut.microservices.repaymentMicroService.services;

import java.util.Calendar;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ut.microservices.repaymentMicroService.dao.IGenericDao;
import ut.microservices.repaymentMicroService.models.*;

@Component
@Service
@Transactional
public class InstallmentService {

    IGenericDao<ApplicantData> applicantDataDao;
    IGenericDao<ApplicationData> applicationDataDao;
    IGenericDao<CustomerVaHistory> customerVaHistoryDao;
    IGenericDao<CustomerLoanRepayment> custLoanRepaymentDao;
    IGenericDao<CustomerLoanInstallmentRepayment> clirDao;
    IGenericDao<LogDokuBca> LogDokuBcaDao;

    @Autowired
    public void setApplicantDataDao(IGenericDao<ApplicantData> daoToSet){
        applicantDataDao = daoToSet;
        applicantDataDao.setClazz(ApplicantData.class);
    }

    @Autowired
    public void setApplicationDataDao(IGenericDao<ApplicationData> daoToSet){
        applicationDataDao = daoToSet;
        applicationDataDao.setClazz(ApplicationData.class);
    }

    @Autowired
    public void setCustomerVaHistoryDao(IGenericDao<CustomerVaHistory> daoToSet) {
        customerVaHistoryDao = daoToSet;
        customerVaHistoryDao.setClazz(CustomerVaHistory.class);
    }

    @Autowired
    public void setCustLoanRepaymentDao(IGenericDao<CustomerLoanRepayment> daoToSet) {
        custLoanRepaymentDao = daoToSet;
        custLoanRepaymentDao.setClazz(CustomerLoanRepayment.class);
    }

    @Autowired
    public void setCustLoanInstallmentDao(IGenericDao<CustomerLoanInstallmentRepayment> daoToSet) {
        clirDao = daoToSet;
        clirDao.setClazz(CustomerLoanInstallmentRepayment.class);
    }

    @Autowired
    public void setLogDokuBcaDao(IGenericDao<LogDokuBca> daoToSet) {
        LogDokuBcaDao = daoToSet;
        LogDokuBcaDao.setClazz(LogDokuBca.class);
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
        clirDao.save(clir);
        
    } 

}