package tr.com.huseyinaydin.springbootaop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Aspect
@Component
public class ServiceAspect {

    @Before("execution(* com.example.springbootaop.service.MessageService.mesajVer(..))")
    public void mesajVerMetodundanOnce(JoinPoint joinPoint){
        System.out.println("Mesajver metodundan önce parametre yakalandı param: " + joinPoint.getArgs()[0]);
        System.out.println(joinPoint.getSignature());
    }

    @After("execution(* com.example.springbootaop.service.*.*(..))")
    public void mesajVerMetodundanSonra(JoinPoint joinPoint){
        System.out.println("Mesajver metodundan sonra parametre yakalandı param: " + joinPoint.getArgs()[0]);
        System.out.println(joinPoint.getSignature());
    }
}