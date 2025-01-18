package com.minorproject.library.e_Library.controller;

import com.minorproject.library.e_Library.dto.AuthDto;
import com.minorproject.library.e_Library.entity.Member;
import com.minorproject.library.e_Library.exceptions.IncorrectCredentialsException;
import com.minorproject.library.e_Library.repository.MemberRepository;
import com.minorproject.library.e_Library.service.AuthenticationService;
import com.minorproject.library.e_Library.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final MemberService memberService;

    public AuthenticationController(AuthenticationService authenticationService, MemberService memberService){
        this.authenticationService = authenticationService;
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<Member> login(@RequestBody AuthDto authDto){
        try{
            Member member = this.authenticationService.login(authDto);
            return new ResponseEntity<>(member, HttpStatus.OK);
        }catch(UsernameNotFoundException | IncorrectCredentialsException exception){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/signUp")
    public ResponseEntity<Member> addMember(@RequestBody Member member){
        Member memberToAdd = this.memberService.addMember(member);
        return new ResponseEntity<>(memberToAdd, HttpStatus.CREATED);
    }
}
