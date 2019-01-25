package lab.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import lombok.Cleanup;
import org.springframework.stereotype.Repository;

import lab.dao.CountryDao;
import lab.model.Country;

@Repository
public class CountryJpaDaoImpl extends AbstractJpaDao implements CountryDao {

	@Override
	public void save(Country country) {
//		TODO: Implement it
		@Cleanup EntityManager em = null;


	}

	@Override
	public List<Country> getAllCountries() {
//	TODO: Implement it
		return null	;
	}

	@Override
	public Country getCountryByName(String name) {
//		TODO: Implement it

		return null;
	}

}
