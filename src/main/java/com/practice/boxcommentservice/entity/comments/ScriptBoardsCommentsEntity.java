package com.practice.boxcommentservice.entity.comments;

import com.practice.boxcommentservice.entity.BaseEntity;
import com.practice.boxcommentservice.entity.comments.type.MyCommentsType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ScriptBoardsComments.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
@Entity
@Table(name = "script_boards_comments",
    indexes = {
        @Index(name = "idx_comment_writer_uuid", columnList = "comment_writer_uuid", unique = false),
        @Index(name = "idx_comment_board_id", columnList = "comment_board_id", unique = false),
        @Index(name = "idx_comment_deleted", columnList = "comment_deleted", unique = false),
    })
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class ScriptBoardsCommentsEntity extends BaseEntity implements CommentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id", updatable = false)
  private long id;

  @Column(name = "comment_board_id", nullable = false, updatable = false)
  private Long boardId;

  @Column(name = "comment_writer_uuid", nullable = false, columnDefinition = "VARCHAR(255)")
  private String writerUuid;

  @Column(name = "comment_writer_nickname", nullable = false, columnDefinition = "VARCHAR(50)")
  private String writerNickname;

  @Column(name = "comment_writer_profile_image_path", nullable = false, columnDefinition = "VARCHAR(255)")
  private String writerProfileImagePath;

  @Column(name = "comment_content", nullable = false, columnDefinition = "VARCHAR(500)")
  private String content;

  @Column(name = "comment_deleted")
  private boolean deleted;

  @Column(name = "comment_type", nullable = false)
  private MyCommentsType type;


  @Builder
  public ScriptBoardsCommentsEntity(Long boardId, String writerUuid, String writerNickname,
      String writerProfileImagePath, String content) {
    this.boardId = boardId;
    this.writerUuid = writerUuid;
    this.writerNickname = writerNickname;
    this.writerProfileImagePath = writerProfileImagePath;
    this.content = content;
    this.deleted = false;
    this.type = MyCommentsType.SCRIPT_BOARDS;
  }

  @Override
  public void update(String content) {
    this.content = content;
  }

  @Override
  public void delete() {
    this.deleted = true;
  }
}
