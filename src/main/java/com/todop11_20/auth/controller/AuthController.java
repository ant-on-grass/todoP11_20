package com.todop11_20.auth.controller;


import com.todop11_20.auth.model.request.AuthRequestDto;
import com.todop11_20.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

  private final AuthService authService;

  public ResponseEntity<String> signUpApi(@RequestBody AuthRequestDto requestDto) {

    return ResponseEntity.ok().body(authService.signUp(requestDto).getMessage());
  }

  public ResponseEntity<String> signInApi(@RequestBody AuthRequestDto requestDto) {

    return ResponseEntity.ok().body(authService.signIn(requestDto).getMessage());
  }

}
