package com.practice.boxcommentservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RequestUpdateCommentDto.
 *
 * @author : middlefitting
 * @since : 2023/09/02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPostCommentDto {

  private String commentContent;
}
