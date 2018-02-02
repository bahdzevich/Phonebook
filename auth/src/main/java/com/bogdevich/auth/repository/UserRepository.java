package com.bogdevich.auth.repository;

import com.bogdevich.auth.entity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findUserByEmail(String email);
}
