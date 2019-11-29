package ut.microservices.repaymentmicroservice.configurations;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    // CIMB configs
    public static final String CIMB_PREFIX = "1109";
    public static final String CIMB_ESCROW_CODE = "0";
    public static final String CIMB_ESCROW_CODE_LENDER = "8";

    // Artajasa configs
    public static final String GENERAL_ERROR_05 = "05";
    public static final String ILLEGAL_SIGNATURE_INQ_REQUEST_01 = "01";
    public static final String INVALID_TO_ACCOUNT_76 = "76";
    public static final String ALREADY_PAID_78 = "78";
    public static final String TRANSACTION_SUCCESS_00 = "00";


    // services
    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
    // public Date services_fee_live = fmt.parse("2019-02-22");
}
