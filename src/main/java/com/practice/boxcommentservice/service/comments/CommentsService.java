package com.practice.boxcommentservice.service.comments;

import com.practice.boxcommentservice.entity.comments.CommentEntity;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageConditionDto;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageResultDto;
import com.practice.boxcommentservice.service.comments.dto.CreateCommentDto;
import com.practice.boxcommentservice.service.comments.dto.DeleteCommentDto;
import com.practice.boxcommentservice.service.comments.dto.GetCommentDto;
import com.practice.boxcommentservice.service.comments.dto.UpdateCommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * CommentsService.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
public interface CommentsService<T extends CommentEntity, S extends CommentsPageResultDto> {

  S createComment(CreateCommentDto dto);

  S updateComment(UpdateCommentDto dto);

  S getComment(GetCommentDto dto);

  Page<S> getCommentsPage(Pageable pageable,
      CommentsPageConditionDto conditionDto);

  void deleteComment(DeleteCommentDto dto);
}
