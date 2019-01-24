package lab.model;

//import javax.persistence.*;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Value
@Builder
//@Entity
@SuppressWarnings("WeakerAccess")
public class PersonImpl implements Person {
  //@Id
  @Default
  int id = 1;

  String name;

  //@ManyToOne(fetch = FetchType.EAGER)
  //@JoinColumn(name = "country_id")
  Country country;

  int age;
  float height;
  boolean isProgrammer;

  @Singular
  List<Contact> contacts;

  @Override
  public void sayHello(Person person) {
    // TODO: realize it!
  }
}
