package com.test.securtiy.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //이 클래스를 상속한 엔티티들은 아래 필드들이 컬럼으로 생성
@EntityListeners(AuditingEntityListener.class) //자동으로 값 매핑 기능 추가(변경일)
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedBy
    private LocalDateTime lastModifiedDate;
}
