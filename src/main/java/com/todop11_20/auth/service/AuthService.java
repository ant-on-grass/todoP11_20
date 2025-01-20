package com.todop11_20.auth.service;


import com.todop11_20.auth.model.request.AuthRequestDto;
import com.todop11_20.auth.model.response.AuthSignInResponseDto;
import com.todop11_20.auth.model.response.AuthSignUpResponseDto;
import com.todop11_20.auth.repository.UserRepository;
import com.todop11_20.common.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;

  @Transactional
  public AuthSignUpResponseDto signUp(AuthRequestDto requestDto) {
    return AuthSignUpResponseDto.createAuthSignUpResponseDto(
        userRepository.save(User.createUser(requestDto))
    );
  }

  @Transactional
  public AuthSignInResponseDto signIn(AuthRequestDto requestDto) {
    return AuthSignInResponseDto.createAuthSignInResponseDto(
        userRepository
            .findByEmailOrElseThrow(requestDto.getEmail()));
  }
}
