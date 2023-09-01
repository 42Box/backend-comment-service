package com.practice.boxcommentservice;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * BoxUserServiceApplication.
 * <p>이 클래스는 Spring Boot 어플리케이션을 시작하는 역할을 합니다. {@link SpringBootApplication} 애노테이션을 사용해
 * Spring Boot 의 자동설정, Bean 등록 등을 자동으로 수행합니다.</p>
 * <p>이 클래스에서는 다음과 같은 작업을 수행합니다.</p>
 * <ul>
 * <li>{@link ModelMapper}를 Bean으로 등록합니다. 이를 통해 객체 간의 매핑 작업을 수행할 수 있습니다.</li>
 * <li>{@link BCryptPasswordEncoder}를 Bean으로 등록합니다. 이를 통해 비밀번호를 암호화/복호화할 수 있습니다.</li>
 * </ul>
 *
 * @author middlefitting
 * @version 1.0.0
 * @see SpringBootApplication
 * @since 2023-08-23
 */
@SpringBootApplication
@EnableJpaAuditing
public class BoxCommentServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BoxCommentServiceApplication.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper mapper = new ModelMapper();
    mapper.getConfiguration()
        .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
        .setFieldMatchingEnabled(true)
        .setMatchingStrategy(MatchingStrategies.STRICT);
    return mapper;
  }
}
