package lab.aop;


import lab.model.CustomerBrokenException;
import lab.model.Person;
import lab.model.Squishee;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Politeness {

  @Pointcut("execution(* sellSquishee(..))")
  public final void sellSquishee() {
  }

  @Before("sellSquishee() && args(person)")
  public void sayHello(Person person) {
    System.out.printf("Hello %s. How are you doing?\n", person.getName());
  }

  @AfterReturning(pointcut = "sellSquishee()",
    returning = "retVal", argNames = "retVal")
  public void askOpinion(Squishee retVal) {
    System.out.printf("Is %s Good Enough? \n", retVal.getName());
  }

  @AfterThrowing(value = "sellSquishee()", throwing = "e")
  public void sayYouAreNotAllowed(CustomerBrokenException e) {
    System.out.println("Hmmm... " + e.getMessage());
  }

  @After("sellSquishee()")
  public void sayGoodBye() {
    System.out.println("Good Bye!");
  }

  @Around("sellSquishee()")
  public Object sayPoliteWordsAndSell(ProceedingJoinPoint pjp) throws Throwable {
    System.out.println("Hi!");
    Object retVal = pjp.proceed();
    System.out.println("See you!");
    return retVal;
  }

}
