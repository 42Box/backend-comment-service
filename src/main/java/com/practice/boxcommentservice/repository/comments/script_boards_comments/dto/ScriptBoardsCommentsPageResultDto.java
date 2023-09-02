package com.practice.boxcommentservice.repository.comments.script_boards_comments.dto;

import com.practice.boxcommentservice.repository.comments.dto.CommentsPageResultDto;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ScriptBoardsCommentsPageResultDto.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
@NoArgsConstructor
public class ScriptBoardsCommentsPageResultDto extends CommentsPageResultDto {

  @QueryProjection
  public ScriptBoardsCommentsPageResultDto(Long commentId, Long commentBoardId,
      String commentWriterUuid, String commentWriterNickname, String commentWriterProfileImagePath,
      String commentContent, LocalDateTime commentRegDate, LocalDateTime commentModDate) {
    this.commentId = commentId;
    this.commentBoardId = commentBoardId;
    this.commentWriterUuid = commentWriterUuid;
    this.commentWriterNickname = commentWriterNickname;
    this.commentWriterProfileImagePath = commentWriterProfileImagePath;
    this.commentContent = commentContent;
    this.commentRegDate = commentRegDate;
    this.commentModDate = commentModDate;
  }
}
