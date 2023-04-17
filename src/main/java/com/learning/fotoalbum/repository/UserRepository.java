package com.learning.fotoalbum.repository;

import com.learning.fotoalbum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Integer>{
    public Optional<User> findByEmail(String email);
}
