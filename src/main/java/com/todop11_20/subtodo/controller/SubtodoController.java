package com.todop11_20.subtodo.controller;

import com.todop11_20.subtodo.model.request.SubTodoRequestDto;
import com.todop11_20.subtodo.service.SubTodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/subtodos")
@RequiredArgsConstructor
@Slf4j
public class SubtodoController {

  private final SubTodoService subTodoService;

  @PostMapping("/users/{user_id}/todos/{todo_id}")
  public ResponseEntity<String> createSubTodoApi(@RequestBody SubTodoRequestDto requestDto,
      @PathVariable("todo_id") Long todoId,
      @PathVariable("user_id") Long userId) {

    return ResponseEntity.ok().body(subTodoService.createSubTodo(requestDto,userId,todoId).getCreateSubTitle());
  }

}
