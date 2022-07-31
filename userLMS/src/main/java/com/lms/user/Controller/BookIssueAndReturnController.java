package com.lms.user.controller;

import com.lms.user.entity.BookIssueAndReturn;
import com.lms.user.exception.BookAlreadyIssued;
import com.lms.user.exception.FineGreaterThanZero;
import com.lms.user.exception.NoCopiesLeftForIssue;
import com.lms.user.service.BookIssueAndReturnServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BookIssueAndReturnController {

    @Autowired
    BookIssueAndReturnServiceImpl bookIssueAndReturnServiceImpl;
    static Logger logger = LoggerFactory.getLogger(BookIssueAndReturnController.class);

    @PostMapping("/issueBook")
    public String issueBook(@Valid @RequestBody BookIssueAndReturn act) throws BookAlreadyIssued, FineGreaterThanZero, NoCopiesLeftForIssue {

        logger.info("issue book called successfully for "+act.getBookId() + " "+ act.getRegistrationNumber());
        int userid = act.getRegistrationNumber();
        int bookId = act.getBookId();
        String out = bookIssueAndReturnServiceImpl.issueBook(userid, bookId);
        logger.info(out+ " "+bookId + " "+userid);
        return out;
    }

    @PostMapping("/returnBook")
    public String returnBook(@Valid @RequestBody BookIssueAndReturn act) {
        logger.info("book returned called successfully with "+ act.getBookId()+" "+act.getRegistrationNumber());
        int userId = act.getRegistrationNumber();
        int bookId = act.getBookId();
        String out = bookIssueAndReturnServiceImpl.returnBook(userId, bookId);
        logger.info("book returned Successfully");
        return out;
    }
}
