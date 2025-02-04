package com.todop11_20.common.domain.entity;

import com.todop11_20.common.domain.enums.UserTodoRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usersubtodo")
@Getter
@NoArgsConstructor
public class UserSubTodo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "subtodo_id")
  private SubTodo subTodo;

  @Enumerated(EnumType.STRING)
  private UserTodoRole userTodoRole;

  private UserSubTodo(User user, SubTodo subTodo, UserTodoRole userTodoRole) {
    this.user = user;
    this.subTodo = subTodo;
    this.userTodoRole = userTodoRole;
  }
  public static UserSubTodo of(User user, SubTodo subTodo, UserTodoRole userTodoRole) {
    return new UserSubTodo(user,subTodo,userTodoRole);
  }
}
