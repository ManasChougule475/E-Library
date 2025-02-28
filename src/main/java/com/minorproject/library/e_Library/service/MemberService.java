package com.minorproject.library.e_Library.service;

import com.minorproject.library.e_Library.entity.Member;
import com.minorproject.library.e_Library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder){
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member addMember(Member member){
        System.out.println("*********");
        member.setPassword(this.passwordEncoder.encode(member.getPassword())); // to save password in encode format in db
        return this.memberRepository.save(member);
    }

    public List<Member> getAllMembers(){
        return this.memberRepository.findAll();
    }

    public Member getMemberById(UUID memberId){
        Optional<Member> memberOptional= this.memberRepository.findById(memberId);
        return memberOptional.orElse(null);
    }

//    public Member updateMember(Member member){
//        Optional<Member> existingMember = this.memberRepository.findById(member.getMemberId());
//
//        if(existingMember.isPresent()){
//            existingMember.get().setFirstName(member.getFirstName());
//            existingMember.get().withLastName(member.getLastName());
//            existingMember.get().setEmail(member.getEmail());
//            existingMember.get().setMobileNumber(member.getMobileNumber());
//            existingMember.get().setSubscriptionStatus(member.getSubscriptionStatus());
//            return this.memberRepository.save(existingMember.get());
//        }else{
//            return null;
//        }
//    }

}
