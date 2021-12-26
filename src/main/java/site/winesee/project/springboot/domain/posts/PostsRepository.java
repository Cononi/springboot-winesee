package site.winesee.project.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 JpaRepository<Entity 클래스, PK 타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성됩니다.
 @Repository를 추가할 필요도 없습니다.
 주의사항으로는 Entity클래스와 기본 Entity Repository는 함께 위치해야 한다.
 Entity 클래스는 기본 Repository 없니는 제대로 역할을 할 수가 없다.
 */
public interface PostsRepository extends JpaRepository<Posts,Long> {
}
