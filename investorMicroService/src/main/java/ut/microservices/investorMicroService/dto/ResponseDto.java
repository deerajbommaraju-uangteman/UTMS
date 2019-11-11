package ut.microservices.investorMicroService.dto;

import java.util.HashMap;
import java.util.List;

public class ResponseDto<T> {
    private List<T> rows;
    private List<ColumnDto> columns;
    private List<ButtonDto> button;
    private HashMap<String,String> additionalData;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public List<ColumnDto> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnDto> columns) {
        this.columns = columns;
    }

    public List<ButtonDto> getButton() {
        return button;
    }

    public void setButton(List<ButtonDto> buttons) {
        this.button = buttons;
    }

    public HashMap<String, String> getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(HashMap<String, String> additionalData) {
        this.additionalData = additionalData;
    }

}