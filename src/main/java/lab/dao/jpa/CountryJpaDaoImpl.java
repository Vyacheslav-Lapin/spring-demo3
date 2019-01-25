package lab.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import lab.model.CountryImpl;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import lab.dao.CountryDao;
import lab.model.Country;

@Repository
public class CountryJpaDaoImpl extends AbstractJpaDao implements CountryDao {

	@Override
	public void save(Country country) {
		@Cleanup EntityManager em = emf.createEntityManager();
    val transaction = em.getTransaction();
    transaction.begin();
    em.persist(country);
    transaction.commit();
	}

	@Override
	public List<Country> getAllCountries() {
    @Cleanup EntityManager em = emf.createEntityManager();
//    val transaction = em.getTransaction();
//    transaction.begin();
    //noinspection JpaQlInspection
    List<Country> countries = em.createQuery("select c from lab.model.CountryImpl c", Country.class)
      .getResultList();
//    transaction.commit();
    return countries;
	}



	@Override
	public Country getCountryByName(String name) {
    @Cleanup EntityManager em = emf.createEntityManager();
//    val transaction = em.getTransaction();
//    transaction.begin();
    //noinspection JpaQlInspection
    CountryImpl country = em.createQuery("select c from lab.model.CountryImpl c where c.name=?1", CountryImpl.class)
      .setParameter(1, name)
      .getSingleResult();
//    transaction.commit();
    return country;
	}

}
