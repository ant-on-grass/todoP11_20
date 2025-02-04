package com.todop11_20.todo.repository;

import static com.querydsl.jpa.JPAExpressions.select;
import static com.todop11_20.common.domain.entity.QTodo.todo;
import static com.todop11_20.common.domain.entity.QUserTodo.userTodo;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todop11_20.todo.model.response.TodoFindReponseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TodoQueryRepositoryImpl implements TodoQueryRepository{

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<TodoFindReponseDto> searchAll(Pageable pageable) {
    // todo 정보 // 참가 인원 - UserTodo 에서 하나의 todo 에 user 수를 count , 참가한 user 닉네임 출력, 참가자의 권한
    List<TodoFindReponseDto> todoList = queryFactory.select(
        Projections.constructor(
            TodoFindReponseDto.class,
        todo,
        select(Wildcard.count)
            .from(userTodo) // Todo < - userTodo.user 변경 후 됨 // 엔터티 객체를 넣어야함
            .where(todo.id.eq(userTodo.todo.id))
      )
    )
        .from(todo) //Todo 조건 쿼리 넣기
        .orderBy(getSortOrders(pageable))
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();

    Long tatalCount = Optional.ofNullable(queryFactory.select(
        Wildcard.count)
        .from(todo)
        .fetchOne()).orElse(0L);


    return new PageImpl<>(todoList,pageable,tatalCount);
  }

  private OrderSpecifier<?>[] getSortOrders(Pageable pageable) {
    List<OrderSpecifier<?>> orders = new ArrayList<>();
    for( Sort.Order order : pageable.getSort()) {
      String property = order.getProperty();
      boolean isAscending = order.isAscending();

      OrderSpecifier<?> orderSpecifier = switch (property) {
        case "subtodo" -> isAscending ? todo.subTodos.size().asc() : todo.subTodos.size().desc();
        default -> todo.createdAt.desc();
      };
      orders.add(orderSpecifier);
    }
    return orders.toArray(new OrderSpecifier[]{});

  }

  private BooleanExpression eqTodoId(Long todoId) {
    if(todoId==null) {
      return null;
    }
    return todo.id.eq(todoId);
  }



}
