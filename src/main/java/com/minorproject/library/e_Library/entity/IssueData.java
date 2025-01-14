package com.minorproject.library.e_Library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.minorproject.library.e_Library.enums.IssueStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static com.minorproject.library.e_Library.enums.IssueStatus.EXPIRED;

@Entity
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
public class IssueData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID issueId;

    @ManyToOne
    @NotNull
    @JsonProperty("member")
//    @JsonIgnore
    private Member member;

//    @JsonProperty(value = "memberId")
//    public UUID getMemberId(){
//        return this.member.getMemberId(); // just returns member id instead of returning entire member
//    }


    @ManyToOne
    @NotNull
    @JsonProperty("book")
//    @JsonIncludeProperties({"id", "name", "author"})
    private Book book;

    @NotNull
    @Builder.Default
    @JsonProperty("issueStatus")
    private IssueStatus issueStatus = IssueStatus.ISSUED;

    @Builder.Default
    @JsonProperty("createdAt")
    private Instant createdAt = Instant.now();

    @JsonProperty("expirationDate")
    private Instant expirationDate;

    @NotNull
    private double amountPaid;

    public Instant calculateExpirationDate() {
        this.expirationDate = this.createdAt.plus ( 1, ChronoUnit.MINUTES );
        return this.expirationDate;
    }

    public double calculateAmountPaid() {
        this.amountPaid = this.book.getPrice () * 0.05D;
        return this.amountPaid;
    }

    public void setIssueStatus(){
        this.issueStatus = IssueStatus.EXPIRED;
    }
    public Instant getExpirationDate(){
        return this.expirationDate;
    }

    // code added cause lombok not working :-  IssueData.builder() :-
    public IssueData(){

    }
    private IssueData(Book book, Member member) {
        this.book = book;
        this.member = member;
    }

    // Builder Inner Class
    public static class IssueDataBuilder {
        private Book book;
        private Member member;

        public IssueDataBuilder book(Book book) {
            this.book = book;
            return this;
        }

        public IssueDataBuilder member(Member member) {
            this.member = member;
            return this;
        }

        public IssueData build() {
            return new IssueData(this.book, this.member);
        }
    }

    public static IssueDataBuilder builder() {
        return new IssueDataBuilder();
    }

}
