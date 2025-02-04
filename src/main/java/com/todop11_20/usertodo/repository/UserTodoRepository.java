package com.todop11_20.usertodo.repository;

import com.todop11_20.common.domain.entity.Todo;
import com.todop11_20.common.domain.entity.User;
import com.todop11_20.common.domain.entity.UserTodo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface UserTodoRepository extends JpaRepository<UserTodo,Long> {
List<UserTodo> findByTodo(Todo todo);

default List<UserTodo>  checkFindByTodo(Todo todo,Long userId) {
  List<UserTodo> checkUserTodoList = findByTodo(todo);
  if(checkUserTodoList==null) {
    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }
  for (UserTodo userTodo : checkUserTodoList) {
    if(userTodo.getUser().getId()==userId && userTodo.getUserTodoRole().toString().equals("manager")) {
      checkUserTodoList.add(userTodo);
    }
  }
  if(checkUserTodoList.size()==0) {
    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
  }
  return checkUserTodoList;
}

  default UserTodo findByIdOrElseThrow(Long id) {
    return findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"일치하는 유저가 없습니다."));
  }
}
