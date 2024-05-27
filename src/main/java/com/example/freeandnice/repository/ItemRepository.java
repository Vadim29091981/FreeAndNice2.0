package com.example.freeandnice.repository;


import com.example.freeandnice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    // Дополнительные методы для поиска предметов (при необходимости)
}
