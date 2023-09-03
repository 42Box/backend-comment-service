package com.practice.boxcommentservice.entity.comments;

import com.practice.boxcommentservice.entity.comments.dto.CommentsFactoryDto;
import org.springframework.stereotype.Component;

/**
 * serviceBoardsLikesFactory.
 *
 * @author : middlefitting
 * @since : 2023/09/01
 */
@Component
public class ServiceBoardsCommentsFactory implements
    CommentsEntityFactory<ServiceBoardsCommentsEntity> {

  public ServiceBoardsCommentsEntity create(CommentsFactoryDto dto) {
    return ServiceBoardsCommentsEntity.builder()
        .writerNickname(dto.getWriterNickname())
        .boardId(dto.getBoardId())
        .writerUuid(dto.getWriterUuid())
        .writerProfileImagePath(dto.getWriterProfileImagePath())
        .content(dto.getContent())
        .build();
  }
}
