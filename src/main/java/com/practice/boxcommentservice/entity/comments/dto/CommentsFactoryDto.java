package com.practice.boxcommentservice.entity.comments.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CommentsFactoryDto.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentsFactoryDto {

  private Long boardId;

  private String writerUuid;

  private String writerNickname;

  private String writerProfileImagePath;

  private String content;
}
