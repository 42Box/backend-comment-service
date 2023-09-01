package com.practice.boxcommentservice.global.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.boxcommentservice.global.env.EnvUtil;
import com.practice.boxcommentservice.global.exception.DefaultServiceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * JsonToObjectConverter.
 *
 * @author : middlefitting
 * @since : 2023/08/29
 */
@Component
@AllArgsConstructor
public class JsonObjectConverter {

  private final ObjectMapper objectMapper;
  private final EnvUtil envUtil;

  public <T> T convertToObject(String rawData, Class<T> type, String errMsg) {
    try {
      return objectMapper.readValue(rawData, type);
    } catch (Exception e) {
      throw new DefaultServiceException(errMsg, envUtil);
    }
  }
}
