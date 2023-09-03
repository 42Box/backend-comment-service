package com.practice.boxcommentservice.repository.comments.service_boards_comments;

import com.practice.boxcommentservice.entity.comments.ServiceBoardsCommentsEntity;
import com.practice.boxcommentservice.repository.comments.CommentsRepository;
import com.practice.boxcommentservice.repository.comments.service_boards_comments.dto.ServiceBoardsCommentsPageResultDto;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * serviceBoardsLikesRepository.
 *
 * @author : middlefitting
 * @since : 2023/08/31
 */
@Repository
public interface ServiceBoardsCommentsRepository extends
    CommentsRepository<ServiceBoardsCommentsEntity, ServiceBoardsCommentsPageResultDto>,
    ServiceBoardsCommentsRepositoryCustom,
    QuerydslPredicateExecutor<ServiceBoardsCommentsEntity> {

}
