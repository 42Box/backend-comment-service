package com.practice.boxcommentservice.controller.dto;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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

  @NotEmpty
  @Length(min = 1, max = 500)
  private String commentContent;
}
