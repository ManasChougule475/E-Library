package com.minorproject.library.e_Library.controller;

import com.minorproject.library.e_Library.entity.Member;
import com.minorproject.library.e_Library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }

    @PostMapping("/add")
    public ResponseEntity<Member> addMember(@RequestBody Member member){
        Member savedMember = this.memberService.addMember(member);
        return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Member>> getAllMember(){
        List<Member> members = this.memberService.getAllMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Member> getMemberById(@RequestParam UUID memberId){
        Member member = this.memberService.getMemberById(memberId);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

//    @PutMapping("/update")
//    public ResponseEntity<Member> updateMember(@RequestBody Member member){
//        Member updatedMember = this.memberService.updateMember(member);
//        return new ResponseEntity<>(updatedMember, HttpStatus.CREATED);
//    }

}
