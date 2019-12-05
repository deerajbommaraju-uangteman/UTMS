package ut.microservices.loanapplicationmicroservice.dto;

/**
 * TestUrlDTO
 */
public class TestUrlDTO {

    public String key;
    public String type;
    public String label;
    public String required;
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public void setTestUrlData(String key,String type,String label,String required){
        this.key=key;
        this.type=type;
        this.label=label;
        this.required=required;
    }

}