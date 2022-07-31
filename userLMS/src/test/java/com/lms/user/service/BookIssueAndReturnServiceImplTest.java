package com.lms.user.service;

import com.lms.user.entity.BookIssueAndReturn;
import com.lms.user.entity.Book;
import com.lms.user.entity.User;
import com.lms.user.exception.BookAlreadyIssued;
import com.lms.user.exception.FineGreaterThanZero;
import com.lms.user.exception.NoCopiesLeftForIssue;
import com.lms.user.repository.BookIssueAndReturnRepo;
import com.lms.user.repository.BookRepo;
import com.lms.user.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BookIssueAndReturnServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class BookIssueAndReturnServiceImplTest {

    @MockBean
    BookIssueAndReturnRepo bookIssueAndReturnRepo;
    @MockBean
    UserRepo userRepo;
    @MockBean
    BookRepo bookRepo;


    @Autowired
    BookIssueAndReturnServiceImpl bookIssueAndReturnServiceImpl;
    //@Autowired
    //UserService userService;
    //@Autowired
    //BookService bookService;

    // test1
    @Test
    void checkBookIssuedSuccess() throws BookAlreadyIssued, FineGreaterThanZero, NoCopiesLeftForIssue {
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("first@gmail.com");
        user.setId(1);
        user.setPassword("abc");

        Book book = new Book();
        book.setBookId(1);
        book.setBookName("java");
        book.setAuthorName("abc");
        book.setNumberOfCopies(10);

        BookIssueAndReturn act = new BookIssueAndReturn();
        act.setBookId(1);
        act.setRegistrationNumber(12345);
        when(userRepo.findById( anyInt())).thenReturn(Optional.of(user));
        when(bookRepo.findById(anyInt())).thenReturn(Optional.of(book));
        when(bookIssueAndReturnRepo.findActivity(anyInt(),anyInt())).thenReturn(null);
        when(bookIssueAndReturnRepo.save((BookIssueAndReturn) any())).thenReturn(act);

        assertThat("book issued successfully", equalTo(bookIssueAndReturnServiceImpl.issueBook(1,1)));
    }

    @Test
    void testBookNotAvailable() throws BookAlreadyIssued, FineGreaterThanZero, NoCopiesLeftForIssue {
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("first@gmail.com");
        user.setId(1);
        user.setPassword("abc");

        Book book = new Book();
        book.setBookId(1);
        book.setBookName("java");
        book.setAuthorName("abc");
        book.setNumberOfCopies(0);

        BookIssueAndReturn act = new BookIssueAndReturn();
        act.setBookId(1);
        act.setRegistrationNumber(12345);
        when(userRepo.findById( anyInt())).thenReturn(Optional.of(user));
        when(bookRepo.findById(anyInt())).thenReturn(Optional.of(book));
        when(bookIssueAndReturnRepo.findActivity(anyInt(),anyInt())).thenReturn(null);
        when(bookIssueAndReturnRepo.save((BookIssueAndReturn) any())).thenReturn(act);

        assertThat("No copies left", equalTo(bookIssueAndReturnServiceImpl.issueBook(1,1)));
    }

    @Test
    void testSameBookReissue() throws BookAlreadyIssued, FineGreaterThanZero, NoCopiesLeftForIssue {
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("first@gmail.com");
        user.setId(1);
        user.setPassword("abc");

        Book book = new Book();
        book.setBookId(1);
        book.setBookName("java");
        book.setAuthorName("abc");
        book.setNumberOfCopies(10);

        BookIssueAndReturn act = new BookIssueAndReturn();
        act.setBookId(1);
        act.setRegistrationNumber(12345);
        when(userRepo.findById( anyInt())).thenReturn(Optional.of(user));
        when(bookRepo.findById(anyInt())).thenReturn(Optional.of(book));
        when(bookIssueAndReturnRepo.findActivity(anyInt(),anyInt())).thenReturn(act);
        when(bookIssueAndReturnRepo.save((BookIssueAndReturn) any())).thenReturn(act);

        assertThat("can't re-issue same book without returning", equalTo(bookIssueAndReturnServiceImpl.issueBook(1,1)));
    }

    @Test
    void testFineGreaterThanZero() throws BookAlreadyIssued, FineGreaterThanZero, NoCopiesLeftForIssue {
        User user = new User();
        user.setId(1);
        user.setFine(100);

        Book book = new Book();
        book.setBookId(1);
        book.setNumberOfCopies(10);


        when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));
        when(bookRepo.findById(anyInt())).thenReturn(Optional.of(book));

        assertThat("Please pay fine first", equalTo(bookIssueAndReturnServiceImpl.issueBook(1,1)));
    }

    @Test
    void testReturnBookSuccess(){
        BookIssueAndReturn act = new BookIssueAndReturn();
        act.setEndDate(LocalDate.now().plusDays(5));
        when(bookIssueAndReturnRepo.findActivity(anyInt(), anyInt())).thenReturn(act);
        doNothing().when(bookIssueAndReturnRepo).delete(any());
        assertThat("returned successfully", equalTo(bookIssueAndReturnServiceImpl.returnBook(1,1)));
    }
}
