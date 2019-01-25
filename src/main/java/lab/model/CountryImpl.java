package lab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.springframework.data.annotation.Id;

@Value
@Builder
@AllArgsConstructor
public class CountryImpl implements Country {

  @Id
  @Setter
  @Default
  @NonFinal
  Integer id = 1;

  String name;
  String codeName;

  public CountryImpl(String name, String codeName) {
    this(1, name, codeName);
  }
}
