package lab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@Builder
@AllArgsConstructor
public class CountryImpl implements Country {

  @Setter
  @Default
  @NonFinal
  int id = 1;

  String name;
  String codeName;

  public CountryImpl(String name, String codeName) {
    this(1, name, codeName);
  }
}
