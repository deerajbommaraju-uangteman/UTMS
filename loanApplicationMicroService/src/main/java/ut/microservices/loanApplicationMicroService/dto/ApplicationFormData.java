package ut.microservices.loanApplicationMicroService.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ApplicationFormData implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @NotEmpty
    @JsonProperty(value = "apmt_full_name", required = true)
    private String apmt_full_name;


}