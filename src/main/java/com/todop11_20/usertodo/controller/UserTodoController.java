package com.todop11_20.usertodo.controller;


import com.todop11_20.usertodo.model.request.CreateUserTodoRequestDto;
import com.todop11_20.usertodo.service.UserTodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usertodos")
@RequiredArgsConstructor
@Slf4j
public class UserTodoController {

  private final UserTodoService userTodoService;

  @PostMapping("/users/{user_id}/todos/{todo_id}")
  public ResponseEntity<String> createRelationApi(@PathVariable("user_id") Long userId,
      @PathVariable("todo_id") Long todoId,
      @RequestBody CreateUserTodoRequestDto requestDto
      ) {
    return ResponseEntity.ok().body(userTodoService.createRelation(userId,todoId,requestDto).getMessage());
  }

}
