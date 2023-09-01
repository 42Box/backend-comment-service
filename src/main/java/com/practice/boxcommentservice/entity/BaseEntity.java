package com.practice.boxcommentservice.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * BaseEntity.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/23
 */
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public class BaseEntity {

  @CreatedDate
  @Column(name = "regDate", updatable = false)
  private LocalDateTime regDate;

  @LastModifiedDate
  @Column(name = "modDate", updatable = true)
  private LocalDateTime modDate;
}

