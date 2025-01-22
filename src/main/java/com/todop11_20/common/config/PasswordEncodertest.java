package com.todop11_20.common.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncodertest {

  public String encode(String rawPassword) {
    return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
  }

  public boolean matches(String rawPassword, String encodedPassword) {
    BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
    return result.verified;
  }
}
