package lab.dao;

import lab.JavaConfig;
import lab.model.CountryImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CountryDataJdbcRepositoryTest { //for lab.dao.CountryDataJdbcRepository

  @Autowired
  CountryDataJdbcRepository countryDataJdbcRepository;

  @Test
  @SneakyThrows
  @DisplayName("\"save\" method works correctly")
  void testSave() {
    // given
    val zim = countryDataJdbcRepository.save(CountryImpl.builder()
      .name("Зимбабве")
      .codeName("ZI").build());

    // when

    // then
    assertThat(zim.getId()).isNotNull();
  }
}
