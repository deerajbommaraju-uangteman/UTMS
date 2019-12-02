package ut.microservices.repaymentmicroservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class CimbSoapService {

    private static final Map<String, Object> users = new HashMap<>();

    @Value("${cimbniaga.company_code_empty}")
    private String COMPANY_CODE_EMPTY;

    // @Value("${new java.text.SimpleDateFormat('${yyyy-mm-dd}').parse('${services_fee_live.live_date}')}")
    private Date SERVICE_FEE_LIVE_DATE;

    public Date getCimbInquiryResponse(){     
        return SERVICE_FEE_LIVE_DATE;
        
    }
}
