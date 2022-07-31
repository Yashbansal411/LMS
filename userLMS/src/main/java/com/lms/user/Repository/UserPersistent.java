package com.lms.user.repository;

import com.lms.user.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class UserPersistent {

    @PersistenceContext
    private EntityManager entityManager;

    public User getUser(int id) {

        Query query = entityManager.createQuery("SELECT u FROM User u where u.id = :id");
        User user = (User) query.setParameter("id",id).getSingleResult();
        return user;
    }

    @Transactional
    public void insertQuery(User user) {

        entityManager.persist(user);
        //return "insert successfully";
    }

    @Transactional
    public String deleteQuery(int id) {

        User user = getUser(id);
        entityManager.remove(user);
        return "deleted successfully using persistent context";
    }

    @Transactional
    public String updateQuery(User user) {

        entityManager.merge(user);
        return "update successfully using persistent context";
    }
}
