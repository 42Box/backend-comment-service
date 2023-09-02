package com.practice.boxcommentservice.global.utils;

import com.practice.boxcommentservice.global.env.EnvUtil;
import com.practice.boxcommentservice.global.exception.DefaultServiceException;
import com.practice.boxcommentservice.global.exception.ERROR;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

/**
 * JsonToObjectConverter.
 *
 * @author : middlefitting
 * @since : 2023/08/29
 */
@Component
@AllArgsConstructor
public class ObjectValidator {

  private final Validator validator;
  private final EnvUtil envUtil;

  public void validateBodyObject(Object object) {
    DataBinder binder = new DataBinder(object);
    binder.setValidator(validator);
    binder.validate();
    BindingResult results = binder.getBindingResult();
    if (results.hasErrors()) {
      throw new DefaultServiceException(ERROR.GLOBAL_ARGUMENT, envUtil);
    }
  }
}
