package com.minorproject.library.e_Library.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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

    @Column(unique = true)
    @JsonProperty("email")
    private String email;

    @JsonProperty("subscriptionStatus")
    private SubscriptionStatus subscriptionStatus;

    public enum SubscriptionStatus{
        ACTIVE,
        INACTIVE
    }

}
