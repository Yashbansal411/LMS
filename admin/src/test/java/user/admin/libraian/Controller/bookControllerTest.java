package user.admin.libraian.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

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
import user.admin.libraian.Entity.book;
import user.admin.libraian.Service.bookService;

@ContextConfiguration(classes = {bookController.class})
@ExtendWith(SpringExtension.class)
class bookControllerTest {
    @Autowired
    private bookController bookController;

    @MockBean
    private bookService bookService;


    @Test
    void testGetBook() throws Exception {
        book book = new book();
        book.setAuthorName("JaneDoe");
        book.setBookId(123);
        book.setBookName("Book Name");
        book.setNumber_of_copies(10);
        when(bookService.getBook(anyInt())).thenReturn(book);

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
        book book = new book();
        book.setAuthorName("JaneDoe");
        book.setBookId(123);
        book.setBookName("Book Name");
        book.setNumber_of_copies(10);
        when(bookService.addBook((book) any())).thenReturn(book);

        book book1 = new book();
        book1.setAuthorName("JaneDoe");
        book1.setBookId(123);
        book1.setBookName("Book Name");
        book1.setNumber_of_copies(10);
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
        book book = new book();
        book.setAuthorName("JaneDoe");
        book.setBookId(123);
        book.setBookName("Book Name");
        book.setNumber_of_copies(10);
        when(bookService.updateBook((book) any())).thenReturn(book);

        book book1 = new book();
        book1.setAuthorName("JaneDoe");
        book1.setBookId(123);
        book1.setBookName("Book Name");
        book1.setNumber_of_copies(10);
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
        when(bookService.deleteBook(anyInt())).thenReturn("Delete Book");
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
        when(bookService.getAll((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{name}", "Name");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }



}

