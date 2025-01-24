package com.todop11_20;


import com.github.javafaker.Faker;
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


  @Override
  @Transactional
  public void run(String... args) throws Exception {

    List<User> userList = new ArrayList<>();

    Faker enFaker = new Faker();
    Faker koFaker = new Faker(new Locale("ko"));

    int totalFakerUnitCount = 100;

    for (int i = 0; i < totalFakerUnitCount ; i++) {

      String email = enFaker.internet().safeEmailAddress();
      log.info("::: test enFaker email : {} :::",email);

      long StartTime = System.currentTimeMillis();
      String userName = koFaker.name().firstName()+koFaker.name().lastName();
      long EndTime = System.currentTimeMillis();
      log.info("::: test koFaker fullName : {} :::",userName);
      log.info("::: test koFaker fullName exTime : {}ms :::",EndTime-StartTime);
      log.info("");

      User user = new User(userName, email, UserRole.USER,false);
      userList.add(user);
    }
    userBatchInsert(totalFakerUnitCount ,10,userList);
  }
  private void userBatchInsert(int totalUnitCount ,int batchSize, List<User> userList) {
    long totalBatchStartTime = System.currentTimeMillis();

    for (int i =0; i < totalUnitCount ; i += batchSize) {
      long startTime = System.currentTimeMillis();
      log.info("::: test batch i : {} :::",i);
      int end = Math.min(i+batchSize,totalUnitCount);

      List<User> batchedUserList = userList.subList(i,end);

      jdbcTemplate.batchUpdate("INSERT INTO USER (EMAIL,PASSWORD,USER_STATUS,IS_DELETE) VALUES (?,?,?,?)", // mysql 에서는 USER 가 예약어가 아니다 // mysql db 확인!
          new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
              ps.setString(1,batchedUserList.get(i).getEmail());
              ps.setString(2,batchedUserList.get(i).getPassword());
              ps.setString(3, batchedUserList.get(i).getUserRole().name()); // 스트링으로 // jpa 는 enum 을 스트링으로 바꿔주지만, jdbc는 수동으로 해줘야한다!
              ps.setBoolean(4,batchedUserList.get(i).isDelete() );
            }

            @Override
            public int getBatchSize() {
              return batchSize; // batch 사이즈를 정하는
            }
          });
      long endTime = System.currentTimeMillis();
      log.info("::: test batch i : {} ::: exTime : {}ms :::",i,endTime-startTime);
      log.info("");
    }
    long totalBatchEndTime = System.currentTimeMillis();
    log.info("::: test total Batch exTime : {}ms :::",totalBatchEndTime-totalBatchStartTime);
    log.info("");

  }
}
