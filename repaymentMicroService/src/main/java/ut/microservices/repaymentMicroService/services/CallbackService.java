package ut.microservices.repaymentMicroService.services;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.HashMap;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.microservices.repaymentMicroService.dao.IGenericDao;
import ut.microservices.repaymentMicroService.models.LogsCallback;

@Service
@Transactional
public class CallbackService {

    IGenericDao<LogsCallback> logsCallbackDao;

    @Autowired
    public void setDao(IGenericDao<LogsCallback> daoToSet) {
        logsCallbackDao = daoToSet;
        logsCallbackDao.setClazz(LogsCallback.class);
    }

    private String vendors[] = { "Dokualfa", "Dokubca" };

    @Autowired
    private RepaymentService repaymentService;

    @Autowired
    ObjectMapper objectMapper;

	public String getCallbackInquiryData(HashMap<String, String> data) throws Exception {
      
        boolean init = Arrays.asList(vendors).contains(data.get("vendor"));

        InetAddress myIP=InetAddress.getLocalHost();

        // fill into logs_callback table
        LogsCallback logs = new LogsCallback();
        logs.setVendor(data.get("vendor"));
        logs.setFromIP(myIP.getHostAddress());
        logs.setContent(data.toString());
        if(init){
                logs.setIsValid(1);
                logs.setDescription("Ip Address accepted for payment Inquiry");
                logsCallbackDao.save(logs);
                switch(data.get("vendor")){
                    case "Dokualfa":
                        return repaymentService.getDokuInquiry(data);

                    case "Dokubca":
                        return repaymentService.getDokuInquiry(data);
                    default: 
                        return "Invalid vendor type";

            }
        }else{
            // fill logs callback as invalid vendor type specified!!
                logs.setIsValid(0);
                logs.setDescription("Invalid vendor type specified!!");
                logsCallbackDao.save(logs);           
        }
        
        return "callback execution failed";
    }
    
    public String getCallbackNotifyData(HashMap<String, String> data) throws Exception {

        boolean init = Arrays.asList(vendors).contains(data.get("vendor"));
        InetAddress myIP=InetAddress.getLocalHost();

        // fill into logs_callback table
        LogsCallback logs = new LogsCallback();
        logs.setVendor(data.get("vendor"));
        logs.setFromIP(myIP.getHostAddress());
        logs.setContent(data.toString());

        if(init){
                logs.setIsValid(1);
                logs.setDescription("Ip Address accepted for payment Notify");
                logsCallbackDao.save(logs);
                switch(data.get("vendor")){
                    case "Dokubca":
                        return repaymentService.setAsDokuPaid(data);
                    default: 
                        return "Invalid vendor type";

            }
        }else{
            // fill logs callback as invalid vendor type specified!!
                logs.setIsValid(0);
                logs.setDescription("Invalid vendor type specified!!");
                logsCallbackDao.save(logs);           
        }
        
        return "Notify callback execution failed";
    }
}