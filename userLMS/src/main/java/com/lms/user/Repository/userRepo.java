package com.lms.user.Repository;

import com.lms.user.Entity.user_table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends JpaRepository<user_table, Integer> {
}
