package com.todop11_20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing //todo 체크 1.
@SpringBootApplication
public class TodoP1120Application {

  public static void main(String[] args) {
    SpringApplication.run(TodoP1120Application.class, args);
  }

}