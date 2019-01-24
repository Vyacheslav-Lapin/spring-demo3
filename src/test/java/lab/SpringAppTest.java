package lab;

import lab.model.PersonImpl;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static lab.HelloWorldTest.getExpectedPerson;
import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class) //@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JavaConfig.class) //@ContextConfiguration("classpath:ioc.xml")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SpringAppTest {

	PersonImpl person;

	@Test
	public void testInitPerson() {
		assertEquals(getExpectedPerson(), person);
	}

}
