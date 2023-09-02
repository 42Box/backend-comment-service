package com.practice.boxcommentservice.repository.comments.dto;

import com.practice.boxcommentservice.entity.comments.CommentEntity;
import com.practice.boxcommentservice.entity.comments.ScriptBoardsCommentsEntity;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * CommentsPageResultDto.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentsPageResultDto {

  protected Long commentId;
  protected Long commentBoardId;
  protected String commentWriterUuid;
  protected String commentWriterNickname;
  protected String commentWriterProfileImagePath;
  protected String commentContent;
  protected LocalDateTime commentRegDate;
  protected LocalDateTime commentModDate;


  public void initByScriptCommentEntity(CommentEntity entity) {
    this.commentId = entity.getId();
    this.commentBoardId = entity.getBoardId();
    this.commentWriterUuid = entity.getWriterUuid();
    this.commentWriterNickname = entity.getWriterNickname();
    this.commentWriterProfileImagePath = entity.getWriterProfileImagePath();
    this.commentContent = entity.getContent();
    this.commentRegDate = entity.getRegDate();
    this.commentModDate = entity.getModDate();
  }
}
