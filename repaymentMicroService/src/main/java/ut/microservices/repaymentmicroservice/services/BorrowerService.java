package ut.microservices.repaymentmicroservice.services;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.microservices.repaymentmicroservice.dao.IGenericDAO;
import ut.microservices.repaymentmicroservice.models.ApplicantData;
import ut.microservices.repaymentmicroservice.models.ApplicationData;
import ut.microservices.repaymentmicroservice.models.CustomerLoanData;
import ut.microservices.repaymentmicroservice.models.CustomerLoanInstallmentRepayment;
import ut.microservices.repaymentmicroservice.models.CustomerLoanRepayment;
import ut.microservices.repaymentmicroservice.models.CustomerPrimaryData;
import ut.microservices.repaymentmicroservice.models.CustomerStaticVaActiveLoan;
import ut.microservices.repaymentmicroservice.models.CustomerVaHistory;
import ut.microservices.repaymentmicroservice.models.LogDokuBca;
import ut.microservices.repaymentmicroservice.models.LogsArtajasa;
import ut.microservices.repaymentmicroservice.models.VaArtajasa;
import ut.microservices.repaymentmicroservice.models.views.GetVAPaymentDetails;

@Service
@Transactional
public class BorrowerService {

    IGenericDAO<ApplicantData> applicantDataDAO;
    IGenericDAO<ApplicationData> applicationDataDAO;
    IGenericDAO<CustomerVaHistory> customerVaHistoryDAO;
    IGenericDAO<CustomerLoanRepayment> custLoanRepaymentDAO;
    IGenericDAO<CustomerLoanInstallmentRepayment> clirDAO;
    IGenericDAO<LogDokuBca> logDokuBcaDAO;
    IGenericDAO<CustomerLoanData> custLoanDataDAO;
    IGenericDAO<CustomerPrimaryData> custPrimaryDataDAO;
    IGenericDAO<VaArtajasa> vaArtajasaDAO;
    IGenericDAO<LogsArtajasa> logsArtajasaDAO;
    IGenericDAO<CustomerStaticVaActiveLoan> custStaticVaActiveLoanDAO;
    IGenericDAO<GetVAPaymentDetails> getVAPaymentDetailsDAO;

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
    public void setvaArtajasaDAO(IGenericDAO<VaArtajasa> vaArtajasaDAO) {
        this.vaArtajasaDAO = vaArtajasaDAO;
        vaArtajasaDAO.setClazz(VaArtajasa.class);
    }

    @Autowired
    public void setLogsArtajasaDAO(IGenericDAO<LogsArtajasa> logsArtajasaDAO) {
        this.logsArtajasaDAO = logsArtajasaDAO;
        logsArtajasaDAO.setClazz(LogsArtajasa.class);
    }

    @Autowired
    public void setCustomerStaticVaActiveLoanDAO(IGenericDAO<CustomerStaticVaActiveLoan> custStaticVaActiveLoanDAO) {
        this.custStaticVaActiveLoanDAO = custStaticVaActiveLoanDAO;
        custStaticVaActiveLoanDAO.setClazz(CustomerStaticVaActiveLoan.class);
    }
    
            
    @Autowired
    public void setVAPaymentDetailsDAO(IGenericDAO<GetVAPaymentDetails> getVAPaymentDetailsDAO){
        this.getVAPaymentDetailsDAO = getVAPaymentDetailsDAO;
        getVAPaymentDetailsDAO.setClazz(GetVAPaymentDetails.class);
    }

    @Autowired
    private DokuPaymentService dokuPaymentService;

    @Autowired
    private CimbNiagaPaymentService cimbPaymentService;

    @Autowired
    private ArtajasaPaymentService artajasaPaymentService;

    @Autowired
    private ObjectMapper objectMapper;

    public void getBorrowerIndex(HashMap<String, Object> authData){
        CustomerLoanData cld = custLoanDataDAO.findValueByColumn("ApplicantID", authData.get("BorrowerID").toString()).get(0);

        ApplicationData apli = applicationDataDAO.findValueByColumn("ApplicationApplicantID", authData.get("BorrowerID").toString()).get(0);

        if(apli.getIsInstallment().equals("Y")){


        }
        else{
            if(cld.getStatus().equals("N")){
                this.getOutstanding(apli.getLoanApplicationID());
            }else{
                
            }
        }
    }

    public void getOutstanding(String LoanApplicationID){

    }

    public void getVAPaymentDetails(String applicationID){
        List<GetVAPaymentDetails> cld= getVAPaymentDetailsDAO.findAll();
        
        // CustomerVaHistory va = customerVaHistoryDAO.findActiveVAByApplicantID(applicantID);


    }
}