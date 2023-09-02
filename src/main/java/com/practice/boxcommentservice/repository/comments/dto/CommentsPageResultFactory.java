package com.practice.boxcommentservice.repository.comments.dto;

import com.practice.boxcommentservice.entity.comments.CommentEntity;
import com.practice.boxcommentservice.entity.comments.dto.CommentsFactoryDto;

/**
 * LikesEntityFactory.
 *
 * @author : middlefitting
 * @since : 2023/09/01
 */
public interface CommentsPageResultFactory<T extends CommentsPageResultDto, S extends CommentEntity> {

  T create(S entity);
}
