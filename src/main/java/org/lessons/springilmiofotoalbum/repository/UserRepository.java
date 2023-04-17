package org.lessons.springilmiofotoalbum.repository;


import java.util.Optional;

import org.lessons.springilmiofotoalbum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByEmail(String email);
}