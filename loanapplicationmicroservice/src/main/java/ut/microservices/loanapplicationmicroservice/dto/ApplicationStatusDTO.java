package ut.microservices.loanapplicationmicroservice.dto;

/**
 * ApplicationNotesDTO
 */
public class ApplicationStatusDTO {
    public Integer ApplicationID;
    public String LoanApplicationID; 
    public Integer BoID; 
    public Integer PreviousCodeID;  
    public String PreviousApplicationStatus; 
    public Integer CodeID; 
    public String ApplicationStatus;

    public Integer getApplicationID() {
        return ApplicationID;
    }

    public void setApplicationID(Integer applicationID) {
        ApplicationID = applicationID;
    }

    public String getLoanApplicationID() {
        return LoanApplicationID;
    }

    public void setLoanApplicationID(String loanApplicationID) {
        LoanApplicationID = loanApplicationID;
    }

    public Integer getBoID() {
        return BoID;
    }

    public void setBoID(Integer boID) {
        BoID = boID;
    }

    public Integer getPreviousCodeID() {
        return PreviousCodeID;
    }

    public void setPreviousCodeID(Integer previousCodeID) {
        PreviousCodeID = previousCodeID;
    }

    public String getPreviousApplicationStatus() {
        return PreviousApplicationStatus;
    }

    public void setPreviousApplicationStatus(String previousApplicationStatus) {
        PreviousApplicationStatus = previousApplicationStatus;
    }

    public Integer getCodeID() {
        return CodeID;
    }

    public void setCodeID(Integer codeID) {
        CodeID = codeID;
    }

    public String getApplicationStatus() {
        return ApplicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        ApplicationStatus = applicationStatus;
    }
}