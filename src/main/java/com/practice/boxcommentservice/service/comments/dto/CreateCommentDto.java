package com.practice.boxcommentservice.service.comments.dto;

/**
 * CreateCommentDto.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
public class CreateCommentDto {

  private long commentBoardId;
  private String userUuid;
  private String commentsWriterNickname;
  private String commentsWriterProfileImagePath;
  private String commentContent;
}
