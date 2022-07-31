package com.lms.user.repository;

import com.lms.user.entity.BookIssueAndReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookIssueAndReturnRepo extends JpaRepository<BookIssueAndReturn, Integer> {

    @Query("Select c from  BookIssueAndReturn c where c.registrationNumber = ?1 and c.bookId = ?2")
    BookIssueAndReturn findActivity(int userId, int bookId);


}
