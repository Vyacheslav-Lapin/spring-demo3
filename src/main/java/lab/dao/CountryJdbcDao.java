package lab.dao;

import com.luxoft.training.spring.springdemo3.Java9BackPort;
import lab.model.Country;
import lab.model.CountryImpl;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CountryJdbcDao extends NamedParameterJdbcDaoSupport {

//  JdbcTemplate jdbcTemplate;
//  NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public static final String[][] COUNTRY_INIT_DATA = {{"Australia", "AU"},
    {"Canada", "CA"}, {"France", "FR"}, {"Hong Kong", "HK"},
    {"Iceland", "IC"}, {"Japan", "JP"}, {"Nepal", "NP"},
    {"Russian Federation", "RU"}, {"Sweden", "SE"},
    {"Switzerland", "CH"}, {"United Kingdom", "GB"},
    {"United States", "US"}};
  //language=H2
  static final String LOAD_COUNTRIES_SQL = "insert into country (name, code_name) values (?, ?)";//language=H2
  static final String CREATE_COUNTRY_TABLE_SQL = "create table country(id identity, name varchar (255), code_name varchar (255))";//language=H2
  static final String DROP_COUNTRY_TABLE_SQL = "drop table country";//language=H2
  static final String GET_ALL_COUNTRIES_SQL = "select * from country";//language=H2
  static final String GET_COUNTRIES_BY_NAME_SQL = "select * from country where name like :name";//language=H2
  static final String GET_COUNTRY_BY_NAME_SQL = "select * from country where name = ?";//language=H2
  static final String GET_COUNTRY_BY_CODE_NAME_SQL = "select * from country where code_name = ?";//language=H2
  static final String UPDATE_COUNTRY_NAME_SQL = "update country SET name=? where code_name=?";

  private static final RowMapper<Country> COUNTRY_ROW_MAPPER = (resultSet, i) ->
    CountryImpl.builder()
      .id(resultSet.getInt("id"))
      .name(resultSet.getString("name"))
      .codeName(resultSet.getString("code_name"))
      .build();

  @Autowired
  public void setDS(DataSource dataSource) {
    super.setDataSource(dataSource);
//    jdbcTemplate = getJdbcTemplate();
//    namedParameterJdbcTemplate = getNamedParameterJdbcTemplate();
  }

  public List<Country> getCountryList() {
    //noinspection ConstantConditions
    return getJdbcTemplate()
      .query(GET_ALL_COUNTRIES_SQL, COUNTRY_ROW_MAPPER);
  }

  public List<Country> getCountryListStartWith(String name) {
    //noinspection ConstantConditions
    return getNamedParameterJdbcTemplate()
      .query(GET_COUNTRIES_BY_NAME_SQL,
        Java9BackPort.mapOf("name", name + "%"),
        COUNTRY_ROW_MAPPER);
  }

  public void updateCountryName(String codeName, String newCountryName) {
    getJdbcTemplate()
      .update(UPDATE_COUNTRY_NAME_SQL, newCountryName, codeName);
  }

  public void loadCountries() {
    for (String[] countryData : COUNTRY_INIT_DATA) {
      getJdbcTemplate().update(LOAD_COUNTRIES_SQL, countryData[0], countryData[1]);
    }
  }

  public Country getCountryByCodeName(String codeName) {
    return getJdbcTemplate().queryForObject(GET_COUNTRY_BY_CODE_NAME_SQL,
      COUNTRY_ROW_MAPPER, codeName);
  }

  public Country getCountryByName(String name) throws CountryNotFoundException {
    //noinspection ConstantConditions
    List<Country> countryList = getJdbcTemplate().query(GET_COUNTRY_BY_NAME_SQL,
      COUNTRY_ROW_MAPPER, name);
    if (countryList.isEmpty())
      throw new CountryNotFoundException();
    return countryList.get(0);
  }
}
