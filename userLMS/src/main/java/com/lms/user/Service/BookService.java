package com.lms.user.service;

import com.lms.user.entity.Book;
import com.lms.user.exception.BookAlreadyPresent;
import com.lms.user.exception.BookNotFound;

import java.util.List;

public interface BookService {
    public Book addBook(Book bookAdded) throws BookAlreadyPresent;

    public Book updateBook(Book newData) throws BookNotFound;

    public Book getBook(int id) throws BookNotFound;

    public String deleteBook(int id) throws BookNotFound;

    public List<Book> getAllBooksWithAuthorNameOrName(String name);
}
