package com.practice.boxcommentservice.global.exception;

/**
 * ERROR.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
public enum ERROR {
  GLOBAL_ARGUMENT("global.error.invalid-argument-request", "유효하지 인자 요청"),
  GLOBAL_AUTH_FAILED("global.error.auth-failed", "권한 없음"),
  COMMENT_NOT_FOUND("comments.error.not-found", "존재하지 않는 댓글"),
  COMMENT_MOD_AUTH("comments.error.mod-auth", "댓글 상태 변경 권한 없음"),
  BOARD_NOT_FOUND("comments.error.board-not-found", "존재하지 않는 게시글"),
  BOARD_SERVICE_ERROR("comments.error.board-service-error", "게시글 서비스 에러");
  private final String envKey;
  private final String description;

  ERROR(String envKey, String description) {
    this.envKey = envKey;
    this.description = description;
  }

  public String getEnvKey() {
    return envKey;
  }

  public String getDescription() {
    return description;
  }
}
