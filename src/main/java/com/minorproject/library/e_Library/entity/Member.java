package com.minorproject.library.e_Library.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minorproject.library.e_Library.enums.SubscriptionStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID memberId;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("mobileNumber")
    private String mobileNumber;

    @Email
    @Column(unique = true)
    @JsonProperty("email")
    private String email;

    @JsonProperty("subscriptionStatus")
    private SubscriptionStatus subscriptionStatus = SubscriptionStatus.INACTIVE;

}
