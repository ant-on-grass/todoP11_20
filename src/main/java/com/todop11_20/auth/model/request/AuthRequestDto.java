package com.todop11_20.auth.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class AuthRequestDto {

  private String email;
  private String password;

}
