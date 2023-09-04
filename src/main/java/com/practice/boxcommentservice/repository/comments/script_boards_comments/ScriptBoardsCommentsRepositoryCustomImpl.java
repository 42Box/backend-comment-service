package com.practice.boxcommentservice.repository.comments.script_boards_comments;


import static com.practice.boxcommentservice.entity.comments.QScriptBoardsCommentsEntity.scriptBoardsCommentsEntity;

import com.practice.boxcommentservice.entity.comments.ScriptBoardsCommentsEntity;
import com.practice.boxcommentservice.repository.comments.dto.CommentsPageConditionDto;
import com.practice.boxcommentservice.repository.comments.script_boards_comments.dto.QScriptBoardsCommentsPageResultDto;
import com.practice.boxcommentservice.repository.comments.script_boards_comments.dto.ScriptBoardsCommentsPageResultDto;
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
 * ScriptBoardsRepositoryCustomImpl.
 *
 * @author : middlefitting
 * @since : 2023/08/30
 */
public class ScriptBoardsCommentsRepositoryCustomImpl implements
    ScriptBoardsCommentsRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  public ScriptBoardsCommentsRepositoryCustomImpl(EntityManager em) {
    this.queryFactory = new JPAQueryFactory(em);
  }


  private OrderSpecifier<?> mainSort(Pageable pageable) {
    if (!pageable.getSort().isEmpty()) {
      for (Sort.Order order : pageable.getSort()) {
        Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
        return new OrderSpecifier<>(direction, scriptBoardsCommentsEntity.regDate);
      }
    }
    return new OrderSpecifier<>(Order.DESC, scriptBoardsCommentsEntity.regDate);
  }

  @Override
  public Page<ScriptBoardsCommentsPageResultDto> getCommentsPage(Pageable pageable,
      CommentsPageConditionDto condition) {
    List<ScriptBoardsCommentsPageResultDto> content = queryFactory
        .select(new QScriptBoardsCommentsPageResultDto(
                scriptBoardsCommentsEntity.id,
                scriptBoardsCommentsEntity.boardId,
                scriptBoardsCommentsEntity.writerUuid,
                scriptBoardsCommentsEntity.writerNickname,
                scriptBoardsCommentsEntity.writerProfileImagePath,
                scriptBoardsCommentsEntity.content,
                scriptBoardsCommentsEntity.regDate,
                scriptBoardsCommentsEntity.modDate,
                scriptBoardsCommentsEntity.type
            )
        ).from(scriptBoardsCommentsEntity)
        .where(
            writerUuidCondition(condition),
            boardIdCondition(condition),
            allSearchCondition(condition),
            contentSearch(condition),
            nicknameSearch(condition),
            scriptBoardsCommentsEntity.deleted.eq(false)
        )
        .orderBy(
            mainSort(pageable)
        )
        .offset((long) pageable.getPageNumber() * pageable.getPageSize())
        .limit(pageable.getPageSize())
        .fetch();

    JPAQuery<ScriptBoardsCommentsEntity> countQuery = queryFactory
        .selectFrom(scriptBoardsCommentsEntity)
        .where(
            writerUuidCondition(condition),
            boardIdCondition(condition),
            allSearchCondition(condition),
            contentSearch(condition),
            nicknameSearch(condition),
            scriptBoardsCommentsEntity.deleted.eq(false)
        );
    return PageableExecutionUtils.getPage(content, pageable,
        countQuery.fetch()::size);
  }

  private BooleanExpression writerUuidCondition(CommentsPageConditionDto condition) {
    return condition.getWriterUuid().isEmpty() ? null
        : scriptBoardsCommentsEntity.writerUuid.eq(condition.getWriterUuid());
  }

  private BooleanExpression boardIdCondition(CommentsPageConditionDto condition) {
    return (condition.getBoardId() > 0) ? scriptBoardsCommentsEntity.boardId.eq(
        condition.getBoardId()) : null;
  }

  private BooleanExpression allSearchCondition(CommentsPageConditionDto condition) {
    if (!condition.getSearch().isEmpty()
        && (condition.getSearchCondition() == ServiceSearchCondition.ALL)) {
      return scriptBoardsCommentsEntity.content.contains(condition.getSearch())
          .or(scriptBoardsCommentsEntity.writerNickname.contains(condition.getSearch()));
    }
    return null;
  }

  private BooleanExpression contentSearch(CommentsPageConditionDto condition) {
    if (!condition.getSearch().isEmpty() && (
        condition.getSearchCondition() == ServiceSearchCondition.CONTENT)) {
      return scriptBoardsCommentsEntity.content.contains(condition.getSearch());
    }
    return null;
  }

  private BooleanExpression nicknameSearch(CommentsPageConditionDto condition) {
    if (!condition.getSearch().isEmpty() && (
        condition.getSearchCondition() == ServiceSearchCondition.WRITER_NICKNAME)) {
      return scriptBoardsCommentsEntity.writerNickname.contains(condition.getSearch());
    }
    return null;
  }
}
