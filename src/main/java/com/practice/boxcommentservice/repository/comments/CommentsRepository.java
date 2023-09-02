package com.practice.boxcommentservice.repository.comments;

import com.practice.boxcommentservice.entity.comments.CommentEntity;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageResultDto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * CommentsRepository.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
@NoRepositoryBean
public interface CommentsRepository<T extends CommentEntity, S extends CommentsPageResultDto> extends
    JpaRepository<T, Long>, CommentsBoardsRepositoryCustom<S> {

  Optional<T> findByIdAndDeleted(long commentId, boolean deleted);
}
