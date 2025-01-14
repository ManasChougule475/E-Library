package com.minorproject.library.e_Library.dto;

import lombok.*;

import java.util.UUID;

//@Getter
//@Setter
public class IssueDataDto { // instead of passing entire member and book object we can use pass there ids in our request
    private UUID memberId;
    private UUID bookId;

    public UUID getBookId(){
        return bookId;
    }

    public UUID getMemberId(){
        return memberId;
    }
}
