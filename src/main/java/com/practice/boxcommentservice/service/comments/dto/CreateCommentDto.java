package com.practice.boxcommentservice.service.comments.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CreateCommentDto.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentDto {

  private long commentsBoardId;
  private String userUuid;
  private String commentsWriterNickname;
  private String commentsWriterProfileImagePath;
  private String commentContent;
}
