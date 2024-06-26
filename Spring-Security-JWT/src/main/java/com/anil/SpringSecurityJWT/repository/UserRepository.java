package com.anil.SpringSecurityJWT.repository;

import com.anil.SpringSecurityJWT.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
        @Query(value = "SELECT * FROM user WHERE username=?1", nativeQuery = true)
        Optional<User>findByUsername(String username);
//    User findByUsername(String username);
}
