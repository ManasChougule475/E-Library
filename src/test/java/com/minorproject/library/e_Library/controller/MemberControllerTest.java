package com.minorproject.library.e_Library.controller;
import com.minorproject.library.e_Library.ELibraryApplication;
import com.minorproject.library.e_Library.entity.Member;
import com.minorproject.library.e_Library.enums.SubscriptionStatus;
import com.minorproject.library.e_Library.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


import java.util.UUID;

@SpringBootTest(classes = ELibraryApplication.class)
public class MemberControllerTest {

    @MockitoBean
    private MemberService memberService;

    private final MemberController memberController;

    @Autowired
    public MemberControllerTest(MemberController memberController) {
        this.memberController = memberController;
    }

    static final Member member = Member.builder()
            .email("testEmail@email.com")
            .memberId(UUID.randomUUID())
            .firstName("Test FName")
            .lastName("Test LName")
            .mobileNumber("testNum")
            .subscriptionStatus(SubscriptionStatus.ACTIVE)
            .build();

//    @Test
//    void addMember_whenAValidMemberIsPassed_shouldReturnCREATED() {
//        Mockito.when(this.memberService.addMember(member)).thenReturn(member);
//
//        ResponseEntity<Member> responseEntity = this.memberController.addMember(member);
//
//        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        Assertions.assertEquals(member, responseEntity.getBody());
//    }
}
