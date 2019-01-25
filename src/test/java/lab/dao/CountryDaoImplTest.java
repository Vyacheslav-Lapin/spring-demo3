package lab.dao;

import lab.model.Country;
import lab.model.CountryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

/**
 * Illustrates basic use of Hibernate as a JPA provider.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class CountryDaoImplTest {

//	private static Log log = LogFactory.getLog(CountryDaoImplTest.class);

	private Country exampleCountry = CountryImpl.builder()
    .name("Australia")
    .codeName("AU").build();

	@Autowired
	private CountryDao countryDao;

  @Before
  public void setUp() {
    countryDao.save(exampleCountry);
  }

  @Test
	public void testSaveCountry() {
		List<Country> countryList = countryDao.getAllCountries();
		assertEquals(1, countryList.size());
		assertEquals(exampleCountry, countryList.get(0));
	}

	@Test
	public void testGtAllCountries() {
		countryDao.save(CountryImpl.builder().name("Canada").codeName("CA").build());
		List<Country> countryList = countryDao.getAllCountries();
		assertEquals(2, countryList.size());
	}

	@Test
	public void testGetCountryByName() {
		Country country = countryDao.getCountryByName("Australia");
    assertThat(country).isEqualTo(exampleCountry);
	}

}
