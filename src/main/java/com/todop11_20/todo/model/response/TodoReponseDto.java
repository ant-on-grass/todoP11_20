package com.todop11_20.todo.model.response;


import com.todop11_20.common.domain.entity.Todo;
import com.todop11_20.todo.model.request.TodoRequestDto;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoReponseDto {


  private String title;

  private String content;

  private String createMessage = "Todo가 성공적으로 생성되었습니다.";
  private String patchMessage = "Todo가 성공적으로 수정되었습니다.";
  private String deleteMessage = "Todo가 성공적으로 삭제되었습니다.";

  private TodoReponseDto(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public static TodoReponseDto of(Todo todo) {
    return new TodoReponseDto(todo.getTitle(),todo.getContent());
  }
}
