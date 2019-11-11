package ut.microservices.repaymentMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class RepaymentMicroServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RepaymentMicroServiceApplication.class, args);
	}

}
