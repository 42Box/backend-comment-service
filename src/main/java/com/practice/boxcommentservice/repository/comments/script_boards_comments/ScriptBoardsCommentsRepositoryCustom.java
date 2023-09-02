package com.practice.boxcommentservice.repository.comments.script_boards_comments;

import com.practice.boxcommentservice.repository.comments.CommentsBoardsRepositoryCustom;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageConditionDto;
import com.practice.boxcommentservice.repository.comments.script_boards_comments.dto.ScriptBoardsCommentsPageResultDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * ScriptBoardsRepositoryCustom.
 *
 * @author : middlefitting
 * @since : 2023/08/30
 */
public interface ScriptBoardsCommentsRepositoryCustom extends
    CommentsBoardsRepositoryCustom<ScriptBoardsCommentsPageResultDto> {

}
