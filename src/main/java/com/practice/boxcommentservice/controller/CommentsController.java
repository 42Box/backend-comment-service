package com.practice.boxcommentservice.controller;

import com.practice.boxcommentservice.controller.dto.RequestPostCommentDto;
import com.practice.boxcommentservice.controller.dto.RequestUpdateCommentDto;
import com.practice.boxcommentservice.entity.comments.CommentEntity;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageResultDto;
import com.practice.boxcommentservice.repository.comments.type.ServiceSearchCondition;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

/**
 * CommentsController.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
public interface CommentsController<ENTITY extends CommentEntity, RES_DTO extends CommentsPageResultDto> {

  ResponseEntity<RES_DTO> createComment(HttpServletRequest request, RequestPostCommentDto dto,
      long boardId);

  ResponseEntity<RES_DTO> updateComment(HttpServletRequest request, RequestUpdateCommentDto dto,
      long boardId, long commentId);

  ResponseEntity<RES_DTO> getComment(long boardId, long commentId);

  ResponseEntity<Page<RES_DTO>> getCommentsPage(long boardId, Pageable pageable, String search,
      String writerUuid, ServiceSearchCondition searchCondition);

  ResponseEntity<Void> deleteComment(HttpServletRequest request, long boardId, long commentId);

}
