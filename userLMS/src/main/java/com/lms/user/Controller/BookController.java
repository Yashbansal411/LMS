package com.lms.user.controller;

import com.lms.user.entity.Book;
import com.lms.user.exception.BookAlreadyPresent;
import com.lms.user.exception.BookNotFound;
import com.lms.user.service.BookServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/book")
public class BookController {

    @Autowired
    BookServiceImpl service;
    static Logger logger = LoggerFactory.getLogger(BookController.class);

    @GetMapping("/getBook/{id}")
    public Book getBook(@PathVariable int id) throws BookNotFound {

        logger.info("/getBook called with id "+id);
        Book book = service.getBook(id);
        logger.info("book called successfully for id"+ id);
        return book;
    }

    @GetMapping("/{name}")
    public List<Book> getBookByName(@PathVariable String name) {

        logger.info("search functionality successfully called for "+ name);
        List<Book> obj = service.getAllBooksWithAuthorNameOrName(name);
        logger.info("result got successfully for search functionality "+name);
        return obj;
    }

    // post mapping
    @PostMapping("/add")
    public Book addBook( @Valid @RequestBody Book newBook) throws BookAlreadyPresent {

        logger.info("/add called for book "+ newBook.getBookName());
        Book book = service.addBook(newBook);
        logger.info("new book added successfully "+newBook.getBookName());
        return book;
    }
    // PUT
    @PutMapping("/update")
    public Book updateBook(@Valid @RequestBody Book update) throws BookNotFound {

        logger.info("update called for "+ update.getBookName());
        Book book = service.updateBook(update);
        logger.info("update for book "+ book.getBookName()+" run successfully");
        return book;
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) throws BookNotFound {

        logger.info("Delete functionality called for "+ id);
        service.deleteBook(id);
        logger.info("Deleted successfully");
        return "deleted successfully";
    }

    @PostMapping("/addCustom")
    public String addBookCustom( @Valid @RequestBody Book newBook) {

        logger.info("/add called for book "+ newBook.getBookName());
        service.bookAddedUsingCustom(newBook);
        logger.info("new book added successfully "+newBook.getBookName());
        return "book added successfully";
    }

    @PutMapping("/updateCustom")
    public String updateBookCustom(@Valid @RequestBody Book update) throws BookNotFound {

        logger.info("update called for "+ update.getBookName());
        service.updateBookCustom(update);
        logger.info("update for book "+ update.getBookName()+" run successfully");
        return "update successfully using custom";
    }

    @DeleteMapping("/deleteCustom/{id}")
    public String deleteBookCustom(@PathVariable int id) {

        logger.info("Delete functionality called for "+ id);
        service.deleteBookCustom(id);
        logger.info("Deleted successfully");
        return "deleted successfully Custom";
    }

    @GetMapping("/getBookCustom/{id}")
    public Book getBookCustom(@PathVariable int id) {

        logger.info("/getBook called with id "+id);
        Book book = service.getBookFromCustomRepo(id);
        logger.info("book called successfully for id"+ id);
        return book;
    }
}
