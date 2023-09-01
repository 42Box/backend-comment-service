package com.practice.boxcommentservice.global.aop.server_checked_error;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * AddServerCheckedErrorHeader.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AddServerCheckedErrorHeader {

}
