package com.practice.boxcommentservice.global.aop.validate_nickname_header;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * NicknameHeaderAuthCheck.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HeaderAuthCheck {

  String headerName() default "uuid";
}
