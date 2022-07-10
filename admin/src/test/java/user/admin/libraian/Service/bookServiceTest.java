package user.admin.libraian.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.admin.libraian.Entity.book;
import user.admin.libraian.Repository.bookRepo;

@ContextConfiguration(classes = {bookService.class})
@ExtendWith(SpringExtension.class)
class bookServiceTest {
    @MockBean
    private bookRepo bookRepo;

    @Autowired
    private bookService bookService;

    @Test
    void testAddBook(){
        book book = new book();
        book.setAuthorName("JaneDoe");
        book.setBookId(123);
        book.setBookName("Book Name");
        book.setNumber_of_copies(10);
        when(bookRepo.save((book) any())).thenReturn(book);

        book book1 = new book();
        book1.setAuthorName("JaneDoe");
        book1.setBookId(123);
        book1.setBookName("Book Name");
        book1.setNumber_of_copies(10);

        assertSame(book, bookService.addBook(book1));
        verify(bookRepo).save((book) any());
    }

    @Test
    void updateBook1(){
        book bookObj = new book();
        bookObj.setAuthorName("JaneDoe");
        bookObj.setBookId(123);
        bookObj.setBookName("Book Name");
        bookObj.setNumber_of_copies(10);
        Optional <book> ofResult = Optional.of(bookObj);
        when(bookRepo.findById((Integer) any())).thenReturn(ofResult);
        when(bookRepo.save((book) any())).thenReturn(bookObj);

        book bookObj1 = new book();
        bookObj1.setAuthorName("JaneDoe");
        bookObj1.setBookId(123);
        bookObj1.setBookName("Book Name");
        bookObj1.setNumber_of_copies(10);

        assertSame(bookObj, bookService.updateBook(bookObj1));
        verify(bookRepo).findById((Integer) any());
        verify(bookRepo).save((book) any());
    }

    @Test
    void updateTest2(){
        book bookObj = new book();
        bookObj.setAuthorName("JaneDoe");
        bookObj.setBookId(123);
        bookObj.setBookName("Book Name");
        bookObj.setNumber_of_copies(10);
        Optional <book> ofResult = Optional.empty();
        when(bookRepo.findById((Integer) any())).thenReturn(ofResult);

        book bookObj1 = new book();
        bookObj1.setAuthorName("JaneDoe");
        bookObj1.setBookId(123);
        bookObj1.setBookName("Book Name");
        bookObj1.setNumber_of_copies(10);

        assertNull(bookService.updateBook(bookObj1));
        verify(bookRepo).findById((Integer) any());
    }
    @Test
    void deleteTest(){
        book bookObj = new book();
        bookObj.setAuthorName("JaneDoe");
        bookObj.setBookId(123);
        bookObj.setBookName("Book Name");
        bookObj.setNumber_of_copies(10);
        Optional <book> ofResult = Optional.empty();
        when(bookRepo.findById((Integer) any())).thenReturn(ofResult);

        assertEquals("no book with id 123",bookService.deleteBook(123));
        verify(bookRepo).findById((Integer) any());
    }
    @Test
    void deleteTest2(){
        book bookObj = new book();
        bookObj.setAuthorName("JaneDoe");
        bookObj.setBookId(123);
        bookObj.setBookName("Book Name");
        bookObj.setNumber_of_copies(10);
        Optional <book> ofResult = Optional.of(bookObj);
        doNothing().when(bookRepo).deleteById((Integer) any());
        when(bookRepo.findById((Integer) any())).thenReturn(ofResult);

        assertEquals("deleted", bookService.deleteBook(123));
    }
    @Test
    void testGetAll() {
        ArrayList<book> bookList = new ArrayList<>();
        when(bookRepo.findAllCustom((String) any())).thenReturn(bookList);
        List<book> actualAll = bookService.getAll("Name");
        assertSame(bookList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(bookRepo).findAllCustom((String) any());

    }

    @Test
    void testIssueBook() {
        book book = new book();
        book.setAuthorName("JaneDoe");
        book.setBookId(123);
        book.setBookName("Book Name");
        book.setNumber_of_copies(10);
        assertEquals("book issued successfully", bookService.issueBook(book));
        assertEquals(9, book.getNumber_of_copies());
    }

    @Test
    void testIssueBook2() {
        book book = mock(book.class);
        when(book.getNumber_of_copies()).thenReturn(10);
        doNothing().when(book).setAuthorName((String) any());
        doNothing().when(book).setBookId(anyInt());
        doNothing().when(book).setBookName((String) any());
        doNothing().when(book).setNumber_of_copies(anyInt());
        book.setAuthorName("JaneDoe");
        book.setBookId(123);
        book.setBookName("Book Name");
        book.setNumber_of_copies(10);
        assertEquals("book issued successfully", bookService.issueBook(book));
        verify(book).getNumber_of_copies();
        verify(book).setAuthorName((String) any());
        verify(book).setBookId(anyInt());
        verify(book).setBookName((String) any());
        verify(book, atLeast(1)).setNumber_of_copies(anyInt());
    }

    @Test
    void testIssueBook3() {
        book book = mock(book.class);
        when(book.getNumber_of_copies()).thenReturn(0);
        doNothing().when(book).setAuthorName((String) any());
        doNothing().when(book).setBookId(anyInt());
        doNothing().when(book).setBookName((String) any());
        doNothing().when(book).setNumber_of_copies(anyInt());
        book.setAuthorName("JaneDoe");
        book.setBookId(123);
        book.setBookName("Book Name");
        book.setNumber_of_copies(10);
        assertEquals("no copies left", bookService.issueBook(book));
        verify(book).getNumber_of_copies();
        verify(book).setAuthorName((String) any());
        verify(book).setBookId(anyInt());
        verify(book).setBookName((String) any());
        verify(book).setNumber_of_copies(anyInt());
    }

    @Test
    void testReturnBook() {
        book book = new book();
        book.setAuthorName("JaneDoe");
        book.setBookId(123);
        book.setBookName("Book Name");
        book.setNumber_of_copies(10);
        assertEquals("book returned successfully", bookService.returnBook(book));
        assertEquals(11, book.getNumber_of_copies());
    }

    @Test
    void testReturnBook2() {
        book book = mock(book.class);
        when(book.getNumber_of_copies()).thenReturn(10);
        doNothing().when(book).setAuthorName((String) any());
        doNothing().when(book).setBookId(anyInt());
        doNothing().when(book).setBookName((String) any());
        doNothing().when(book).setNumber_of_copies(anyInt());
        book.setAuthorName("JaneDoe");
        book.setBookId(123);
        book.setBookName("Book Name");
        book.setNumber_of_copies(10);
        assertEquals("book returned successfully", bookService.returnBook(book));
        verify(book).getNumber_of_copies();
        verify(book).setAuthorName((String) any());
        verify(book).setBookId(anyInt());
        verify(book).setBookName((String) any());
        verify(book, atLeast(1)).setNumber_of_copies(anyInt());
    }
}

