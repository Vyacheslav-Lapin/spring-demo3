package lab.dao;

import lab.model.CountryImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDataJdbcRepository extends CrudRepository<CountryImpl, Integer> {
}
