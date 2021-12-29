package site.winesee.project.springboot.web;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import site.winesee.project.springboot.service.posts.PostsService;

// final이 선언된 모든 필드를 인자값으로 하는 생성자.
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    /*
    Model 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있습니다.
    여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달합니다.
     */
    @GetMapping("/")
    public String index(Model model) {
        // 모델에 실어서 List 전송
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
