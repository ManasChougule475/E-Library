package com.minorproject.library.e_Library.service;

import com.minorproject.library.e_Library.dto.IssueDataDto;
import com.minorproject.library.e_Library.entity.Book;
import com.minorproject.library.e_Library.entity.IssueData;
import com.minorproject.library.e_Library.entity.Member;
import com.minorproject.library.e_Library.repository.IssueDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class IssueDataService {

        private final IssueDataRepository issueDataRepository;
        private final BookService bookService;
        private final MemberService memberService;

        @Autowired
        public IssueDataService(IssueDataRepository issueDataRepository, BookService bookService, MemberService memberService){
            this.issueDataRepository = issueDataRepository;
            this.bookService = bookService;
            this.memberService = memberService;
        }

        public IssueData addIssueData(IssueDataDto issueDataDto){
            Book book = this.bookService.getBookById(issueDataDto.getBookId());
            Member member = this.memberService.getMemberById((issueDataDto.getMemberId()));
            if(book==null && member==null){
                throw new RuntimeException();
            }
            IssueData issueData = IssueData.builder().book(book).member(member).build();
            return this.addIssueData(issueData);
        }

        public IssueData addIssueData(IssueData issueData){
            issueData.calculateAmountPaid();
            issueData.calculateExpirationDate();
            return this.issueDataRepository.save(issueData);
        }

        public List<IssueData> getIssueDataByMemberId(UUID memberId){
            return this.issueDataRepository.findByMemberId(memberId);
        }

        public List<IssueData> changeIssueDataStatus(UUID memberId){
            List<IssueData> issueDataList= this.issueDataRepository.findByMemberId(memberId);
            Instant now = Instant.now();
            for (int i=0;i<issueDataList.size(); i++){
                IssueData issueData = issueDataList.get(i);
                if (issueData.getExpirationDate().isBefore(now)) {
                    issueData.setIssueStatus();
                    issueDataRepository.save(issueData); // Save the updated IssueData back to the database
                }
            }

            return issueDataList;
        }
}
