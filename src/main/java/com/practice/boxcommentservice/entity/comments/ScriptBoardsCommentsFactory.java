package com.practice.boxcommentservice.entity.comments;

import com.practice.boxcommentservice.entity.comments.dto.CommentsFactoryDto;
import org.springframework.stereotype.Component;

/**
 * ScriptBoardsLikesFactory.
 *
 * @author : middlefitting
 * @since : 2023/09/01
 */
@Component
public class ScriptBoardsCommentsFactory implements
    CommentsEntityFactory<ScriptBoardsCommentsEntity> {

  public ScriptBoardsCommentsEntity create(CommentsFactoryDto dto) {
    return ScriptBoardsCommentsEntity.builder()
        .writerNickname(dto.getWriterNickname())
        .boardId(dto.getBoardId())
        .writerUuid(dto.getWriterUuid())
        .writerProfileImagePath(dto.getWriterProfileImagePath())
        .content(dto.getContent())
        .build();
  }
}
