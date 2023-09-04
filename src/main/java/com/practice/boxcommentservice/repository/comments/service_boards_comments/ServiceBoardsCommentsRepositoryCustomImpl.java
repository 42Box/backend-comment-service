package com.practice.boxcommentservice.repository.comments.service_boards_comments;


import static com.practice.boxcommentservice.entity.comments.QScriptBoardsCommentsEntity.scriptBoardsCommentsEntity;
import static com.practice.boxcommentservice.entity.comments.QServiceBoardsCommentsEntity.serviceBoardsCommentsEntity;

import com.practice.boxcommentservice.entity.comments.ServiceBoardsCommentsEntity;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageConditionDto;
import com.practice.boxcommentservice.repository.comments.service_boards_comments.dto.QServiceBoardsCommentsPageResultDto;
import com.practice.boxcommentservice.repository.comments.service_boards_comments.dto.ServiceBoardsCommentsPageResultDto;
import com.practice.boxcommentservice.repository.comments.type.ServiceSearchCondition;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;


/**
 * serviceBoardsRepositoryCustomImpl.
 *
 * @author : middlefitting
 * @since : 2023/08/30
 */
public class ServiceBoardsCommentsRepositoryCustomImpl implements
    ServiceBoardsCommentsRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  public ServiceBoardsCommentsRepositoryCustomImpl(EntityManager em) {
    this.queryFactory = new JPAQueryFactory(em);
  }


  private OrderSpecifier<?> mainSort(Pageable pageable) {
    if (!pageable.getSort().isEmpty()) {
      for (Sort.Order order : pageable.getSort()) {
        Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
        return new OrderSpecifier<>(direction, serviceBoardsCommentsEntity.regDate);
      }
    }
    return new OrderSpecifier<>(Order.DESC, serviceBoardsCommentsEntity.regDate);
  }

  @Override
  public Page<ServiceBoardsCommentsPageResultDto> getCommentsPage(Pageable pageable,
      CommentsPageConditionDto condition) {
    List<ServiceBoardsCommentsPageResultDto> content = queryFactory
        .select(new QServiceBoardsCommentsPageResultDto(
                serviceBoardsCommentsEntity.id,
                serviceBoardsCommentsEntity.boardId,
                serviceBoardsCommentsEntity.writerUuid,
                serviceBoardsCommentsEntity.writerNickname,
                serviceBoardsCommentsEntity.writerProfileImagePath,
                serviceBoardsCommentsEntity.content,
                serviceBoardsCommentsEntity.regDate,
                serviceBoardsCommentsEntity.modDate,
                serviceBoardsCommentsEntity.type
            )
        ).from(serviceBoardsCommentsEntity)
        .where(
            writerUuidCondition(condition),
            boardIdCondition(condition),
            allSearchCondition(condition),
            contentSearch(condition),
            nicknameSearch(condition),
            serviceBoardsCommentsEntity.deleted.eq(false)
        )
        .orderBy(
            mainSort(pageable)
        )
        .offset((long) pageable.getPageNumber() * pageable.getPageSize())
        .limit(pageable.getPageSize())
        .fetch();

    JPAQuery<ServiceBoardsCommentsEntity> countQuery = queryFactory
        .selectFrom(serviceBoardsCommentsEntity)
        .where(
            writerUuidCondition(condition),
            boardIdCondition(condition),
            allSearchCondition(condition),
            contentSearch(condition),
            nicknameSearch(condition),
            serviceBoardsCommentsEntity.deleted.eq(false)
        );
    return PageableExecutionUtils.getPage(content, pageable,
        countQuery.fetch()::size);
  }

  private BooleanExpression writerUuidCondition(CommentsPageConditionDto condition) {
    return condition.getWriterUuid().isEmpty() ? null
        : serviceBoardsCommentsEntity.writerUuid.eq(condition.getWriterUuid());
  }

  private BooleanExpression boardIdCondition(CommentsPageConditionDto condition) {
    return (condition.getBoardId() > 0) ? serviceBoardsCommentsEntity.boardId.eq(
        condition.getBoardId()) : null;
  }

  private BooleanExpression allSearchCondition(CommentsPageConditionDto condition) {
    if (!condition.getSearch().isEmpty()
        && (condition.getSearchCondition() == ServiceSearchCondition.ALL)) {
      return serviceBoardsCommentsEntity.content.contains(condition.getSearch())
          .or(serviceBoardsCommentsEntity.writerNickname.contains(condition.getSearch()));
    }
    return null;
  }

  private BooleanExpression contentSearch(CommentsPageConditionDto condition) {
    if (!condition.getSearch().isEmpty() && (
        condition.getSearchCondition() == ServiceSearchCondition.CONTENT)) {
      return serviceBoardsCommentsEntity.content.contains(condition.getSearch());
    }
    return null;
  }

  private BooleanExpression nicknameSearch(CommentsPageConditionDto condition) {
    if (!condition.getSearch().isEmpty() && (
        condition.getSearchCondition() == ServiceSearchCondition.WRITER_NICKNAME)) {
      return serviceBoardsCommentsEntity.writerNickname.contains(condition.getSearch());
    }
    return null;
  }
}
