package ezenweb.model.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTime {

    // 1. 레코드/엔티티 등록날짜
    @CreatedDate // default now
    protected LocalDateTime cdate;
    //2.레코드 엔티티 수정날짜
    @LastModifiedDate
    protected LocalDateTime udate;
}
