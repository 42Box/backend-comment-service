package com.practice.boxcommentservice.service.comments.script_boards_comments;

import com.practice.boxcommentservice.client.board_client.ScriptBoardClient;
import com.practice.boxcommentservice.entity.comments.ScriptBoardsCommentsEntity;
import com.practice.boxcommentservice.entity.comments.ScriptBoardsCommentsFactory;
import com.practice.boxcommentservice.global.env.EnvUtil;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageConditionDto;
import com.practice.boxcommentservice.repository.comments.script_boards_comments.ScriptBoardsCommentsRepository;
import com.practice.boxcommentservice.repository.comments.script_boards_comments.dto.ScriptBoardsCommentsPageResultDto;
import com.practice.boxcommentservice.repository.comments.script_boards_comments.dto.ScriptBoardsCommentsPageResultFactory;
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
import org.springframework.transaction.annotation.Transactional;

/**
 * ScriptBoardsCommentsService.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
@Service
public class ScriptBoardsCommentsService implements
    CommentsService<ScriptBoardsCommentsEntity, ScriptBoardsCommentsPageResultDto> {

  private final CommentsServiceImplTemplate<ScriptBoardsCommentsEntity, ScriptBoardsCommentsPageResultDto> commentsServiceImplTemplate;


  @Autowired
  public ScriptBoardsCommentsService(
      ScriptBoardsCommentsRepository commentsRepository,
      ScriptBoardsCommentsFactory commentsEntityFactory,
      ScriptBoardsCommentsPageResultFactory commentsPageResultFactory,
      EnvUtil envUtil,
      ScriptBoardClient scriptBoardClient) {
    this.commentsServiceImplTemplate = new CommentsServiceImplTemplate<>(
        commentsRepository, commentsEntityFactory, commentsPageResultFactory, envUtil,
        scriptBoardClient);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ScriptBoardsCommentsPageResultDto createComment(CreateCommentDto dto) {
    return commentsServiceImplTemplate.createComment(dto);
  }

  @Override
  public ScriptBoardsCommentsPageResultDto updateComment(UpdateCommentDto dto) {
    return commentsServiceImplTemplate.updateComment(dto);
  }

  @Override
  public ScriptBoardsCommentsPageResultDto getComment(GetCommentDto dto) {
    return commentsServiceImplTemplate.getComment(dto);
  }

  @Override
  public Page<ScriptBoardsCommentsPageResultDto> getCommentsPage(Pageable pageable,
      CommentsPageConditionDto conditionDto) {
    return commentsServiceImplTemplate.getCommentsPage(pageable, conditionDto);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteComment(DeleteCommentDto dto) {
    commentsServiceImplTemplate.deleteComment(dto);
  }
}
