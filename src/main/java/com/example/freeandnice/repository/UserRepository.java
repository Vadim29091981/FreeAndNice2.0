package com.example.freeandnice.repository;

import com.example.freeandnice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Дополнительные методы для поиска пользователей (при необходимости)
}