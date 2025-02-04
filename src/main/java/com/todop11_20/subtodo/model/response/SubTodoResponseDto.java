package com.todop11_20.subtodo.model.response;

import com.todop11_20.common.domain.entity.SubTodo;
import lombok.Getter;

@Getter
public class SubTodoResponseDto {

  private String subTitle;
  private String createSubTitle = "이슈가 생성되었습니다.";
  private String patchSubTitle = "이슈를 수정했습니다.";
  private String deleteSubTitle = "이슈가 삭제되었습니다.";



  private SubTodoResponseDto(String subTitle) {
    this.subTitle = subTitle;
  }

  public static SubTodoResponseDto of(SubTodo subTodo) {
    return new SubTodoResponseDto(subTodo.getSubtitle());
  }
}
