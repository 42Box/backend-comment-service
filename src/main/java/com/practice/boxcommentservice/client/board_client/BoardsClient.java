package com.practice.boxcommentservice.client.board_client;

import com.practice.boxcommentservice.entity.comments.CommentEntity;
import org.springframework.http.HttpStatus;

/**
 * UserClient.
 *
 * @author : middlefitting
 * @since : 2023/08/31
 */

public interface BoardsClient<ENTITY extends CommentEntity> {

  HttpStatus increaseComment(long boardId);

  HttpStatus decreaseComment(long boardId);
}
