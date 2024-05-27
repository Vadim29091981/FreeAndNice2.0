package com.example.freeandnice.service;
import com.example.freeandnice.exceptions.ResourceNotFoundException;
import com.example.freeandnice.model.Auction;
import com.example.freeandnice.model.Bid;
import com.example.freeandnice.model.User;
import com.example.freeandnice.repository.AuctionRepository;
import com.example.freeandnice.repository.BidRepository;
import com.example.freeandnice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    public Optional<Bid> getBidById(int id) {
        return bidRepository.findById(id);
    }

    public Bid createBid(Bid bid, int userId, int auctionId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found for this id :: " + auctionId));
        bid.setUser(user);
        bid.setAuction(auction);
        return bidRepository.save(bid);
    }
    public Bid updateBid(int id, Bid bidDetails) {
        Optional<Bid> bidOptional = bidRepository.findById(id);
        if (bidOptional.isPresent()) {
            Bid bid = bidOptional.get();
            bid.setAmount(bidDetails.getAmount());
            bid.setUser(bidDetails.getUser());
            bid.setAuction(bidDetails.getAuction());
            // Сохраняем обновленную ставку
            return bidRepository.save(bid);
        } else {
            // Обработка случая, когда ставка не найдена
            return null;
        }
    }

    public void deleteBid(int id) {
        bidRepository.deleteById(id);
    }
}