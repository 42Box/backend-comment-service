package com.practice.boxcommentservice.controller.script_boards_comments;

import com.practice.boxcommentservice.controller.CommentsController;
import com.practice.boxcommentservice.controller.CommentsControllerImplTemplate;
import com.practice.boxcommentservice.controller.dto.RequestPostCommentDto;
import com.practice.boxcommentservice.controller.dto.RequestUpdateCommentDto;
import com.practice.boxcommentservice.entity.comments.ScriptBoardsCommentsEntity;
import com.practice.boxcommentservice.global.aop.validate_nickname_header.HeaderAuthCheck;
import com.practice.boxcommentservice.repository.comments.script_boards_comments.dto.ScriptBoardsCommentsPageResultDto;
import com.practice.boxcommentservice.repository.comments.type.ServiceSearchCondition;
import com.practice.boxcommentservice.service.comments.CommentsServiceImplTemplate;
import com.practice.boxcommentservice.service.comments.script_boards_comments.ScriptBoardsCommentsService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ScriptBoardsCommentsController.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
@RestController
@RequestMapping("/script-boards")
public class ScriptBoardsCommentsController implements
    CommentsController<ScriptBoardsCommentsEntity, ScriptBoardsCommentsPageResultDto> {

  private final CommentsControllerImplTemplate<ScriptBoardsCommentsEntity, ScriptBoardsCommentsPageResultDto> commentControllerImpl;

  @Autowired
  public ScriptBoardsCommentsController(ModelMapper modelMapper,
      ScriptBoardsCommentsService scriptBoardsCommentsService) {
    this.commentControllerImpl = new CommentsControllerImplTemplate<>(modelMapper,
        scriptBoardsCommentsService);
  }


  @Override
  @HeaderAuthCheck
  @PostMapping("/{boardId}/comments")
  public ResponseEntity<ScriptBoardsCommentsPageResultDto> createComment(HttpServletRequest request,
      @RequestBody @Valid RequestPostCommentDto dto, @PathVariable long boardId) {
    return commentControllerImpl.createComment(request, dto, boardId);
  }

  @Override
  @HeaderAuthCheck
  @PutMapping("/{boardId}/comments/{commentId}")
  public ResponseEntity<ScriptBoardsCommentsPageResultDto> updateComment(HttpServletRequest request,
      @RequestBody @Valid RequestUpdateCommentDto dto, @PathVariable long boardId,
      @PathVariable long commentId) {
    return commentControllerImpl.updateComment(request, dto, boardId, commentId);
  }

  @Override
  @GetMapping("/{boardId}/comments/{commentId}")
  public ResponseEntity<ScriptBoardsCommentsPageResultDto> getComment(@PathVariable long boardId,
      @PathVariable long commentId) {
    return commentControllerImpl.getComment(boardId, commentId);
  }

  @Override
  @GetMapping("/{boardId}/comments")
  public ResponseEntity<Page<ScriptBoardsCommentsPageResultDto>> getCommentsPage(
      @PathVariable long boardId,
      @PageableDefault(page = 0, size = 5, sort = "regDate", direction = Direction.DESC) Pageable pageable,
      @RequestParam(required = false, defaultValue = "") String search,
      @RequestParam(required = false, defaultValue = "") String writerUuid,
      @RequestParam(required = false, defaultValue = "NONE") ServiceSearchCondition searchCondition) {
    return commentControllerImpl.getCommentsPage(boardId, pageable, search, writerUuid,
        searchCondition);
  }

  @Override
  @HeaderAuthCheck
  @DeleteMapping("/{boardId}/comments/{commentId}")
  public ResponseEntity<Void> deleteComment(HttpServletRequest request,
      @PathVariable long boardId,
      @PathVariable long commentId) {
    return commentControllerImpl.deleteComment(request, boardId, commentId);
  }
}
