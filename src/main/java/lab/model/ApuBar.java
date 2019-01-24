package lab.model;

import org.springframework.stereotype.Component;

@Component
public class ApuBar implements Bar {

  public Squishee sellSquishee(Person customer) {
    if (customer.isBroke())
      throw new CustomerBrokenException();

    System.out.println("Here is your Squishee \n");
    return new Squishee("Usual Squishee");
  }
}
