package com.example.freeandnice.repository;

import com.example.freeandnice.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Integer> {
    // Дополнительные методы для поиска аукционов (при необходимости)
}