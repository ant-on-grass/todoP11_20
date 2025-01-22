package com.todop11_20.comment.model.response;

import com.todop11_20.common.domain.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

  private String comment;
  private String createMessage = "댓글이 생성되었습니다.";
  private String patchMessage = "댓글이 수정되었습니다.";
  private String deleteMessage = "댓글이 삭제되었습니다.";

  private CommentResponseDto(String comment) {
    this.comment = comment;
  }

  public static CommentResponseDto of(Comment comment) {
    return new CommentResponseDto(comment.getComment());
  }
}
