package com.practice.boxcommentservice.repository.comments;

import com.practice.boxcommentservice.repository.comments.dto.CommentsPageConditionDto;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageResultDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * ScriptBoardsRepositoryCustom.
 *
 * @author : middlefitting
 * @since : 2023/08/30
 */
@NoRepositoryBean
public interface CommentsBoardsRepositoryCustom<T extends CommentsPageResultDto> {

  Page<T> getCommentsPage(Pageable pageable, CommentsPageConditionDto condition);
}
