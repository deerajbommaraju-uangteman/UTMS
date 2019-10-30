package ut.microservices.investorMicroService.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InvestorMicroServiceAspect {

	@Before(value = "execution(* ut.microservices.investorMicroService.service.InvestorService.*(..)) and args(..)")
	public void beforeAdvice(JoinPoint joinPoint) {
		System.out.println("Before method:" + joinPoint.getSignature());
	}

	@After(value = "execution(* ut.microservices.investorMicroService.service.InvestorService.*(..)) and args(..)")
	public void afterAdvice(JoinPoint joinPoint) {
		System.out.println("After method:" + joinPoint.getSignature());
	}
}