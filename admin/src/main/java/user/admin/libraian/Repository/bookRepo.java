package user.admin.libraian.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.admin.libraian.Entity.book;

@Repository
public interface bookRepo extends JpaRepository<book, Integer> {
}
