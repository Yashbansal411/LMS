package com.lms.user.service;

import com.lms.user.exception.BookAlreadyIssued;
import com.lms.user.exception.FineGreaterThanZero;
import com.lms.user.exception.NoCopiesLeftForIssue;

public interface BookIssueAndReturnService {
    public String issueBook(int userId, int bookId) throws BookAlreadyIssued, FineGreaterThanZero, NoCopiesLeftForIssue;

    public String returnBook(int userId, int bookId);
}
