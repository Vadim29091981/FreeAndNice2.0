package com.example.freeandnice.controller;

import com.example.freeandnice.exceptions.ResourceNotFoundException;
import com.example.freeandnice.model.Bid;
import com.example.freeandnice.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bids")
public class BidController {

    @Autowired
    private BidService bidService;

    @GetMapping
    public List<Bid> getAllBids() {
        return bidService.getAllBids();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bid> getBidById(@PathVariable int id) {
        Optional<Bid> bid = bidService.getBidById(id);
        if (bid.isPresent()) {
            return ResponseEntity.ok(bid.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Bid> createBid(@RequestBody Bid bid, @RequestParam int userId,
                                         @RequestParam int auctionId) throws ResourceNotFoundException {
        Bid newBid = bidService.createBid(bid, userId, auctionId);
        return ResponseEntity.ok(newBid);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bid> updateBid(@PathVariable int id, @RequestBody Bid bidDetails) {
        Bid updatedBid = bidService.updateBid(id, bidDetails);
        if (updatedBid != null) {
            return ResponseEntity.ok(updatedBid);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBid(@PathVariable int id) {
        bidService.deleteBid(id);
        return ResponseEntity.noContent().build();
    }
}