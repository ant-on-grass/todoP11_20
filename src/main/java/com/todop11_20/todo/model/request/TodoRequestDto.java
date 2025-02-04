package com.todop11_20.todo.model.request;


import com.todop11_20.common.domain.entity.UserTodo;
import com.todop11_20.common.domain.enums.UserTodoRole;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TodoRequestDto {

  private Long userId;

  private String title;

  private String content;


}
