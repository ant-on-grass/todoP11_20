package com.todop11_20;

import com.todop11_20.common.domain.EnumStatusInterface;
import com.todop11_20.common.domain.annotation.FieldAnnotation;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Generic<T> {

  private final JdbcTemplate jdbcTemplate;

  public void batchInsert(int totalUnitCount, int batchSize, List<T> list, T instance,int startIndex, int endIndex) {
    long totalBatchStartTime = System.currentTimeMillis();

    for (int i = 0; i < totalUnitCount; i += batchSize) {
      long startTime = System.currentTimeMillis();
      log.info("::: instance batch i : {} :::", i);
      int end = Math.min(i + batchSize, totalUnitCount);

      List<T> batchedTList = list.subList(i, end);

      // 쿼리
        // 클래스 명
      String className = instance.getClass().getSimpleName();

      // 엔터티 상 필드 명 리스트 // 리플랙션
      Field[] declaredFields = instance.getClass().getDeclaredFields();
      List<String> fieldsNames = new ArrayList<>();

      // 데이터 베이스 상 필드 명 리스트 - 어노테이션으로 구분 - 해당 어노테이션은 엔터티에 명시 // 커스텀 어노테이션
      List<String> annotationFieldNames = new ArrayList<>();


      String parcountcount1 = ""; // 필드 명
      String parcountcount2 = ""; // 벨류 값 - ?
      int j = 0;
      for (Field declaredField : declaredFields) {
        if(startIndex <=j && j<= endIndex) { // 엔터티 상 필드 중 선택한 범위(index)의 필드 명 추출
          log.info("::: j  : {} :::",j);
          String fieldName = declaredField.getName(); // 엔터티상 필드명
          fieldsNames.add(fieldName);

          FieldAnnotation annotation = declaredField.getAnnotation(FieldAnnotation.class); // 데이터 베이스 상 필드 명
          String annotationFieldName = (annotation != null) ? annotation.value() : declaredField.getName();
          annotationFieldNames.add(annotationFieldName);
          if (j==endIndex) {
            log.info("::: endIndex : {} :::",endIndex);
            parcountcount1 += annotationFieldName;
            parcountcount2 += " ?";
            break;
          }
          parcountcount1 += annotationFieldName + ",";
          parcountcount2 += " ? ,";
          j++;
          continue;
        }
        j++;
      }

      String sql = "INSERT INTO " + className + " (" + parcountcount1 + ") " + "VALUES (" + parcountcount2 + ")";

      log.info("::: sql : {} :::",sql);

      jdbcTemplate.batchUpdate(
          sql,
          // mysql 에서는 USER 가 예약어가 아니다 // mysql db 확인!

          // 리플랙션 set 갯수 - 거기에 타입 // 동적으로 클래스에 객체에 접 // 런타임 도중에 바꿀 수 있게 해줌
          // 속도가 느림
          new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
              //익명 클래스 (Anonymous Class)**를 사용해서 BatchPreparedStatementSetter 인터페이스를 구현한 것
              //익명 클래스는 메서드 내부에 정의된 것처럼 보이지만 실제로는 해당 인터페이스를 구현하는 독립적인 객체를 생성
                try {

                  // 리스트에서 현재 객체를 가져옴
                  T currentObject = batchedTList.get(i);

                  for (int x = 0; x < fieldsNames.size(); x++) {
                    // 필드 객체 가져오기
                    Field field = currentObject.getClass().getDeclaredField(fieldsNames.get(x));
                    field.setAccessible(true); // private 필드 접근 허용

                    // 필드 값 가져오기
                    Object fieldValue = field.get(currentObject);
                    //리플렉션을 사용해 currentObject의 해당 필드 값을 가져옴.
                    //field.get(instance)는 인스턴스에서 해당 필드 값을 반환.



                    // 필드 타입에 따라 PreparedStatement에 값 설정 // field.getType() 필드의 타입을 가지고오는 메서드
                    if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                      ps.setBoolean(x + 1, fieldValue != null ? (Boolean) fieldValue : false);
                    } else if (field.getType() == int.class || field.getType() == Integer.class) {
                      ps.setInt(x + 1, fieldValue != null ? (Integer) fieldValue : 0);
                    } else if (field.getType() == long.class || field.getType() == Long.class) {
                      ps.setLong(x + 1, fieldValue != null ? (Long) fieldValue : 0L);
                    } else if (field.getType() == String.class) {
                      ps.setString(x + 1, fieldValue != null ? (String) fieldValue : null);
                    } else if (EnumStatusInterface.class.isAssignableFrom(field.getType())) {
                      // Enum 타입인 경우 처리
                      EnumStatusInterface enumValue = (EnumStatusInterface) fieldValue;
                      ps.setString(x + 1, enumValue != null ? enumValue.getCode() : null);
                    } else {
                      // 기본적으로 Object로 처리
                      ps.setObject(x + 1, fieldValue);
                    }
                 }
                } catch (NoSuchFieldException e) {
                  throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                  throw new RuntimeException(e);
                }
            }

            @Override
            public int getBatchSize() {
              return batchSize; // batch 사이즈를 정하는
            }
          });
      long endTime = System.currentTimeMillis();
      log.info("::: instance batch i : {} ::: exTime : {}ms :::", i, endTime - startTime);
      log.info("");
    }
    long totalBatchEndTime = System.currentTimeMillis();
    log.info("::: instance total Batch exTime : {}ms :::", totalBatchEndTime - totalBatchStartTime);
    log.info("");

  }
}
