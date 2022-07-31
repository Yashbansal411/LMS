package com.lms.user.repository;

import com.lms.user.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

    @Query("SELECT c FROM Book c WHERE c.bookName = ?1 OR c.authorName = ?1")
    List<Book> findAllCustom(String name);
}
