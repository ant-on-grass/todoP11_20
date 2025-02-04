package com.todop11_20.todo.repository;

import com.todop11_20.common.domain.entity.Todo;
import com.todop11_20.todo.model.response.TodoFindReponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface TodoRepository extends JpaRepository<Todo,Long> , TodoQueryRepository{

  default Todo findByIdOrElseThrow(Long id) {
    return findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"일치하는 유저가 없습니다."));
  }

}
