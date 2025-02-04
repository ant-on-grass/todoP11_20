package com.todop11_20.todo.model.response;


import com.todop11_20.common.domain.entity.SubTodo;
import com.todop11_20.common.domain.entity.Todo;
import com.todop11_20.common.domain.entity.UserTodo;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class TodoFindReponseDto {

  private Long todoId;

  private String title;

  private String content;

  private List<Long> userIds = new ArrayList<>();

  private List<Long> subTodoIds = new ArrayList<>();
  private List<String> subTodoTitles = new ArrayList<>();

  private long countUserInTodo;
// 여기를 고쳐야한다. dsl은 생성자를 기준으로 만들기 때문에, 밑에 처럼 정적 팩토리 메서드는 먹히지 않는다
  public TodoFindReponseDto(Todo todo, long countUserInTodo) {
    this.todoId = todo.getId();
    this.title = todo.getTitle();
    this.content = todo.getContent();
    this.userIds = todo.getTodos().stream()
        .map(userTodo -> userTodo.getUser().getId())
        .toList();
    this.subTodoIds = todo.getSubTodos().stream()
        .map(SubTodo::getId)
        .toList();
    this.subTodoTitles = todo.getSubTodos().stream()
        .map(SubTodo::getSubtitle)
        .toList();
    this.countUserInTodo = countUserInTodo;
  }

//  public static TodoFindReponseDto of(Todo todo, long countUserInTodo) {
//    List<Long> userIds = todo.getTodos().stream()
//        .map(userTodo -> userTodo.getUser().getId())
//        .toList();
//
//    // SubTodo IDs 추출
//    List<Long> subTodoIds = todo.getSubTodos().stream()
//        .map(SubTodo::getId)
//        .toList();
//
//    List<String> subTodoTitles = todo.getSubTodos().stream()
//        .map(SubTodo::getSubtitle)
//        .toList();
//
//    return new TodoFindReponseDto(
//        todo.getId(),
//        todo.getTitle(),
//        todo.getContent(),
//        todo.getId(),
//        userIds,
//        subTodoIds,
//        subTodoTitles,
//        (int) countUserInTodo
//
//    );
//  }
}
