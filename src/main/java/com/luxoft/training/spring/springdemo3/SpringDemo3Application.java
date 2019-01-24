package com.luxoft.training.spring.springdemo3;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RepositoryRestResource
interface CatRepository extends JpaRepository<Cat, Long> {
}

@SpringBootApplication
public class SpringDemo3Application {

  public static void main(String[] args) {
    SpringApplication.run(SpringDemo3Application.class, args);
  }

}

@Data
@Entity
@NoArgsConstructor
class Cat {

  @Id
  @GeneratedValue
  Long id;

  String name;

  public Cat(String name) {
    this.name = name;
  }
}
