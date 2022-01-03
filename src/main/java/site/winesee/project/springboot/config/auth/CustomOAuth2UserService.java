package site.winesee.project.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import site.winesee.project.springboot.domain.user.User;
import site.winesee.project.springboot.domain.user.UserRepository;
import site.winesee.project.springboot.web.dto.OAuthAttributes;
import site.winesee.project.springboot.web.dto.SessionUser;

import javax.servlet.http.HttpSession;
import java.util.Collections;

/*
구글 로그인 이후 가져온 사용자 정보(email, name, picture 등)들을 기반으로 가입 및 정보수정, 세션 저장 등의 기능을 지원.
 */

// final이나 NonNull 필드에 대해 생성자를 생성함.
@RequiredArgsConstructor
// 서비스 선언
@Service
/*
OAuth2UserService 인터페이스를 상속받아 구현및 재정의

 */
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    // CURD 생성
    private final UserRepository userRepository;
    // 세션 생성
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName
                , oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user",new SessionUser(user));

        return  new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                                        attributes.getAttributes(),
                                        attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());
        return  userRepository.save(user);
    }

}
