package com.minorproject.library.e_Library.dto;

import lombok.*;

import java.util.UUID;

//@Getter
//@Setter
public class IssueDataDto {
    private UUID memberId;
    private UUID bookId;

    public UUID getBookId(){
        return bookId;
    }

    public UUID getMemberId(){
        return memberId;
    }
}
