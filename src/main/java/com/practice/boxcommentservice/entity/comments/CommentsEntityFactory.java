package com.practice.boxcommentservice.entity.comments;

import com.practice.boxcommentservice.entity.comments.dto.CommentsFactoryDto;

/**
 * LikesEntityFactory.
 *
 * @author : middlefitting
 * @since : 2023/09/01
 */
public interface CommentsEntityFactory<T extends CommentEntity> {

  T create(CommentsFactoryDto dto);
}
