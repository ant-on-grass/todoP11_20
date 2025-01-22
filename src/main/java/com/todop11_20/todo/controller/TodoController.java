package com.todop11_20.todo.controller;


import com.todop11_20.common.domain.entity.Todo;
import com.todop11_20.todo.model.response.TodoFindReponseDto;
import com.todop11_20.todo.model.response.TodoReponseDto;
import com.todop11_20.usertodo.model.request.CreateUserTodoRequestDto;
import com.todop11_20.todo.model.request.TodoRequestDto;
import com.todop11_20.todo.service.TodoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@Slf4j
public class TodoController {

  private final TodoService todoService;

  @PostMapping
  public ResponseEntity<String> createTodoApi(@RequestBody TodoRequestDto requestDto) {

    return ResponseEntity.ok().body(todoService.createTodo(requestDto).getCreateMessage());
  }


  @GetMapping
  public ResponseEntity<Page<TodoFindReponseDto>> findAllApi(Pageable pageable) {

    return ResponseEntity.ok().body(todoService.findAll(pageable));
  }

  @DeleteMapping("/{todo_id}")
  public ResponseEntity<String> deleteApi(@PathVariable("todo_id") Long todoId) {

    todoService.delete(todoId);

    return ResponseEntity.ok().body("삭제 성공");
  }

}
