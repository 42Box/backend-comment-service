package com.practice.boxcommentservice.repository.comments.script_boards_comments;

import com.practice.boxcommentservice.entity.comments.ScriptBoardsCommentsEntity;
import com.practice.boxcommentservice.repository.comments.CommentsRepository;
import com.practice.boxcommentservice.repository.comments.script_boards_comments.dto.ScriptBoardsCommentsPageResultDto;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * ScriptBoardsLikesRepository.
 *
 * @author : middlefitting
 * @since : 2023/08/31
 */
@Repository
public interface ScriptBoardsCommentsRepository extends
    CommentsRepository<ScriptBoardsCommentsEntity, ScriptBoardsCommentsPageResultDto>,
    ScriptBoardsCommentsRepositoryCustom,
    QuerydslPredicateExecutor<ScriptBoardsCommentsEntity> {

}
