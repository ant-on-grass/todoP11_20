package com.todop11_20.auth.model.response;


import com.todop11_20.common.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class AuthSignUpResponseDto {

  private String email;

  private String message = "회원 가입에 성공하였습니다.";

  private AuthSignUpResponseDto(String email) {
    this.email = email;
  }

  public static AuthSignUpResponseDto createAuthSignUpResponseDto(User user) {
    return new AuthSignUpResponseDto(user.getEmail());
  }
}
