package ut.microservices.reconcileMicroService.Models;
import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class PromoterDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String address;

    private MultipartFile statement;
}