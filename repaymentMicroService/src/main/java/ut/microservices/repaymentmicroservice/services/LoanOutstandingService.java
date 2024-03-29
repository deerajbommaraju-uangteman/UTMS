package ut.microservices.repaymentmicroservice.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Math;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ut.microservices.repaymentmicroservice.dao.IGenericDAO;
import ut.microservices.repaymentmicroservice.models.*;
import ut.microservices.repaymentmicroservice.models.views.GetLoanDataView;

@Component
@Service
@Transactional
public class LoanOutstandingService {

    IGenericDAO<ApplicantData> applicantDataDAO;
    IGenericDAO<ApplicationData> applicationDataDAO;
    IGenericDAO<GetLoanDataView> getLoanDataViewDAO;
    IGenericDAO<MrLoanScheme> mrLoanSchemeDAO;
    IGenericDAO<MrLoanPromo> mrLoanPromoDAO;
    
    @Autowired
    public void setLoanDataViewDAO(IGenericDAO<GetLoanDataView> getLoanDataViewDAO){
        this.getLoanDataViewDAO = getLoanDataViewDAO;
        getLoanDataViewDAO.setClazz(GetLoanDataView.class);
    }

    @Autowired
    public void setMrLoanSchemeDAO(IGenericDAO<MrLoanScheme> mrLoanSchemeDAO){
        this.mrLoanSchemeDAO = mrLoanSchemeDAO;
        mrLoanSchemeDAO.setClazz(MrLoanScheme.class);
    }
    
    @Autowired
    public void setMrLoanPromoDAO(IGenericDAO<MrLoanPromo> mrLoanPromoDAO){
        this.mrLoanPromoDAO = mrLoanPromoDAO;
        mrLoanPromoDAO.setClazz(MrLoanPromo.class);
    }    
       
    @Value("${loanoutstanding.transmision_fee}")
    private int TRANSMISSION_FEE;
   
    @Value("${loanoutstanding.extension_fee}")
    private int EXTENSION_FEE;

    @Value("${outstanding.overdue_late_fee}")
    private double OVERDUE_LATE_FEE;
    
    @Value("${outstanding.overdue_calculate_fee_per_days}")
    private double OVERDUE_LATE_FEE_PER_DAY;
    
	public void getLoanOutstandingDetails(String cldLoanApplicationID, Double cldLoanAmount, Date cldLoanStartDatetime, Date cldLoanDueDatetime, String cldPromoCode, boolean isNewInterestRate) throws Exception{
        int calculateDays = 0;
        HashMap<String, Object> resultData = new HashMap<>();
        // Get Customer Loan Data and CUstomer Loan Repayment
        GetLoanDataView loanData = this.getLoanData(cldLoanApplicationID, "CLD");

        // Get total of grace period
        HashMap<String, Object> gracePeriodObj = getGracePeriod(cldLoanApplicationID, "GP", cldLoanAmount, cldLoanStartDatetime, cldPromoCode);
        // Get  new interest rate
        HashMap<String, Object> interestRateObj = getGracePeriod(cldLoanApplicationID, "IR", cldLoanAmount, cldLoanStartDatetime, null, isNewInterestRate);
        // Get interest rate from promo
        HashMap<String, Object> interestRatePromoObj = getGracePeriod(cldLoanApplicationID, "IR", cldLoanAmount, cldLoanStartDatetime, cldPromoCode);
        
        if(loanData.getExtensionStatus().equals("Y")){
            calculateDays = loanData.getCldLoanDaysLength() + loanData.getExtensionDay();

        }else{
            calculateDays = loanData.getCldLoanDaysLength();
        }

        Integer daysAfterGracePeriod = calculateDays + Integer.parseInt(gracePeriodObj.get("gracePeriod").toString());

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        long diff = new Date().getTime() - cldLoanStartDatetime.getTime();
        int differenceDays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        System.out.println(differenceDays);

        // Interest Rate that Used for calculating outstanding		
		Double interestRateUsed = (cldPromoCode == null || (cldPromoCode != null && loanData.getExtensionStatus().equalsIgnoreCase("Y"))) ? Double.parseDouble(interestRateObj.get("interestRate").toString())
                                  : Double.parseDouble(interestRatePromoObj.get("interestRate").toString());

        // Conditional To Get Outstanding                          
        if(differenceDays < calculateDays){
            // normal days (not late)  
            resultData.put("amountValue", this.calculateLending(cldLoanAmount, (interestRateUsed/100), differenceDays, TRANSMISSION_FEE, isNewInterestRate));
            // add Math.round()
        
        }
        else if(differenceDays >= calculateDays && differenceDays <= (calculateDays + Integer.parseInt(gracePeriodObj.get("gracePeriod").toString()))){
            // Between Due Date and grace period
            resultData.put("amountValue", this.calculateLending(cldLoanAmount, (interestRateUsed/100), calculateDays, TRANSMISSION_FEE, isNewInterestRate));
        }
        else if(differenceDays > daysAfterGracePeriod){
            // More than 1 day after grace period     
            resultData.put("amountValue", 0.1 * (this.calculateLending(cldLoanAmount, (interestRateUsed/100), calculateDays, TRANSMISSION_FEE, isNewInterestRate) 
                            + OVERDUE_LATE_FEE +  ((differenceDays - (daysAfterGracePeriod)) * OVERDUE_LATE_FEE_PER_DAY)));
        }
        else{
            resultData.put("amountValue", 0.00);
        }

        int originalDayLength = loanData.getCldLoanDaysLength() - loanData.getExtensionDay();
        // Get Amount when extension_status = Y
        if(loanData.getExtensionStatus().equals("Y") && differenceDays > calculateDays
            || (loanData.getExtensionStatus().equals("Y") &&  differenceDays <= calculateDays && differenceDays >= originalDayLength)){
                resultData.put("amountValue", Double.parseDouble(resultData.get("amountValue").toString()) + EXTENSION_FEE);
            }


    
	}

    private Double calculateLending(Double cldLoanAmount, double interestRate, int days, int transmissionFee, boolean isNewInterestRate) {
        Double amountValue ;
        if(isNewInterestRate){
            amountValue = cldLoanAmount + (cldLoanAmount * interestRate * days) + transmissionFee;
        }
        else{
            amountValue = cldLoanAmount * (Math.pow((1+ interestRate), days)) + transmissionFee;
        }
        return amountValue;
    }

    private HashMap<String, Object> getGracePeriod(String cldLoanApplicationID, String type, Double cldLoanAmount,
        Date cldLoanStartDatetime, String cldPromoCode, boolean... isNewRate) {

        int repeatNo = 4;
        HashMap<String, Object> resultData = new HashMap<>();
        // this.repeatNo = Integer.parseInt(cldLoanApplicationID.substring(-2));               
        List<MrLoanScheme> mrLoanSchemeList = null;

        // for repeat loan, set the increment value
        if(repeatNo >= 1 && repeatNo <=3){
            mrLoanSchemeList = mrLoanSchemeDAO.findInMrLoanScheme(cldLoanAmount, cldLoanStartDatetime, repeatNo);
        }else{
            mrLoanSchemeList = mrLoanSchemeDAO.findInMrLoanScheme(cldLoanAmount, cldLoanStartDatetime, 4);
        }

        if(mrLoanSchemeList.size() >0){
            MrLoanScheme mrLoanScheme = mrLoanSchemeList.get(0); 
            boolean isNewRateValue = isNewRate.length > 0 ? isNewRate[0] : false;
            switch(type){
                case "GP": 
                    resultData.put("gracePeriod", mrLoanScheme.getMlsLoanGracePeriod());
                    break;

                case "IR":
                    if(isNewRateValue){
                        resultData.put("interestRate", mrLoanScheme.getMlsNewInterestRate()); 
                        break;  
                    }else{
                        resultData.put("interestRate", mrLoanScheme.getMlsInterestRate()); 
                        break;  
                    }            
            }

            if(cldPromoCode != null){
                List<MrLoanPromo> mrLoanPromoList = mrLoanPromoDAO.findValueByColumn("MlpCode", cldPromoCode);

                if(mrLoanPromoList.size() > 0){
                    MrLoanPromo mrLoanPromo = mrLoanPromoList.get(0); 
                    if(mrLoanPromo.getMlpIsRepeat().equalsIgnoreCase("b") 
                        || (repeatNo ==1 && mrLoanPromo.getMlpIsRepeat().equalsIgnoreCase("n"))
                        || (repeatNo > 1 && mrLoanPromo.getMlpIsRepeat().equalsIgnoreCase("y"))){

                        switch(type){
                            case "GP":
                                resultData.put("gracePeriod", mrLoanPromo.getMlpLoanGracePeriod());
                                break;

                            case "IR":
                                resultData.put("interestRate", mrLoanPromo.getMlpInterestRate());
                                break;
                        }
                    }
                }else{
                    return null;
                }
            }

        }else{
            return null;
        }
        return resultData;
    }

    private GetLoanDataView getLoanData(String cldLoanApplicationID, String type) {
        GetLoanDataView data = null;

        if(type.equalsIgnoreCase("CLD")){
            List<GetLoanDataView> dataList = getLoanDataViewDAO.findValueByColumn("CldLoanApplicationID", cldLoanApplicationID);
           
            if(dataList.size() >0){
                data = dataList.get(0); 
            }
        }else{
            // ApplictionData apliData = applicationDataDAO
        }
        return data;
    }


}