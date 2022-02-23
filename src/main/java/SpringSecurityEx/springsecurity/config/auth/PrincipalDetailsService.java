package SpringSecurityEx.springsecurity.config.auth;

import SpringSecurityEx.springsecurity.model.User;
import SpringSecurityEx.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//시큐리티 설정에서 loginProcessUrl("/login");
// /login요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어있는 loadUserByUsername 함수가 실행 . .
/*여기서 반환값은 Security Session이 들어감*/
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    //시큐리티 session => Authentication => UserDetails타입 . . 정상 작동 하게 되면
    //시큐리티 session(내부 Authentication(내부 UserDetails)) 이런식으로 차례로 내부에 저장됨!!
    @Override
    /*html input으로 넘어오는 name타입의 "username" => 이곳으로 그대로 넘어옴*/
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username = " + username);
        User userEntity = userRepository.findByUsername(username);
        if (userEntity != null) {
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
