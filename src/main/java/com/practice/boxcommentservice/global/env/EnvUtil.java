package com.practice.boxcommentservice.global.env;

import com.practice.boxcommentservice.global.exception.EnvException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * EnvUtil.java 환경변수를 가져오는 유틸리티 클래스
 *
 * @author middlefitting
 * @version 1.0.0
 * @see Environment
 * @since 2023-08-22
 */
@Component
@RequiredArgsConstructor
public class EnvUtil {

  private final Environment env;

  public String getStringEnv(String key) {
    Optional<String> valueOpt = Optional.ofNullable(env.getProperty(key));
    return valueOpt.orElseThrow(EnvException::new);
  }

  public int getIntegerEnv(String key) {
    Optional<String> valueOpt = Optional.ofNullable(env.getProperty(key));
    return Integer.parseInt(valueOpt.orElseThrow(EnvException::new));
  }

  public HttpStatus getHttpStatusEnv(String key) {
    Optional<String> valueOpt = Optional.ofNullable(env.getProperty(key));
    return HttpStatus.valueOf(Integer.parseInt(valueOpt.orElseThrow(EnvException::new)));
  }
}
