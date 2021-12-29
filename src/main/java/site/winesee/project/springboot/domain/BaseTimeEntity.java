package site.winesee.project.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
BassTimeEntitiy클래스는 모든 Entity의 상위 클래스가 되어 Entity들의 createDate, modifiedDate를 자동으로 관리하는 역활
 */
@Getter // Get메소드를 만들어줌.
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 자동으로 필드들(createDate, modifiedDate)도 칼럼으로 인식하도록 함.
@EntityListeners(AuditingEntityListener.class)  // BaseTimeEntity 클래스에 Auditing(자동으로 시간값을넣어줌) 기능을 포함시킵니다.
public class BaseTimeEntity {

    // Entity가 생성되어 저장될 때 시간이 자동 저장됩니다.
    @CreatedDate
    private LocalDateTime createDate;

    // 조회한 Entity의 값을 변경할 때 시간이 자동 저장됩니다.
    @LastModifiedDate
    private LocalDateTime modifieDate;
}
