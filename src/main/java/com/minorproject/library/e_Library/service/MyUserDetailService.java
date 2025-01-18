package com.minorproject.library.e_Library.service;

import com.minorproject.library.e_Library.entity.Member;
import com.minorproject.library.e_Library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {
//    private final String PASSWORD = "$2a$12$zflkbi8n7WPO5n9IApU9DOd/CgSV6BodGfhft2sZOGQPIeKghhrIy";
//    List<User> userList = Arrays.asList(
//            new User("user1",PASSWORD, Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER"))),
//            new User("manas", PASSWORD, Collections.singletonList(new SimpleGrantedAuthority("ROLE_LIBRARIAN")))
//    );
    // implement role based access control (rbac) ,
    // use database credentials

    private final MemberRepository memberRepository;

    @Autowired
    public MyUserDetailService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<Member> member = this.memberRepository.findByUsername(username);
        if (member.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Member with username: %s is not found.", username));
        }
        Member m = member.get(); // fetching member form database , the username and password is compared against data entered during form login
        return User.builder()
                .username(m.getUsername())
                .password(m.getPassword()) // in database password should be in bcrypted form so that it will be compared with user entered password in browser form
                .roles(m.getRole())
                .build();
    }

}
