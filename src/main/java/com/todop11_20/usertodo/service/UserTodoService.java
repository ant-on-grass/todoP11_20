package com.todop11_20.usertodo.service;

import com.todop11_20.auth.repository.UserRepository;
import com.todop11_20.common.domain.entity.Todo;
import com.todop11_20.common.domain.entity.User;
import com.todop11_20.common.domain.entity.UserTodo;
import com.todop11_20.common.domain.enums.UserTodoRole;
import com.todop11_20.todo.repository.TodoRepository;
import com.todop11_20.usertodo.model.request.CreateUserTodoRequestDto;
import com.todop11_20.usertodo.model.response.UserTodoResponseDto;
import com.todop11_20.usertodo.repository.UserTodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserTodoService {

  private final UserTodoRepository userTodoRepository;
  private final UserRepository userRepository;
  private final TodoRepository todoRepository;

  public UserTodoResponseDto createRelation(Long userId, Long todoId,
      CreateUserTodoRequestDto requestDto) {

    Todo todo = todoRepository.findByIdOrElseThrow(todoId);
    userTodoRepository.checkFindByTodo(todo,userId);

    User user = userRepository.findByIdOrElseThrow(userId);
    UserTodo userTodo = UserTodo.of(user, todo,
        UserTodoRole.of(requestDto.getSelectRole()));

    userTodoRepository.save(userTodo);

    return UserTodoResponseDto.of(userTodo);

  }
}
