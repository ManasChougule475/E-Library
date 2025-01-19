package com.minorproject.library.e_Library.controller;

import com.minorproject.library.e_Library.dto.AuthDto;
import com.minorproject.library.e_Library.dto.AuthResponse;
import com.minorproject.library.e_Library.entity.Member;
import com.minorproject.library.e_Library.exceptions.IncorrectCredentialsException;
import com.minorproject.library.e_Library.repository.MemberRepository;
import com.minorproject.library.e_Library.service.AuthenticationService;
import com.minorproject.library.e_Library.service.MemberService;
import com.minorproject.library.e_Library.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
//    private final AuthenticationService authenticationService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final MemberService memberService;

    public AuthenticationController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, MemberService memberService){
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDto authDto){
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(userDetails, jwtToken));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> addMember(@RequestBody Member member){
        Member memberToAdd = this.memberService.addMember(member);
        UserDetails userDetails = new User(memberToAdd.getUsername(), memberToAdd.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(
                        member.getRole()
                )));
        String jwtToken = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(userDetails, jwtToken));
    }
}
