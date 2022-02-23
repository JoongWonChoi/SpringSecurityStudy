package SpringSecurityEx.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // Spring 시큐리티 필터가 스프링 필터체인에 등록됨. Spring 시큐리티를 사용하기 위한 선언
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*SpringSecurity가 웹을 처리하는 기본 방식은 HttpSession이다.*/


    @Bean//Bean으로 등록된 메서드의 리턴값들은 IoC(Inversion of Controller)에 등록되어 사용할 수 있음
    //즉 BCryptPasswordEncoder타입의 BCryptPasswordEncoder 클래스를 사용 가능
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests() //시큐리티 처리에 HttpServletRequest를 사용한다는 의미
                /*antMatcher() : 특정한 경로를 지정*/
                .antMatchers("/user/**").authenticated()//authenticate : 인증 되면 들어갈 수 있는 주소!
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                //.usernameParameter()
                .loginProcessingUrl("/login")// /login 주소가 호출되면 시큐리티가 낚아채서 대신 로그인 진행해줌
                .defaultSuccessUrl("/");//login 완료되면 기본적으로 이동할 URL주소
    }
    /*formLogin() ==> 로그인 페이지와 기타 로그인의 성공, 실패 등을 처리하기 위한 메서드*/
}
