package ut.microservices.loanapplicationmicroservice.dto;

/**
 * ApplicationNotesDTO
 */
public class ApplicationNotesDTO {

    private 	Integer	ApplicationNotesID;
    private 	Integer	ApplicationID;
    private 	String	TypeNote;
    private 	String	Note;
    private 	String	InputBy;
    private 	String	UpdateBy;

    public Integer getApplicationNotesID() {
        return ApplicationNotesID;
    }

    public void setApplicationNotesID(Integer applicationNotesID) {
        ApplicationNotesID = applicationNotesID;
    }

    public Integer getApplicationID() {
        return ApplicationID;
    }

    public void setApplicationID(Integer applicationID) {
        ApplicationID = applicationID;
    }

    public String getTypeNote() {
        return TypeNote;
    }

    public void setTypeNote(String typeNote) {
        TypeNote = typeNote;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getInputBy() {
        return InputBy;
    }

    public void setInputBy(String inputBy) {
        InputBy = inputBy;
    }

    public String getUpdateBy() {
        return UpdateBy;
    }

    public void setUpdateBy(String updateBy) {
        UpdateBy = updateBy;
    }

    
}