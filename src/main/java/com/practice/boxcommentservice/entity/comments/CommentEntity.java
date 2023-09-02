package com.practice.boxcommentservice.entity.comments;

import java.time.LocalDateTime;

/**
 * CommentEntity.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
public interface CommentEntity {

  long getId();

  Long getBoardId();

  String getWriterUuid();

  String getWriterNickname();

  String getWriterProfileImagePath();

  String getContent();

  LocalDateTime getRegDate();

  LocalDateTime getModDate();
}
