package com.practice.boxcommentservice.service.comments.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DeleteCommentDto.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCommentDto {

  private long commentId;
  private long commentBoardId;
  private String userUuid;
}
