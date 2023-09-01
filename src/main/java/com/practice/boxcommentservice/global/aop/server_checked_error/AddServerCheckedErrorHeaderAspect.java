package com.practice.boxcommentservice.global.aop.server_checked_error;

import com.practice.boxcommentservice.global.env.EnvUtil;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * AddServerCheckedErrorHeaderAspect.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/24
 */
@Aspect
@Component
@AllArgsConstructor
public class AddServerCheckedErrorHeaderAspect {

  private final EnvUtil envUtil;

  @Around("@annotation(com.practice.boxcommentservice.global.aop.server_checked_error.AddServerCheckedErrorHeader) && args(ex,..)")
  public Object addCustomErrorHeader(ProceedingJoinPoint joinPoint, Exception ex) throws Throwable {
    String key = envUtil.getStringEnv("header.server-checked-error.key");
    String value = envUtil.getStringEnv("header.server-checked-error.value");
    Object result = joinPoint.proceed();

    if (result instanceof ResponseEntity) {
      HttpHeaders headers = new HttpHeaders();
      headers.set(key, value);
      return new ResponseEntity<>(((ResponseEntity<?>) result).getBody(), headers,
          ((ResponseEntity<?>) result).getStatusCode());
    }
    return result;
  }
}
