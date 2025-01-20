package com.todop11_20.auth.model.response;

import com.todop11_20.common.domain.entity.User;
import lombok.Getter;

@Getter
public class AuthSignInResponseDto {

  private String email;

  private String message = "로그인에 성공했습니다.";

  private AuthSignInResponseDto(String email) {
    this.email = email;
  }

  public static AuthSignInResponseDto createAuthSignInResponseDto(User user) {
    return new AuthSignInResponseDto(user.getEmail());
  }
}
