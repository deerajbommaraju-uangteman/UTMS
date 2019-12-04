package ut.microservices.investormicroservice.dto;

import java.util.HashMap;
import java.util.List;

public class ResponseDTO<T> {
    private List<T> data;
    private HashMap<String,String> additionalData;


    public HashMap<String, String> getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(HashMap<String, String> additionalData) {
        this.additionalData = additionalData;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}