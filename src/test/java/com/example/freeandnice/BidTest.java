package com.example.freeandnice;

import com.example.freeandnice.exceptions.ResourceNotFoundException;
import com.example.freeandnice.model.Auction;
import com.example.freeandnice.model.Bid;
import com.example.freeandnice.model.User;
import com.example.freeandnice.repository.AuctionRepository;
import com.example.freeandnice.repository.BidRepository;
import com.example.freeandnice.repository.UserRepository;
import com.example.freeandnice.service.BidService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BidTest {


    @Mock
    private BidRepository bidRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuctionRepository auctionRepository;

    @InjectMocks
    private BidService bidService;

    @Test
    public void testCreateBid() throws ResourceNotFoundException {
        Bid bid = new Bid();
        bid.setId(1);
        User user = new User();
        user.setId(1);
        Auction auction = new Auction();
        auction.setId(1);

        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        when(auctionRepository.findById(1)).thenReturn(java.util.Optional.of(auction));
        when(bidRepository.save(bid)).thenReturn(bid);

        Bid result = bidService.createBid(bid, 1, 1);

        assertEquals(1, result.getId());
        assertEquals(1, result.getUser().getId());
        assertEquals(1, result.getAuction().getId());
    }

    @Test
    public void testGetAllBids() {
        Bid bid1 = new Bid();
        Bid bid2 = new Bid();
        List<Bid> bids = Arrays.asList(bid1, bid2);

        when(bidRepository.findAll()).thenReturn(bids);

        List<Bid> result = bidService.getAllBids();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetBidById() {
        Bid bid = new Bid();
        bid.setId(1);

        when(bidRepository.findById(1)).thenReturn(Optional.of(bid));

        Optional<Bid> result = bidService.getBidById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }
}