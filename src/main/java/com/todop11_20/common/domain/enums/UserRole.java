package com.todop11_20.common.domain.enums;

import com.todop11_20.common.domain.EnumStatusInterface;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum UserRole implements EnumStatusInterface {

  ADMIN("ADMIN"),USER("USER");

  private final String role;

  UserRole(String role) {
    this.role = role;
  }

  public static UserRole toString(String userRole) {
    return Arrays.stream(UserRole.values())
        .filter(r -> r.name().equalsIgnoreCase(userRole))
        .findFirst()
        .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
  }

  @Override
  public String getCode() {
    return role;
  }
}
