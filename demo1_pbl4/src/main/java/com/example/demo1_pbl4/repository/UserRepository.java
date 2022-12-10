package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users  WHERE CONCAT(first_name,' ',last_name) LIKE %?1%",nativeQuery = true)
    public List<User> search(String keyword);

    @Query(value = "SELECT * FROM users WHERE username=?1", nativeQuery = true)
    User findUserByUsername(String username);
}
