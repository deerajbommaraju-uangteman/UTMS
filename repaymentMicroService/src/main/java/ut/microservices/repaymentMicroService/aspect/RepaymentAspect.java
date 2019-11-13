package ut.microservices.repaymentMicroService.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepaymentAspect {

	@Before(value = "execution(* ut.microservices.repaymentMicroService.services.RepaymentService.*(..)) and args(..)")
	public void beforeAdvice(JoinPoint joinPoint) {
		System.out.println("Before method:" + joinPoint.getSignature());
	}

	@After(value = "execution(* ut.microservices.repaymentMicroService.services.RepaymentService.*(..)) and args(..)")
	public void afterAdvice(JoinPoint joinPoint) {
		System.out.println("After method:" + joinPoint.getSignature());
	}
}