package com.practice.boxcommentservice.repository.comments.script_boards_comments.dto;

import com.practice.boxcommentservice.entity.comments.ScriptBoardsCommentsEntity;
import com.practice.boxcommentservice.entity.comments.dto.CommentsFactoryDto;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageResultFactory;
import org.springframework.stereotype.Component;

/**
 * ScriptBoardsLikesFactory.
 *
 * @author : middlefitting
 * @since : 2023/09/01
 */
@Component
public class ScriptBoardsCommentsPageResultFactory implements
    CommentsPageResultFactory<ScriptBoardsCommentsPageResultDto, ScriptBoardsCommentsEntity> {

  @Override
  public ScriptBoardsCommentsPageResultDto create(ScriptBoardsCommentsEntity entity) {
    return new ScriptBoardsCommentsPageResultDto(entity);
  }
}
