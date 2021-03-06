package ut.microservices.investormicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GlobalException extends Exception{

    private static final long serialVersionUID = 1L;

    public GlobalException(String message){
        super(message);
    }
}