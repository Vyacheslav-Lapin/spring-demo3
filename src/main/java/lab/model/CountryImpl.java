package lab.model;

import lombok.*;
import lombok.Builder.Default;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Table(name = "Country")
@Builder
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CountryImpl implements Country {

  @Id
  @Setter
  @NonFinal
  @GeneratedValue
  Integer id;

  String name;
  String codeName;

  public CountryImpl(String name, String codeName) {
    this(1, name, codeName);
  }
}
