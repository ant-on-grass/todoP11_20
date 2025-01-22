package com.todop11_20.common.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.todop11_20.common.domain.enums.UserTodoRole;
import com.todop11_20.todo.model.request.TodoRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="todo")
@Getter
@NoArgsConstructor

public class Todo extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL,orphanRemoval = true)
  //@JsonManagedReference
  //@JsonIgnore
  private List<UserTodo> todos = new ArrayList<>();

  @OneToMany(cascade = CascadeType.REMOVE,orphanRemoval = true)
  private List<SubTodo> subTodos = new ArrayList<>();

  private void userTodoAdd(User user, UserTodoRole userTodoRole) { //todo 체크 2.
    UserTodo userTodo = UserTodo.of(user, this,
        userTodoRole);
    this.todos.add(userTodo);
  }

  private Todo(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public static Todo of(User user, UserTodoRole userTodoRole,TodoRequestDto requestDto) {
    Todo todo = new Todo(requestDto.getTitle(), requestDto.getContent()); //todo 체크 2.
    todo.userTodoAdd(user,userTodoRole);
    return todo;
  }

  public void addSubTodos(SubTodo subTodo) {
    this.subTodos.add(subTodo);
  }
}
