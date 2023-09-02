package com.practice.boxcommentservice.controller;

import com.practice.boxcommentservice.controller.dto.RequestPostCommentDto;
import com.practice.boxcommentservice.controller.dto.RequestUpdateCommentDto;
import com.practice.boxcommentservice.entity.comments.CommentEntity;
import com.practice.boxcommentservice.global.env.EnvUtil;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageConditionDto;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageResultDto;
import com.practice.boxcommentservice.repository.comments.type.ServiceSearchCondition;
import com.practice.boxcommentservice.service.comments.CommentsService;
import com.practice.boxcommentservice.service.comments.dto.CreateCommentDto;
import com.practice.boxcommentservice.service.comments.dto.DeleteCommentDto;
import com.practice.boxcommentservice.service.comments.dto.GetCommentDto;
import com.practice.boxcommentservice.service.comments.dto.UpdateCommentDto;
import javax.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * CommentsControllerImplTemplate.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
public class CommentsControllerImplTemplate<ENTITY extends CommentEntity, RES_DTO extends CommentsPageResultDto> implements
    CommentsController<ENTITY, RES_DTO> {

  private final ModelMapper modelMapper;
  private final CommentsService<ENTITY, RES_DTO> commentsService;

  public CommentsControllerImplTemplate(ModelMapper modelMapper,
      CommentsService<ENTITY, RES_DTO> commentsService) {
    this.modelMapper = modelMapper;
    this.commentsService = commentsService;
  }


  @Override
  public ResponseEntity<RES_DTO> createComment(HttpServletRequest request,
      RequestPostCommentDto dto, long boardId) {
    String userUuid = request.getHeader("uuid");
    String nickname = request.getHeader("nickname");
    String profileImagePath = request.getHeader("profileImagePath");

    CreateCommentDto createCommentDto = modelMapper.map(dto, CreateCommentDto.class);
    createCommentDto.setCommentsBoardId(boardId);
    createCommentDto.setCommentsWriterNickname(nickname);
    createCommentDto.setUserUuid(userUuid);
    createCommentDto.setCommentsWriterProfileImagePath(profileImagePath);
    RES_DTO res_dto = commentsService.createComment(createCommentDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(res_dto);
  }

  @Override
  public ResponseEntity<RES_DTO> updateComment(HttpServletRequest request,
      RequestUpdateCommentDto dto, long boardId,
      long commentId) {
    String userUuid = request.getHeader("uuid");
    UpdateCommentDto updateCommentDto = modelMapper.map(dto, UpdateCommentDto.class);
    updateCommentDto.setCommentBoardId(boardId);
    updateCommentDto.setCommentId(commentId);
    updateCommentDto.setUserUuid(userUuid);
    RES_DTO res_dto = commentsService.updateComment(updateCommentDto);
    return ResponseEntity.status(HttpStatus.OK).body(res_dto);
  }

  @Override
  public ResponseEntity<RES_DTO> getComment(long boardId, long commentId) {
    GetCommentDto getCommentDto = new GetCommentDto();
    getCommentDto.setCommentBoardId(boardId);
    getCommentDto.setCommentId(commentId);
    RES_DTO res_dto = commentsService.getComment(getCommentDto);
    return ResponseEntity.status(HttpStatus.OK).body(res_dto);
  }

  @Override
  public ResponseEntity<Page<RES_DTO>> getCommentsPage(long boardId, Pageable pageable,
      String search, String writerUuid, ServiceSearchCondition getCommentsPage) {
    CommentsPageConditionDto getCommentsPageDto = new CommentsPageConditionDto();
    getCommentsPageDto.setBoardId(boardId);
    getCommentsPageDto.setSearch(search);
    getCommentsPageDto.setWriterUuid(writerUuid);
    getCommentsPageDto.setSearchCondition(getCommentsPage);
    Page<RES_DTO> res_dto = commentsService.getCommentsPage(pageable, getCommentsPageDto);
    return ResponseEntity.status(HttpStatus.OK).body(res_dto);
  }

  @Override
  public ResponseEntity<Void> deleteComment(HttpServletRequest request, long boardId,
      long commentId) {
    String userUuid = request.getHeader("uuid");
    DeleteCommentDto deleteCommentDto = new DeleteCommentDto();
    deleteCommentDto.setCommentBoardId(boardId);
    deleteCommentDto.setCommentId(commentId);
    deleteCommentDto.setUserUuid(userUuid);
    commentsService.deleteComment(deleteCommentDto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
