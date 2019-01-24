package lab;

import lab.model.Bar;
import lab.model.CustomerBrokenException;
import lab.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
class AopAspectJExceptionTest {

  @Autowired
  private Bar bar;

  @Autowired
  private Person person;

  @BeforeEach
  void setUp() {
    person = person.withBroke(true);
  }

  @Test
  void testAfterThrowingAdvice() {
    String msg = TestUtils.fromSystemOutPrintln(() ->
      assertThrows(CustomerBrokenException.class, () -> bar.sellSquishee(person)));

    assertTrue("Customer is not broken ", msg.contains("Hmmm..."));
  }
}
