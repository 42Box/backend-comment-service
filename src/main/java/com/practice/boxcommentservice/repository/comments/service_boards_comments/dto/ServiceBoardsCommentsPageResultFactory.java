package com.practice.boxcommentservice.repository.comments.service_boards_comments.dto;

import com.practice.boxcommentservice.entity.comments.ServiceBoardsCommentsEntity;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageResultFactory;
import org.springframework.stereotype.Component;

/**
 * serviceBoardsLikesFactory.
 *
 * @author : middlefitting
 * @since : 2023/09/01
 */
@Component
public class ServiceBoardsCommentsPageResultFactory implements
    CommentsPageResultFactory<ServiceBoardsCommentsPageResultDto, ServiceBoardsCommentsEntity> {

  @Override
  public ServiceBoardsCommentsPageResultDto create(ServiceBoardsCommentsEntity entity) {
    return new ServiceBoardsCommentsPageResultDto(entity);
  }
}
