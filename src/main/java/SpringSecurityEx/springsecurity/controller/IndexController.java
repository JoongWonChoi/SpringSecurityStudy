package SpringSecurityEx.springsecurity.controller;

import SpringSecurityEx.springsecurity.model.User;
import SpringSecurityEx.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"", "/"})
    public String index() {
        //기본 폴더 경로 ==> src/main/resources
        //뷰 리졸버 설정 : templates(prefix) + string + .html
        return "index";
    }
    @GetMapping("/user")
    public @ResponseBody String user() {
        return "user";
    }
    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "admin";
    }
    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "manager";
    }
    @GetMapping("/loginForm")
    public String login() {
        return "loginForm";
    }
    /*원하는 뷰를 출력하기 전에 스프링 세큐리티가 해당 주소를 인터셉트 하여 스프링 세큐리티가 제공하는 뷰를 출력.(로그인)
    * -->but SecurityConfig파일 생성 후에 작동하지 않음. 즉, 원하는 뷰가 출력 됨.*/
    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @PostMapping("/join")
    public @ResponseBody String join(User user) {
        System.out.println(user);
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);

        userRepository.save(user); //회원가입 잘 됨
        return "join";
    }
}
