package com.todop11_20.todo.repository;

import com.todop11_20.todo.model.response.TodoFindReponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TodoQueryRepository {

  Page<TodoFindReponseDto> searchAll(Pageable pageable);

//  public Page<TodoFindReponseDto> search(Long todoId ,Pageable pageable);


}
