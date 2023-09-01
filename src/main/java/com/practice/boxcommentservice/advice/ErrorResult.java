package com.practice.boxcommentservice.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ErrorResult.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/24
 */
@AllArgsConstructor
@Data
public class ErrorResult {

  private String msg;
  private int code;
}
