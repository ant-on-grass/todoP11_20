package com.todop11_20;


import com.github.javafaker.Faker;
import com.todop11_20.common.domain.entity.Todo;
import com.todop11_20.common.domain.entity.User;
import com.todop11_20.common.domain.enums.UserRole;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class FakerTest implements CommandLineRunner {

  private final JdbcTemplate jdbcTemplate;
  private final Generic<User> userGeneric;

  @Override
  @Transactional
  public void run(String... args) throws Exception {

    List<User> userList = new ArrayList<>();
    List<Todo> todoList = new ArrayList<>();

    Faker enFaker = new Faker();
    Faker koFaker = new Faker(new Locale("ko"));

    int totalFakerUnitCount = 100000;

    for (int i = 0; i < totalFakerUnitCount ; i++) {
      // user faker
      String email = enFaker.internet().safeEmailAddress();
      log.info("::: test enFaker email : {} :::",email);
      log.info("");

      long StartTime = System.currentTimeMillis();
      String userName = koFaker.name().firstName()+koFaker.name().lastName();
      long EndTime = System.currentTimeMillis();
      log.info("::: test koFaker fullName : {} :::",userName);
      log.info("::: test koFaker fullName exTime : {}ms :::",EndTime-StartTime);
      log.info("");

      // todo faker
      String todoTitle = koFaker.book().title();
      log.info("::: test koFaker todoTitle : {} :::",todoTitle);
      log.info("");
      String todoContent = enFaker.dune().title();
      log.info("::: test koFaker todoContent : {} :::",todoContent);
      log.info("");

      User user = new User(userName, email, UserRole.USER,false);
      userList.add(user);

      Todo todo = new Todo(todoTitle, todoContent);
      todoList.add(todo);
    }

    userGeneric.batchInsert(totalFakerUnitCount,1000,userList,userList.get(0),1,4);
  }

}
