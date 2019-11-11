package ut.microservices.loanApplicationMicroService.dto;

public class ColumnDto{
    public String title;
    public String dataIndex;
    public String key;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDataIndex() {
        return dataIndex;
    }
    
    public void setDataIndex(String dataIndex) {
        this.dataIndex = dataIndex;
    }
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}