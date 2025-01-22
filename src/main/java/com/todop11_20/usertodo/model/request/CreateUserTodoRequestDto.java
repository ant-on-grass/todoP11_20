package com.todop11_20.usertodo.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateUserTodoRequestDto {

  private String title;

  private String content;

  private String selectRole;

}
