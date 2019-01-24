package com.luxoft.training.spring.springdemo3;

import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WithMockUser(authorities = "ADMIN")
public class SpringDemo3ApplicationTests {

  @Autowired
  MockMvc mvc;

  @Autowired
  CatRepository catRepository;

  @Before
  public void setUp() {
    Stream.of("Felix", "Garfield", "Whiskers")
      .map(Cat::new)
      .forEach(catRepository::save);
  }

  @Test
  @SneakyThrows
  public void contextLoads() {
    MediaType halJson = MediaType.parseMediaType("application/hal+json;charset=UTF-8");

    mvc.perform(get("/cats"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(halJson))
      .andExpect(
        mvcResult -> {
          String contentAsString = mvcResult.getResponse().getContentAsString();
          assertEquals("3", contentAsString.split("totalElements")[1]
            .split(":")[1].trim()
            .split(",")[0]);
        });
  }

}
