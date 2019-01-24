package lab;

import lab.model.ApuBar;
import lab.model.Bar;
import lab.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
class AopAspectJTest {

  String msg;

  @Autowired
  private Bar bar;

  @Autowired
  private Person person;

  @BeforeEach
  void setUp() {
    msg = TestUtils.fromSystemOutPrintln(() -> bar.sellSquishee(person));
  }

  @Test
  void testBeforeAdvice() {
    assertTrue("Before advice is not good enough...", msg.contains("Hello"));
    assertTrue("Before advice is not good enough...", msg.contains("How are you doing?"));
  }

  @Test
  void testAfterAdvice() {
    System.out.println(msg);
    assertTrue("After advice is not good enough...", msg.contains("Good Bye!"));
  }

  @Test
  void testAfterReturningAdvice() {
    assertTrue("Customer is broken", msg.contains("Good Enough?"));
    System.out.println(msg);
  }

  @Test
  void testAroundAdvice() {
    assertTrue("Around advice is not good enough...", msg.contains("Hi!"));
    assertTrue("Around advice is not good enough...", msg.contains("See you!"));
    System.out.println(msg);
  }

  @Test
  void testAllAdvices() {
    assertFalse("barObject instanceof ApuBar", bar instanceof ApuBar);
  }
}
