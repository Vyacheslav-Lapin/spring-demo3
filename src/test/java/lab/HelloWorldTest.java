package lab;

import lab.model.ContactImpl;
import lab.model.CountryImpl;
import lab.model.Person;
import lab.model.PersonImpl;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static lab.model.Contact.ContactType.EMAIL;
import static lombok.AccessLevel.PRIVATE;
import static org.junit.Assert.assertEquals;

@FieldDefaults(level = PRIVATE)
public class HelloWorldTest {

  @SuppressWarnings("WeakerAccess")
  static String APPLICATION_CONTEXT_XML_FILE_NAME = "ioc.xml";

  BeanFactory context = new ClassPathXmlApplicationContext(
    APPLICATION_CONTEXT_XML_FILE_NAME);

  static Person getExpectedPerson() {
    return PersonImpl.builder()
      .name("John Smith")
      .age(35)
      .country(CountryImpl.builder()
        .name("Russia")
        .codeName("RU")
        .build())
      .height(1.78f)
      .isProgrammer(true)
      .contact(ContactImpl.builder().value("222-33-22").build())
      .contact(ContactImpl.builder().type(EMAIL).value("john@smith.com").build())
      .build();
  }

  @Test
  public void testInitPerson() {
    assertEquals(getExpectedPerson(), context.getBean("person"));
  }
}
