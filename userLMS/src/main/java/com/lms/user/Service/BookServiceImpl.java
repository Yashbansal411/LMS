package com.lms.user.service;

import com.lms.user.entity.Book;
import com.lms.user.exception.BookAlreadyPresent;
import com.lms.user.exception.BookNotFound;
import com.lms.user.repository.BookCustomRepo;
import com.lms.user.repository.BookRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BookCustomRepo bookCustomRepo;

    static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    /*
       This method intend to insert new books to database
       however do note that book id should be unique
       @Param bookAdded is the book object which we want to add in database
     */

    public Book addBook(Book bookAdded) throws BookAlreadyPresent {
        logger.info("add function called successfully for "+ bookAdded.getBookId());
        // code for checking if book already added by checking database
        Book currentBook = bookRepo.findById(bookAdded.getBookId()).orElse(null);
        // code for check if book already is null of not
        if (currentBook == null) {
            // code for saving book object to database
            return bookRepo.save(bookAdded);
        }
        // code for throw error when book already present
        throw new BookAlreadyPresent("User already present");
    }
    /*
    This method intend to update already existed book
    however do note that if book will be available
    @Param newData is updated book object
    */
    public Book updateBook(Book newData) throws BookNotFound {
        logger.info("update function called successfully "+ newData.getBookId());
        // code for checking if book present in database or not
        if (bookRepo.findById(newData.getBookId()).orElse(null)==null) {
            logger.info("Id was not present for "+newData.getBookId());
            // code to throw exception when no book present for update
            throw new BookNotFound("Book not found");
        }
        logger.info("update function worked successfully for id "+newData.getBookId());
        // code to save updated book in database
        return bookRepo.save(newData);
    }
    /*
    This method intend to return book object from the database
    however do note that if book should available in database
    @Param id is unique book id
     */
    public Book getBook(int id) throws BookNotFound {
        logger.info("get book function called for "+ id);
        // code to get book object from database
        Book currentBook = bookRepo.findById(id).orElse(null);
        if (currentBook==null) {
            logger.info("No book found for id "+id);
            // code to throw exception
            throw new BookNotFound("Book not found");
        }
        logger.info("get book successfully completed for id "+id);
        return currentBook;
    }
    /*
        This method intend to delete object from database
        However do note that object must present in database
        @Param id is unique book id
     */
    public String deleteBook(int id) throws BookNotFound {
        logger.info("delete book function called for id "+id);
        // code to get book object from database
        Book currentBook = bookRepo.findById(id).orElse(null);
        // code for checking if book present in database or not
        if (currentBook==null) {
            logger.info("no book found for id "+id + " for deletion");
            throw new BookNotFound("Book not found");
        }
        // code to delete book from database
        bookRepo.deleteById(id);
        logger.info("book deleted successfully for id "+id);
        return "deleted";
    }
    /*
        This method intend to get all the books of particular book name or author name, searched by user
        @Param name is the string search by the user
     */
    public List<Book> getAllBooksWithAuthorNameOrName(String name) {
        logger.info("get all function called successfully");
        // code to get book object from database from book name or author name
        return bookRepo.findAllCustom(name);
    }
    /*
        This method intend to add book object in database using custom repo
        @param book  is the book object
     */
    public String bookAddedUsingCustom(Book book) {
        logger.info("book added called inside custom repo");
        // code for adding book object in database using custom repository
        bookCustomRepo.save(book);
        return "book saved successfully using custom repo";
    }
    /*
        This method intend to update book object in database using custom repo
        @Param newData is the book object
     */
    public String updateBookCustom(Book newData) throws BookNotFound {
        logger.info("update function called successfully custom repo "+ newData.getBookId());
        // code to check if book object present in database using custom repo
        if (bookCustomRepo.getById(newData.getBookId())==null) {
            logger.info("Id was not present for "+newData.getBookId());
            throw new BookNotFound("Book not found");
        }
        logger.info("update function worked successfully for id "+newData.getBookId());
        // code to update book object in database using custom repo
        bookCustomRepo.update(newData);
        return "updates successfully using custom repo";
    }
    /*
      This method intend to delete book from database using custom repository
      @Param id is the unique id of the book
     */
    public String deleteBookCustom(int id) {
        logger.info("delete called for "+id);
        // code to delete object from database using custom repository
        bookCustomRepo.delete(id);
        return "deleted for id "+id +" using custom repo";
    }
    /*
        This method intend to get book from database using custom repository
        @Param id is the unique id of the book
     */
    public Book getBookFromCustomRepo(int id) {
        // code to get book object from database using custom repository
        Book book = bookCustomRepo.getById(id);
        return book;
    }
}
