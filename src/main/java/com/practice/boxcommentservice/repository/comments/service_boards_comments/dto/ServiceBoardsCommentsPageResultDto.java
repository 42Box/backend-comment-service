package com.practice.boxcommentservice.repository.comments.service_boards_comments.dto;

import com.practice.boxcommentservice.entity.comments.ServiceBoardsCommentsEntity;
import com.practice.boxcommentservice.entity.comments.type.MyCommentsType;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageResultDto;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

/**
 * serviceBoardsCommentsPageResultDto.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
@NoArgsConstructor
public class ServiceBoardsCommentsPageResultDto extends CommentsPageResultDto {

  public ServiceBoardsCommentsPageResultDto(ServiceBoardsCommentsEntity entity) {
    super(entity);
  }

  @QueryProjection
  public ServiceBoardsCommentsPageResultDto(Long commentId, Long commentBoardId,
      String commentWriterUuid, String commentWriterNickname, String commentWriterProfileImagePath,
      String commentContent, LocalDateTime commentRegDate, LocalDateTime commentModDate,
      MyCommentsType commentType) {
    this.commentId = commentId;
    this.commentBoardId = commentBoardId;
    this.commentWriterUuid = commentWriterUuid;
    this.commentWriterNickname = commentWriterNickname;
    this.commentWriterProfileImagePath = commentWriterProfileImagePath;
    this.commentContent = commentContent;
    this.commentRegDate = commentRegDate;
    this.commentModDate = commentModDate;
    this.commentType = commentType;
  }
}
