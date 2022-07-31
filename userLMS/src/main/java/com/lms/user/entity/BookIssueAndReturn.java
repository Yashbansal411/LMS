package com.lms.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.time.LocalDate;

// table contains info regarding book issue and return
@Data
@Entity
@IdClass(BookIssueAndReturnId.class)
@AllArgsConstructor
@NoArgsConstructor
public class BookIssueAndReturn {

    @Id
    private int registrationNumber;
    @Id
    private int bookId;
    private LocalDate startDate;
    private LocalDate endDate;
}
