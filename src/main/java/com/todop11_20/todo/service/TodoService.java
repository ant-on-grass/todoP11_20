package com.todop11_20.todo.service;

import com.todop11_20.auth.repository.UserRepository;
import com.todop11_20.common.domain.entity.Todo;
import com.todop11_20.common.domain.entity.User;
import com.todop11_20.common.domain.enums.UserTodoRole;
import com.todop11_20.todo.model.response.TodoFindReponseDto;
import com.todop11_20.todo.repository.TodoQueryRepository;
import com.todop11_20.usertodo.model.request.CreateUserTodoRequestDto;
import com.todop11_20.todo.model.request.TodoRequestDto;
import com.todop11_20.todo.model.response.TodoReponseDto;
import com.todop11_20.todo.repository.TodoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class TodoService {

  private final TodoRepository todoRepository;
  private final UserRepository userRepository;

  @Transactional
  public TodoReponseDto createTodo(TodoRequestDto requestDto) {
    User user = userRepository.findByIdOrElseThrow(requestDto.getUserId());

    Todo todo = todoRepository.save(Todo.of(user, UserTodoRole.MANAGER,requestDto));

    return TodoReponseDto.of(todo);
  }

@Transactional
  public Page<TodoFindReponseDto> findAll(Pageable pageable) {
  Page<TodoFindReponseDto> pageTodoFindReponseDto = todoRepository.searchAll(pageable);

  //Page<TodoFindReponseDto> pageTodoFindReponseDto = pageAll.map(TodoFindReponseDto::of);
  return pageTodoFindReponseDto;
  }

  public void delete(Long todoId) {
    Todo todo = todoRepository.findByIdOrElseThrow(todoId);

    todoRepository.delete(todo);
  }

}
