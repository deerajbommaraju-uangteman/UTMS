package ut.microservices.loanapplicationmicroservice.dto;

import java.util.List;

public class ResponseDTO<T> {
    private List<T> rows;
    private List<ColumnDTO> columns;
    private List<ButtonDTO> button;

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

    public void setButton(List<ButtonDTO> button) {
        this.button = button;
    }



}