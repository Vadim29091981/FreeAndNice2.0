package com.example.freeandnice.controller;

import com.example.freeandnice.exceptions.ResourceNotFoundException;
import com.example.freeandnice.model.Auction;
import com.example.freeandnice.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping
    public List<Auction> getAllAuctions() {
        return auctionService.getAllAuctions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auction> getAuctionById(@PathVariable int id) {
        Optional<Auction> auction = auctionService.getAuctionById(id);
        if (auction.isPresent()) {
            return ResponseEntity.ok(auction.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Auction> createAuction(@RequestBody Auction auction, @RequestParam int itemId) throws ResourceNotFoundException {
        Auction newAuction = auctionService.createAuction(auction, itemId);
        return ResponseEntity.ok(newAuction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auction> updateAuction(@PathVariable int id, @RequestBody Auction auctionDetails) {
        Auction updatedAuction = auctionService.updateAuction(id, auctionDetails);
        if (updatedAuction != null) {
            return ResponseEntity.ok(updatedAuction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuction(@PathVariable int id) {
        auctionService.deleteAuction(id);
        return ResponseEntity.noContent().build();
    }
}