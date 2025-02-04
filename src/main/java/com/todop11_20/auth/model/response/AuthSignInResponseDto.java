package com.todop11_20.auth.model.response;

import com.todop11_20.common.domain.entity.User;
import lombok.Getter;

@Getter
public class AuthSignInResponseDto {

  private String email;

  private String message = "로그인에 성공했습니다.";

  private String token;

  private AuthSignInResponseDto(String email, String token) {
    this.email = email;
    this.token = token;
  }

  public static AuthSignInResponseDto createAuthSignInResponseDto(User user,String token) {
    return new AuthSignInResponseDto(user.getEmail(),token);
  }
}
