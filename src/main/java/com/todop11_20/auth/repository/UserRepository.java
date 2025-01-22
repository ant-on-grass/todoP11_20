package com.todop11_20.auth.repository;

import com.todop11_20.common.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface UserRepository extends JpaRepository<User,Long> {

  Optional<User> findByEmail(String email);

  default User findByEmailOrElseThrow(String email) {
    return findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"일치하는 유저가 없습니다."));
  }

  default User findByIdOrElseThrow(Long id) {
    return findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"일치하는 유저가 없습니다."));
  }



}
