package ut.microservices.investormicroservice.dto;

import java.util.HashMap;
import java.util.List;

public class ResponseDTO<T> {
    private List<T> rows;
    private List<ColumnDTO> columns;
    private List<ButtonDTO> button;
    private HashMap<String,String> additionalData;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

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

}