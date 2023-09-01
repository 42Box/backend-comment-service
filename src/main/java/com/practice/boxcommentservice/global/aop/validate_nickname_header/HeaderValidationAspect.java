package com.practice.boxcommentservice.global.aop.validate_nickname_header;

import com.practice.boxcommentservice.global.env.EnvUtil;
import com.practice.boxcommentservice.global.exception.DefaultServiceException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * NicknameHeaderValidationAspect.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/25
 */

@Aspect
@Component
@AllArgsConstructor
public class HeaderValidationAspect {

  private final EnvUtil envUtil;

  @Before("@annotation(headerAuthCheck)")
  public void validateHeader(JoinPoint joinPoint, HeaderAuthCheck headerAuthCheck) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (attributes == null) {
      throw new RuntimeException();
    }
    String headerName = headerAuthCheck.headerName();
    HttpServletRequest request = attributes.getRequest();
    Optional<String> headerOpt = Optional.ofNullable(request.getHeader(headerName));
    headerOpt.orElseThrow(() -> new DefaultServiceException("users.error.auth-failed", envUtil));
    String header = headerOpt.get();
    if (header.isEmpty()) {
      throw new DefaultServiceException("users.error.auth-failed", envUtil);
    }
  }
}