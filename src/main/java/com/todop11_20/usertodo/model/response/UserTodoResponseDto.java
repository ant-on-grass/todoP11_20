package com.todop11_20.usertodo.model.response;

import com.todop11_20.common.domain.entity.UserTodo;
import com.todop11_20.common.domain.enums.UserTodoRole;
import lombok.Getter;

@Getter
public class UserTodoResponseDto {

  private Long userId;
  private Long todoId;
  private UserTodoRole role;
  private String message = "유저가 Todo에 성공적으로 참석하였습니다.";

  private UserTodoResponseDto(Long userId, Long todoId, UserTodoRole role) {
    this.userId = userId;
    this.todoId = todoId;
    this.role = role;
  }

  public static UserTodoResponseDto of(UserTodo userTodo) {
    return new UserTodoResponseDto(
        userTodo.getUser().getId(),
        userTodo.getTodo().getId(),
        userTodo.getUserTodoRole()
    );
  }
}
