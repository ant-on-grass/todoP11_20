package com.todop11_20.common.domain.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="todo")
@Getter
@NoArgsConstructor
public class Todo extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @OneToMany(mappedBy = "todo_id", cascade = CascadeType.REMOVE,orphanRemoval = true)
  private List<Todo> todos = new ArrayList<>();

  @OneToMany(cascade = CascadeType.REMOVE,orphanRemoval = true)
  private List<SubTodo> subTodo = new ArrayList<>();




}
