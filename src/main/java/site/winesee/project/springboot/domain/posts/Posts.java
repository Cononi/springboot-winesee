package site.winesee.project.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.winesee.project.springboot.domain.BaseTimeEntity;

import javax.persistence.*;

// lombok 어노테이션 겟메소드 생성, 파라메터가 없는 기본 생성자 생성
@Getter
@NoArgsConstructor
// JPA의 어노테이션
@Entity
/*
실제 DB와 매칭될 클래스이며 Entity 클래스라고도 한다.
DB 데이터에 작업할 경우 실제 쿼리를 날리기보다 이 Entity 클래스의 수정을 통해 작업한다.
@Entity 테이블과 링크될 클래스임을 나타냅니다.
기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭합니다.
ex) SalesManager java -> sales_manager table

@id 해당 테이블의 PK 필드를 나타냅니다.
@GeneratiedValue
PK 생성규칙을 나타냅니다.
스프링 부트 2.0에서는 GenerationType.IDENTITY 옵셩을 추가해야만 auto_increment가 됩니다.
@Column
테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됩니다.
사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용합니다.
문자열 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나
타입을 TEXT로 변경하고 싶거나 등의 경우에 사용됩니다.
 */
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String auther;

    /*
    해당 클래스의 빌더 패턴 클래스를 생성
    생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    필요한 데이터만 설정할 수 있음
    유연성을 확보할 수 있음
    가독성을 높일 수 있음
    불변성을 확보할 수 있음
    - 결과적으로 필요한것만 동적으로 사용가능 -
     */
    @Builder
    public Posts(String title, String content, String auther){
        this.title = title;
        this.content = content;
        this.auther = auther;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
