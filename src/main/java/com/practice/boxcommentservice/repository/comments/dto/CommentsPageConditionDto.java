package com.practice.boxcommentservice.repository.comments.dto;

import com.practice.boxcommentservice.repository.comments.type.ServiceSearchCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CommentsPageConditionDto.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentsPageConditionDto {

  private String search;
  private ServiceSearchCondition searchCondition;
  private String writerUuid;
  private long boardId;
}
