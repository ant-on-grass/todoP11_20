package com.todop11_20.common.domain.entity;


import com.todop11_20.comment.model.request.CommentRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "comment")
  private String comment;

  private Comment(String comment) {
    this.comment = comment;
  }

  public static Comment of(CommentRequestDto requestDto) {
    return new Comment(requestDto.getComment());
  }
}
