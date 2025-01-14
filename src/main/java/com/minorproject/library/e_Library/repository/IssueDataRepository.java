package com.minorproject.library.e_Library.repository;

import com.minorproject.library.e_Library.entity.IssueData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IssueDataRepository extends JpaRepository<IssueData, UUID> {
    @Query("select i from IssueData i where i.member.memberId = ?1")
    List<IssueData> findByMemberId(UUID memberId); // this will give list of all the issue tickets associated with given member id

}
