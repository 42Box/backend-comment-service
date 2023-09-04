package com.practice.boxcommentservice.service.comments.service_boards_comments;

import com.practice.boxcommentservice.client.board_client.ServiceBoardClient;
import com.practice.boxcommentservice.entity.comments.ServiceBoardsCommentsEntity;
import com.practice.boxcommentservice.entity.comments.ServiceBoardsCommentsFactory;
import com.practice.boxcommentservice.global.env.EnvUtil;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageConditionDto;
import com.practice.boxcommentservice.repository.comments.service_boards_comments.ServiceBoardsCommentsRepository;
import com.practice.boxcommentservice.repository.comments.service_boards_comments.dto.ServiceBoardsCommentsPageResultDto;
import com.practice.boxcommentservice.repository.comments.service_boards_comments.dto.ServiceBoardsCommentsPageResultFactory;
import com.practice.boxcommentservice.service.comments.CommentsService;
import com.practice.boxcommentservice.service.comments.CommentsServiceImplTemplate;
import com.practice.boxcommentservice.service.comments.dto.CreateCommentDto;
import com.practice.boxcommentservice.service.comments.dto.DeleteCommentDto;
import com.practice.boxcommentservice.service.comments.dto.GetCommentDto;
import com.practice.boxcommentservice.service.comments.dto.UpdateCommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * serviceBoardsCommentsService.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
@Service
public class ServiceBoardsCommentsService implements
    CommentsService<ServiceBoardsCommentsEntity, ServiceBoardsCommentsPageResultDto> {

  private final CommentsServiceImplTemplate<ServiceBoardsCommentsEntity, ServiceBoardsCommentsPageResultDto> commentsServiceImplTemplate;


  @Autowired
  public ServiceBoardsCommentsService(
      ServiceBoardsCommentsRepository commentsRepository,
      ServiceBoardsCommentsFactory commentsEntityFactory,
      ServiceBoardsCommentsPageResultFactory commentsPageResultFactory,
      EnvUtil envUtil,
      ServiceBoardClient serviceBoardClient) {
    this.commentsServiceImplTemplate = new CommentsServiceImplTemplate<>(
        commentsRepository, commentsEntityFactory, commentsPageResultFactory, envUtil,
        serviceBoardClient);
  }

  @Override
  public ServiceBoardsCommentsPageResultDto createComment(CreateCommentDto dto) {
    return commentsServiceImplTemplate.createComment(dto);
  }

  @Override
  public ServiceBoardsCommentsPageResultDto updateComment(UpdateCommentDto dto) {
    return commentsServiceImplTemplate.updateComment(dto);
  }

  @Override
  public ServiceBoardsCommentsPageResultDto getComment(GetCommentDto dto) {
    return commentsServiceImplTemplate.getComment(dto);
  }

  @Override
  public Page<ServiceBoardsCommentsPageResultDto> getCommentsPage(Pageable pageable,
      CommentsPageConditionDto conditionDto) {
    return commentsServiceImplTemplate.getCommentsPage(pageable, conditionDto);
  }

  @Override
  public void deleteComment(DeleteCommentDto dto) {
    commentsServiceImplTemplate.deleteComment(dto);
  }
}
