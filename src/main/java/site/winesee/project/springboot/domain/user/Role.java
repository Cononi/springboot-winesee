package site.winesee.project.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
// 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다.
// 열거형 타입 : 요소나 멤버라 불리는 명명된 값의 집합을 이루는 자료형.(열거자 이름들은 대체적으로 상수 역할을 하는 식별자)
public enum Role {
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}

/*
스프링 시큐리트에서는 권한 코드에 항상 ROLE_이 앞에 있어야만 한다. 그래서 코드별 키 값을 ROLE_GUEST, ROLE_USER 등으로 지정한다.
 */