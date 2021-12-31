package site.winesee.project.springboot.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.winesee.project.springboot.domain.BaseTimeEntity;

import javax.persistence.*;

//javax.persistence : 관계형 DB에 사용
//org.springframework.data.annotation : JPA에 의해 지원되지 않는 Nosql이나 프레임워크에서 사용

@Getter // Get생성
@NoArgsConstructor // 파라매터가 없는 기본 생성자 생성
@Entity // 이 클래스는 JPA가 관리를 한다. DB의 테이블과 Class(VO,DTO)와 맵핑하려면 반드시 선언 해야한다.
/*
제약사항
1. final, enum, interface, class를 사용할 수 없다.
2. 생성자중 기본 생성자가 반드시 필요한다.
 */
// JPA
public class User extends BaseTimeEntity { // BaseTimeEntity (BaseTime Auditing 자동으로 시간 필드 인식)

    @Id // Primary Key : PK 선언 ( 직접 할당 )
    /*
    GeneratedValue ( 자동 생성 - 키값 자동 증가할건지. )
    IDENTITY : 데이터 베이스에 위임(MYSQL)
      - Auto_increment
    SEQUNECE : 데이터베이스 시퀀스 오브젝트 사용(ORACLE)
      - @Sequence@Generator 필요
    TABLE : 키 생성용 테이블 사용, 모든 DB에서 사용
      - @TableGenerator 필요
    AUTO : 방언에 따라 자동 지정, 기본값

    사용시 -
    transaction.commit()을 호출할 때가 아니라 entityManager.persist() 가 호출될 때 바로 DB에 insert 쿼리 를 날림
    JPA 내부에서 Insert 쿼리 실행 후 바로 생성된 productId 값을 리턴 받음
    productId 를 PK 로 영속성 컨텍스트에 저장
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Column 어노테이션은 객체 필드와 DB 테이블 컬럼을 맵핑한다.
    @Column(nullable = false) // Null 비허용
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    /*
    1. JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지를 결정한다.
    2. 기본적으로는 int로 된 숫자가 저장된다.
    3. 숫자로 저장되면 데이터베이스로 확인할 때 그 값이 무슨 코드를 의미하는지 알 수가 없다.
    4. 그래서 문자열 (EnumType.String)로 저장될 수 있도록 선언한다.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // 모델 객체를 생성할 때 Builder를 자동으로 추가해 주는 Annotation.
    /*
    빌더 패턴(Builder pattern) 이란 복합 객체의 생성 과정과 표현 방법을 분리하여
     동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 하는 패턴이다.
    */
    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
