package SpringSecurityEx.springsecurity.repository;

import SpringSecurityEx.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//CRUD 함수를 JpaRepository가 들고 있음
public interface UserRepository extends JpaRepository<User,Integer> {

    //findBy 규칙 -> Username 문법
    //select * from user where username = username
    public User findByUsername(String username);

    /*//select * from user where email=>. .
    public User findByEmail();*/

    //Jpa query methods 문법 공부!!
}
