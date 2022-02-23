package SpringSecurityEx.springsecurity.config.auth;

//시큐리티가 /login 주소요청이 오면 낚아채서 로그인 진행시킨다.
//로그인 진행이 완료되면 session을 만들고 넣어준다.
//시큐리티 session.(Security ContextHolder)
//시큐리티 세션에 들어갈 수 있는 객체가 정해져있음. => Authentication타입 객체!
//Authentication타입 내부에 User정보가 있어야된다.
//User 타입은 UserDetails타입 객체

//SecuritySession => Authentication객체 => UserDetails(Principal Details포함)타입

import SpringSecurityEx.springsecurity.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/*여기서 반환값은 Authentication(PrincipalDetailsService)에 들어감*/
public class PrincipalDetails implements UserDetails {

    private User user;//콤포지션


    public PrincipalDetails(User user) {
        this.user = user;
    }
    //해당 유저의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        //나의 사이트에서 1년동안 회원이 로그인을 안하면 휴면 계정으로 돌리기로 한다는 가정에 사용
        //현재시간 - 마지막로그인시간 => 1년 초과하면 return false . . .
        return true;
    }
}
