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
import lombok.Setter;

@Entity
@Table(name = "usertodo")
@Getter
@NoArgsConstructor
@Setter
public class UserTodo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  //@JsonBackReference
  //@JsonIgnore
  private User user;

  @ManyToOne
  @JoinColumn(name = "todo_id")
  //@JsonBackReference
  //@JsonIgnore
  private Todo todo;

  @Enumerated(EnumType.STRING)
  private UserTodoRole userTodoRole;

  private UserTodo(User user, Todo todo, UserTodoRole userTodoRole) {
    this.user = user;
    this.todo = todo;
    this.userTodoRole = userTodoRole;
  }

  public static UserTodo of(User user, Todo todo, UserTodoRole userTodoRole) {
    return new UserTodo(user,todo,
        userTodoRole);
  }
}
