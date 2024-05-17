package org.example.bizarreadventure.repository;

import org.example.bizarreadventure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    boolean existsByLogin(String login);
    User findByLogin(String login);
    Optional<User> findById(int id);
}