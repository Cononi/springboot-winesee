package site.winesee.project.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// CRUD를 자동으로 생성해준다.
public interface UserRepository extends JpaRepository<User,Long> {
    /*
    소셜 로그인으로 반환되는 값 중 email을 통해 이미 생성된 사용자인지 처음 가입하는 사용자인지 판단하기위한 메소드.

    Java8에서는 Optional<T> 클래스를 사용해 NPE를 방지할 수 있도록 도와준다. Optional<T>는 null이 올 수 있는 값을 감싸는 Wrapper 클래스로,
     참조하더라도 NPE가 발생하지 않도록 도와준다.
    Optional 클래스는 아래와 같은 value에 값을 저장하기 때문에 null이더라도 바로 NPE가 발생하지 않으며, 클래스이기 때문에 각종 메소드를 제공해준다.
     */
    Optional<User> findByEmail(String email);
}
