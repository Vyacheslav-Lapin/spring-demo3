package lab;

import lab.dao.CountryDao;
import lab.model.Country;
import lab.model.CountryImpl;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RequiredArgsConstructor
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
@FieldDefaults(level = PRIVATE, makeFinal = true)
class JdbcTest {

  CountryDao countryDao;

  List<Country> expectedCountryList = new ArrayList<Country>();
  List<Country> expectedCountryListStartsWithA = new ArrayList<Country>();
  Country countryWithChangedName = CountryImpl.builder()
    .id(1)
    .name("Russia")
    .codeName("RU").build();

  @BeforeEach
  void setUp() {
    initExpectedCountryLists();
    countryDao.loadCountries();
  }

  @Test
  @DirtiesContext
  void testCountryList() {
    List<Country> countryList = countryDao.getCountryList();
    assertNotNull(countryList);
    assertEquals(expectedCountryList.size(), countryList.size());
    for (int i = 0; i < expectedCountryList.size(); i++)
      assertEquals(expectedCountryList.get(i), countryList.get(i));
  }

  @Test
  @DirtiesContext
  void testCountryListStartsWithA() {
    List<Country> countryList = countryDao.getCountryListStartWith("A");
    assertNotNull(countryList);
    assertEquals(expectedCountryListStartsWithA.size(), countryList.size());
    for (int i = 0; i < expectedCountryListStartsWithA.size(); i++)
      assertEquals(expectedCountryListStartsWithA.get(i), countryList.get(i));
  }

  @Test
  @DirtiesContext
  void testCountryChange() {
    countryDao.updateCountryName("RU", "Russia");
    assertEquals(countryWithChangedName, countryDao.getCountryByCodeName("RU"));
  }

  private void initExpectedCountryLists() {
    for (int i = 0; i < CountryDao.COUNTRY_INIT_DATA.length;) {
      String[] countryInitData = CountryDao.COUNTRY_INIT_DATA[i++];
      Country country = CountryImpl.builder()
        .id(i)
        .name(countryInitData[0])
        .codeName(countryInitData[1]).build();

      expectedCountryList.add(country);
      if (country.getName().startsWith("A"))
        expectedCountryListStartsWithA.add(country);
    }
  }
}
