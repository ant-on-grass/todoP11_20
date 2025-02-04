package com.todop11_20.subtodo.service;

import com.todop11_20.auth.repository.UserRepository;
import com.todop11_20.common.domain.entity.SubTodo;
import com.todop11_20.common.domain.entity.Todo;
import com.todop11_20.common.domain.entity.User;
import com.todop11_20.common.domain.entity.UserSubTodo;
import com.todop11_20.common.domain.entity.UserTodo;
import com.todop11_20.common.domain.enums.UserTodoRole;
import com.todop11_20.subtodo.model.request.SubTodoRequestDto;
import com.todop11_20.subtodo.model.response.SubTodoResponseDto;
import com.todop11_20.subtodo.repository.SubTodoRepository;
import com.todop11_20.todo.repository.TodoRepository;
import com.todop11_20.usertodo.repository.UserTodoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubTodoService {

  private final SubTodoRepository subTodoRepository;
  private final TodoRepository todoRepository;
  private final UserRepository userRepository;
  private final UserTodoRepository userTodoRepository;

  @Transactional
  public SubTodoResponseDto createSubTodo(SubTodoRequestDto requestDto,Long userId,Long todoId) {
    Todo todo = todoRepository.findByIdOrElseThrow(todoId);
    UserTodo userTodo = userTodoRepository.checkFindByTodo(todo, userId).get(0);

    User user = userRepository.findByIdOrElseThrow(userId);

    SubTodo subtodo = subTodoRepository.save(SubTodo.of(
        requestDto.getSubTitle(),todo,user, userTodo.getUserTodoRole()
      )
    );
    todo.addSubTodos(subtodo);
    return SubTodoResponseDto.of(subtodo);
  }

}
