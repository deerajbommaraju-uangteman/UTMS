package ut.microservices.repaymentmicroservice.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ut.microservices.repaymentmicroservice.dao.IGenericDAO;
import ut.microservices.repaymentmicroservice.dto.BorrowerLoanDetailsDTO;
import ut.microservices.repaymentmicroservice.dto.GetOutstandingDTO;
import ut.microservices.repaymentmicroservice.dto.InstallmentDetailsDTO;
import ut.microservices.repaymentmicroservice.models.*;
import ut.microservices.repaymentmicroservice.models.views.*;

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
    IGenericDAO<GetOutStandingDataView> getOutStandingDataViewDAO;
    
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
    public void setOutStandingDataViewDAO(IGenericDAO<GetOutStandingDataView> getOutStandingDataViewDAO){
        this.getOutStandingDataViewDAO = getOutStandingDataViewDAO;
        getOutStandingDataViewDAO.setClazz(GetOutStandingDataView.class);
    }

    @Autowired
    private LoanOutstandingService loanOutstandingService;

    // @Value("${new java.text.SimpleDateFormat('${yyyy-mm-dd}').parse('${services_fee_live.live_date}')}")
    // private Date SERVICE_FEE_LIVE_DATE;

    public void getBorrowerIndex(HashMap<String, Object> authData) throws Exception {
        CustomerLoanData cld = custLoanDataDAO.findValueByColumn("ApplicantID", authData.get("BorrowerID").toString()).get(0); // authData.get("CldID")

        ApplicationData apli = applicationDataDAO.findValueByColumn("ApplicationApplicantID", authData.get("BorrowerID").toString()).get(0);

        // Installment loan get outstanding details
        if(apli.getIsInstallment() != null && apli.getIsInstallment().equals("Y")){
            // Date service_fee_live = \Config.get("services.services_fee_live.live_date");
            this.getOutstanding(apli.getLoanApplicationID());

        }
        else{
            if(cld.getStatus().equals("N")){
                this.getOutstanding(apli.getLoanApplicationID());
            }else{
                
            }
        }
    }

    public GetOutstandingDTO<InstallmentDetailsDTO> getOutstanding(String loanApplicationID) throws Exception{
        GetOutstandingDTO<InstallmentDetailsDTO> response = new GetOutstandingDTO<InstallmentDetailsDTO>();
        List<GetOutStandingDataView> dataList = getOutStandingDataViewDAO.findValueByColumn("CldLoanApplicationID", loanApplicationID);

        if(dataList.size() > 0){
            GetOutStandingDataView loanData = dataList.get(0);

            if(loanData !=null && loanData.getIsInstallment().equals("N")){
                loanOutstandingService.getLoanOutstandingDetails(loanData.getCldLoanApplicationID(), loanData.getCldLoanAmount(), loanData.getCldLoanStartDatetime(), loanData.getCldLoanDueDatetime(), loanData.getCldPromoCode(), true);

                CustomerLoanRepayment clr = custLoanRepaymentDAO.findValueByColumn("LoanApplicationID", loanApplicationID).get(0);
            }
            else{
                // installment loan logic
            }
        }else{
            response.setMessage("Loan ID is not yet disbursed");
        }
        return response;
    }

    // get payment details
    public BorrowerLoanDetailsDTO getVAPaymentDetails(String loanApplicationID){
        
        BorrowerLoanDetailsDTO resultdata = new BorrowerLoanDetailsDTO();
        ApplicationData apliData = applicationDataDAO.findValueByColumn("LoanApplicationID", loanApplicationID).get(0);
        GetVAPaymentDetails cld= getVAPaymentDetailsDAO.findValueByColumn("CldApplicantID", apliData.getApplicationApplicantID().toString()).get(0);

        List<CustomerVaHistory> vaList = customerVaHistoryDAO.findActiveVAByApplicantID(cld.getCldApplicantID().toString());
        List<CustomerLoanRepayment> clrList = custLoanRepaymentDAO.findValueByColumn("LoanApplicationID", cld.getApliLoanApplicationID());

        if(vaList.size() > 0){
            CustomerVaHistory va = vaList.get(0);
            resultdata.setCvhId(va.getID());
            resultdata.setVaNumber(va.getVaNumber());
            resultdata.setRepayAmount(va.getAmountToPay());
            
        }else{ 
            // redirect to user/installment?apli_id=
        }

        if(clrList.size() >0){
            CustomerLoanRepayment clr = clrList.get(0);
            if(cld.getIsInstallment() != null && cld.getIsInstallment().equalsIgnoreCase("N")){
                resultdata.setPaymentMethod(clr.getRepaymentType());
                resultdata.setLoanType(4);

                if(!clr.getVtransactionStatus().equals("P")) {
                    this.getLoanHistory();
                }
            
            }
            else{

                // if(cld.getCldStatus().equals("Y"))  redirect to user/installment?apli_id=
                List<CustomerLoanInstallmentRepayment> clirList = clirDAO.findInstallmentRepayment(clr.getId());
                if(clirList.size() >0){
                    CustomerLoanInstallmentRepayment clir = clirList.get(0);

                    resultdata.setPaymentMethod(clir.getRepaymentType());
                    resultdata.setLoanType(5);

                    // installmentService.getInstallmentLoanDetails(cld.getCldApplicantID());
                }
            }
            
        }

        resultdata.setCld(cld);
        resultdata.setLoanApplicationID(cld.getApliLoanApplicationID());
        resultdata.setApplicationID(apliData.getApplicationID());
        resultdata.setIsInstallment(cld.getIsInstallment());
        return resultdata;
    }

    public HashMap<String, String> postResetPaymentOption(String loanApplicationID){
        HashMap<String, String> response = new HashMap<>();
        ApplicationData apliData = applicationDataDAO.findValueByColumn("LoanApplicationID", loanApplicationID).get(0);

        CustomerVaHistory objVa = customerVaHistoryDAO.findActiveVAByApplicantID(apliData.getApplicationApplicantID().toString()).get(0);

        if(objVa != null){
            objVa.setIsVaActive("N");
            customerVaHistoryDAO.update(objVa);
            response.put("callback", "success");
        }
        return response;
    }

    private void getLoanHistory() {
    }
}