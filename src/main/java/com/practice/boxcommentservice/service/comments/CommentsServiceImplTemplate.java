package com.practice.boxcommentservice.service.comments;

import com.practice.boxcommentservice.client.board_client.BoardsClient;
import com.practice.boxcommentservice.entity.comments.CommentEntity;
import com.practice.boxcommentservice.entity.comments.CommentsEntityFactory;
import com.practice.boxcommentservice.entity.comments.dto.CommentsFactoryDto;
import com.practice.boxcommentservice.global.env.EnvUtil;
import com.practice.boxcommentservice.global.exception.DefaultServiceException;
import com.practice.boxcommentservice.global.exception.ERROR;
import com.practice.boxcommentservice.repository.comments.CommentsRepository;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageConditionDto;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageResultDto;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageResultFactory;
import com.practice.boxcommentservice.service.comments.dto.CreateCommentDto;
import com.practice.boxcommentservice.service.comments.dto.DeleteCommentDto;
import com.practice.boxcommentservice.service.comments.dto.GetCommentDto;
import com.practice.boxcommentservice.service.comments.dto.UpdateCommentDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

/**
 * CommentsServiceImpl.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
public class CommentsServiceImplTemplate<ENTITY extends CommentEntity, RES_DTO extends CommentsPageResultDto> implements
    CommentsService<ENTITY, RES_DTO> {

  private final CommentsRepository<ENTITY, RES_DTO> commentsRepository;
  private final CommentsEntityFactory<ENTITY> commentsEntityFactory;
  private final CommentsPageResultFactory<RES_DTO, ENTITY> commentsPageResultFactory;
  private final EnvUtil envUtil;
  private final BoardsClient<ENTITY> boardsClient;

  public CommentsServiceImplTemplate(CommentsRepository<ENTITY, RES_DTO> commentsRepository,
      CommentsEntityFactory<ENTITY> commentsEntityFactory,
      CommentsPageResultFactory<RES_DTO, ENTITY> commentsPageResultFactory, EnvUtil envUtil,
      BoardsClient<ENTITY> boardsClient) {
    this.commentsRepository = commentsRepository;
    this.commentsEntityFactory = commentsEntityFactory;
    this.commentsPageResultFactory = commentsPageResultFactory;
    this.envUtil = envUtil;
    this.boardsClient = boardsClient;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public RES_DTO createComment(CreateCommentDto dto) {
    CommentsFactoryDto factoryDto = new CommentsFactoryDto(dto);
    ENTITY entity = commentsEntityFactory.create(factoryDto);
    commentsRepository.save(entity);
    HttpStatus boardStatus = boardsClient.increaseComment(dto.getCommentsBoardId());
    if (boardStatus == HttpStatus.NOT_FOUND) {
      throw new DefaultServiceException(ERROR.BOARD_NOT_FOUND, envUtil);
    } else if (boardStatus != HttpStatus.OK) {
      throw new DefaultServiceException(ERROR.BOARD_SERVICE_ERROR, envUtil);
    }
    return commentsPageResultFactory.create(entity);
  }

  @Override
  public RES_DTO updateComment(UpdateCommentDto dto) {
    ENTITY entity = findByIDAndBoardIdAndDeleted(dto.getCommentId(), dto.getCommentBoardId());
    authCheck(entity, dto.getUserUuid());
    entity.update(dto.getCommentContent());
    commentsRepository.save(entity);
    return commentsPageResultFactory.create(entity);
  }

  @Override
  public RES_DTO getComment(GetCommentDto dto) {
    ENTITY entity = findByIDAndBoardIdAndDeleted(dto.getCommentId(), dto.getCommentBoardId());
    return commentsPageResultFactory.create(entity);
  }

  @Override
  public Page<RES_DTO> getCommentsPage(Pageable pageable,
      CommentsPageConditionDto conditionDto) {
    return commentsRepository.getCommentsPage(pageable, conditionDto);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteComment(DeleteCommentDto dto) {
    ENTITY entity = findByIDAndBoardIdAndDeleted(dto.getCommentId(), dto.getCommentBoardId());
    authCheck(entity, dto.getUserUuid());
    entity.delete();
    commentsRepository.save(entity);
    HttpStatus boardStatus = boardsClient.decreaseComment(dto.getCommentBoardId());
    if (boardStatus == HttpStatus.NOT_FOUND) {
      throw new DefaultServiceException(ERROR.BOARD_NOT_FOUND, envUtil);
    } else if (boardStatus != HttpStatus.OK) {
      throw new DefaultServiceException(ERROR.BOARD_SERVICE_ERROR, envUtil);
    }
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////---private---////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////
  private void authCheck(ENTITY entity, String compareId) {
    if (!entity.getWriterUuid().equals(compareId)) {
      throw new DefaultServiceException(ERROR.COMMENT_MOD_AUTH, envUtil);
    }
  }

  private ENTITY findByIDAndBoardIdAndDeleted(long commentId, long boardId) {
    return commentsRepository.findByIdAndBoardIdAndDeleted(commentId, boardId, false)
        .orElseThrow(() -> new DefaultServiceException(ERROR.COMMENT_NOT_FOUND, envUtil));
  }
}
