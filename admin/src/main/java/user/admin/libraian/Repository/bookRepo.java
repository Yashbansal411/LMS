package user.admin.libraian.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import user.admin.libraian.Entity.book;

import java.util.List;

@Repository
public interface bookRepo extends JpaRepository<book, Integer> {

    @Query("SELECT c FROM book c WHERE c.bookName = ?1 OR c.authorName = ?1")
    List<book> findAllCustom(String name);
}
