package site.winesee.project.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
 JpaRepository<Entity 클래스, PK 타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성됩니다.
 @Repository를 추가할 필요도 없습니다.
 주의사항으로는 Entity클래스와 기본 Entity Repository는 함께 위치해야 한다.
 Entity 클래스는 기본 Repository 없니는 제대로 역할을 할 수가 없다.
 */
public interface PostsRepository extends JpaRepository<Posts,Long> {
    /*
    Querydsl을 추천하는 이유는 다음과 같다.
    1. 타입 안정성이 보장된다.
    단순한 문자열로 쿼리를 생성하는 것이 아니라. 메소드를 기반으로 쿼리를 생성하기 때문에 오타나 존재하지 않는 컬럼명을 명시할 경우
    IDE에서 자동으로 검출됩니다. 이 장점은 Joop에서도 지원하는 장점이지만, MyBatis에서는 지원하지 않습니다.

    2. 대부분 많은 회사에서 사용 중입니다.
    JPA를 적극적으로 사용하는 회사에서는 Querydsl를 적극적으로 사용 중입니다.

    3. 레퍼런스가 많다.
    앞 2번의 장점에서 이어지는 것인데, 많은 회사와 개발자들이 사용하다보니 그만큼
    군내 자료가 많으며, 어떤 문제가 발생했을 때 여러 커뮤니티에 질문하고 그에 대한 답변을
    들을 수 있다는 것은 큰 장점이다.
     */
    // SpringDataJpa에서 제공하는 기본 메소드만으로 해결가능 하지만, @Query가 훨씬 가독성이 좋아서 선택해서 사용하면된다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
