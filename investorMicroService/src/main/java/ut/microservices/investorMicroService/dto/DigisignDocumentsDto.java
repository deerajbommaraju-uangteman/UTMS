package ut.microservices.investorMicroService.dto;

public class DigisignDocumentsDto {
    private String DocumentID;
    private String DocumentLenderID;
    private String lenderAgreementStatus;
    private String applicationID;
    private String key;

    public String getDocumentID() {
        return DocumentID;
    }

    public void setDocumentID(String documentID) {
        DocumentID = documentID;
    }

    public String getDocumentLenderID() {
        return DocumentLenderID;
    }

    public void setDocumentLenderID(String documentLenderID) {
        DocumentLenderID = documentLenderID;
    }

    public String getLenderAgreementStatus() {
        return lenderAgreementStatus;
    }

    public void setLenderAgreementStatus(String lenderAgreementStatus) {
        this.lenderAgreementStatus = lenderAgreementStatus;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}