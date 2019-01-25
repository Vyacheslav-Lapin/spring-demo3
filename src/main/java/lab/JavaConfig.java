package lab;

import lab.model.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

import java.util.List;

import static lab.model.Contact.ContactType.EMAIL;
import static lombok.AccessLevel.PRIVATE;

//@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@ImportResource("classpath:application-context.xml")
@FieldDefaults(level = PRIVATE)
public class JavaConfig {

  @Bean
  Contact contact1() {
    return ContactImpl.builder().value("222-33-22").build();
  }

  @Bean
  Contact contact2() {
    return ContactImpl.builder().type(EMAIL).value("john@smith.com").build();
  }

  @Bean
  Country country() {
    return CountryImpl.builder().name("Russia").codeName("RU").build();
  }

  @Bean
  Person person(Country country, List<Contact> contacts) {
      return PersonImpl.builder().name("John Smith")
        .age(35)
        .country(country)
        .height(1.78f)
        .isProgrammer(true)
        .contacts(contacts)
        .build();
  }
}
