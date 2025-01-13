package com.minorproject.library.e_Library.entity;

import com.minorproject.library.e_Library.enums.IssueStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
public class IssueData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID issueId;

    @OneToOne
    @NotNull
    private Member member;

    @OneToOne
    @NotNull
    private Book book;

    @NotNull
    @Builder.Default
    private IssueStatus issueStatus = IssueStatus.ISSUED;

    @Builder.Default
    private Instant createdAt = Instant.now();

    private Instant expirationDate;

    @NotNull
    private double amountPaid;

    public Instant calculateExpirationDate() {
        this.expirationDate = this.createdAt.plus ( 15, ChronoUnit.DAYS );
        return this.expirationDate;
    }

    public double calculateAmountPaid() {
        this.amountPaid = this.book.getPrice () * 0.05D;
        return this.amountPaid;
    }

}
