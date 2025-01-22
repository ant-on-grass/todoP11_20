package com.todop11_20.common.domain.entity;


import com.todop11_20.common.domain.enums.UserTodoRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subtodo")
@Getter
@NoArgsConstructor
public class SubTodo extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  @Column(name = "subtitle")
  private String subtitle;

  @ManyToOne
  @JoinColumn(name = "todo_id")
  private Todo todo;

  @OneToMany(mappedBy = "subTodo",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<UserSubTodo> userSubTodoList = new ArrayList<>();


  private SubTodo(String subtitle,Todo todo) {
    this.subtitle = subtitle;
    this.todo = todo;
  }

  public static SubTodo of(String subtitle,Todo todo,User user, UserTodoRole userTodoRole) {
    SubTodo subTodo = new SubTodo(subtitle, todo);
    subTodo.addUserSub(user, subTodo, userTodoRole);
    return subTodo;
  }

  private void addUserSub(User user, SubTodo subTodo, UserTodoRole userTodoRole) {
    UserSubTodo userSubTodo = UserSubTodo.of(user, subTodo, userTodoRole);
    userSubTodoList.add(userSubTodo);
  }
}
