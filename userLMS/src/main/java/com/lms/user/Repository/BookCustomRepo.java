package com.lms.user.repository;

import com.lms.user.entity.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BookCustomRepo {
    @PersistenceContext
    private EntityManager entityManager;

    public Book getById(int id) {

        Query query = entityManager.createQuery("SELECT b FROM Book b where b.id = :id");
        Book book = (Book) query.setParameter("id",id).getSingleResult();
        return book;
    }

    @Transactional
    public void save(Book book) {

        entityManager.persist(book);
    }

    @Transactional
    public void update(Book book) {

        entityManager.merge(book);
    }

    @Transactional
    public void delete(int id) {

        Book book = getById(id);
        entityManager.remove(book);
    }

    public List<Book> getByNameOrAuthorName(String name) {

        Query query = entityManager.createQuery("SELECT b FROM Book b where b.bookName = :name or b.authorName = :name");
        List<Book> li = query.setParameter("name",name).getResultList();
        return li;
    }
}
