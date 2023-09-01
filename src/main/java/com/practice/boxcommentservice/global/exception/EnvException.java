package com.practice.boxcommentservice.global.exception;

/**
 * EnvException.java 환경변수가 존재하지 않을 때 발생하는 전용 예외
 *
 * @author middlefitting
 * @version 1.0.0
 * @see RuntimeException
 * @since 2023-08-22
 */
public class EnvException extends RuntimeException {

  public EnvException() {
    super("서버에서 에러 메시지가 정의되지 않았습니다!");
  }
}

