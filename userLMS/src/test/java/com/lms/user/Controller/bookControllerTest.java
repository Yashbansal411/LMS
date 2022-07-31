package com.lms.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.user.entity.Book;
import com.lms.user.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BookController.class})
@ExtendWith(SpringExtension.class)
class bookControllerTest {
    @Autowired
    private BookController bookController;

    @MockBean
    private BookServiceImpl bookServiceImpl;

    @Test
    void testGetBook() throws Exception {
        Book book = new Book();
        book.setAuthorName("JaneDoe");
        book.setBookId(123);
        book.setBookName("Book Name");
        book.setNumberOfCopies(10);
        when(bookServiceImpl.getBook(anyInt())).thenReturn(book);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getBook/{id}", 1);
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"bookId\":123,\"bookName\":\"Book Name\",\"authorName\":\"JaneDoe\",\"number_of_copies\":10}"));
    }


    @Test
    void testAddBook() throws Exception {
        Book book = new Book();
        book.setAuthorName("JaneDoe");
        book.setBookId(123);
        book.setBookName("Book Name");
        book.setNumberOfCopies(10);
        when(bookServiceImpl.addBook((Book) any())).thenReturn(book);

        Book book1 = new Book();
        book1.setAuthorName("JaneDoe");
        book1.setBookId(123);
        book1.setBookName("Book Name");
        book1.setNumberOfCopies(10);
        String content = (new ObjectMapper()).writeValueAsString(book1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"bookId\":123,\"bookName\":\"Book Name\",\"authorName\":\"JaneDoe\",\"number_of_copies\":10}"));
    }


    @Test
    void testUpdateBook() throws Exception {
        Book book = new Book();
        book.setAuthorName("JaneDoe");
        book.setBookId(123);
        book.setBookName("Book Name");
        book.setNumberOfCopies(10);
        when(bookServiceImpl.updateBook((Book) any())).thenReturn(book);

        Book book1 = new Book();
        book1.setAuthorName("JaneDoe");
        book1.setBookId(123);
        book1.setBookName("Book Name");
        book1.setNumberOfCopies(10);
        String content = (new ObjectMapper()).writeValueAsString(book1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"bookId\":123,\"bookName\":\"Book Name\",\"authorName\":\"JaneDoe\",\"number_of_copies\":10}"));
    }


    @Test
    void testDeleteBook() throws Exception {
        when(bookServiceImpl.deleteBook(anyInt())).thenReturn("Delete Book");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/delete/{id}", 1);
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Book"));
    }




    @Test
    void testGetBookByName() throws Exception {
        when(bookServiceImpl.getAllBooksWithAuthorNameOrName((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{name}", "Name");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }



}

