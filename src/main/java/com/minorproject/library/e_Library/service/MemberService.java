package com.minorproject.library.e_Library.service;

import com.minorproject.library.e_Library.entity.Member;
import com.minorproject.library.e_Library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member){
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
