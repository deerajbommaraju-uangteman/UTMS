package ut.microservices.loanapplicationmicroservice.configuration;

/**
 * ApplicantConfiguaration
 */
public class ApplicantConfiguaration {

    public static Boolean mobileConfig= true;
    public static Boolean emailVaildator = true;
    

    public static Boolean getMobileConfig() {
        return mobileConfig;
    }

    public static void setMobileConfig(Boolean mobileConfig) {
        ApplicantConfiguaration.mobileConfig = mobileConfig;
    }

    public static Boolean getEmailVaildator() {
        return emailVaildator;
    }

    public static void setEmailVaildator(Boolean emailVaildator) {
        ApplicantConfiguaration.emailVaildator = emailVaildator;
    }

}