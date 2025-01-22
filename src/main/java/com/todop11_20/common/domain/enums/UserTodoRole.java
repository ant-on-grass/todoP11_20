package com.todop11_20.common.domain.enums;

import java.util.Arrays;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum UserTodoRole {

  PARTICIPANT("participant"),
  MANAGER("manager");

  private final String role;

  UserTodoRole(String role) {
    this.role = role;
  }

  public static UserTodoRole of(String userTodoRole) {
    return Arrays.stream(UserTodoRole.values())
        .filter(r -> r.name().equalsIgnoreCase(userTodoRole))
        .findFirst()
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
  }


}
