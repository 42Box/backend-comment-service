package com.practice.boxcommentservice.service.comments.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * UpdateCommentDto.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommentDto {

  private long commentId;
  private long commentBoardId;
  private String userUuid;
  private String commentContent;
}
