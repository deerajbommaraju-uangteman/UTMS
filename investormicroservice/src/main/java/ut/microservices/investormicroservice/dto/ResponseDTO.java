package ut.microservices.investormicroservice.dto;

import java.util.HashMap;
import java.util.List;

public class ResponseDTO<T> {
    private List<T> data;
    private List<ColumnDTO> columns;
    private List<ButtonDTO> button;
    private HashMap<String,String> additionalData;



    public List<ColumnDTO> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnDTO> columns) {
        this.columns = columns;
    }

    public List<ButtonDTO> getButton() {
        return button;
    }

    public void setButton(List<ButtonDTO> buttons) {
        this.button = buttons;
    }

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