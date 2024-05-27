package com.example.freeandnice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.freeandnice.exceptions.ResourceNotFoundException;
import com.example.freeandnice.model.Auction;
import com.example.freeandnice.model.Item;
import com.example.freeandnice.repository.AuctionRepository;
import com.example.freeandnice.repository.ItemRepository;
import com.example.freeandnice.service.AuctionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuctionTest {

    @Mock
    private AuctionRepository auctionRepository;

    @InjectMocks
    private AuctionService auctionService;

    @Mock
    private ItemRepository itemRepository;


    @Test
    public void testGetAllAuctions() {
        Auction auction1 = new Auction();
        Auction auction2 = new Auction();
        List<Auction> auctions = Arrays.asList(auction1, auction2);

        when(auctionRepository.findAll()).thenReturn(auctions);

        List<Auction> result = auctionService.getAllAuctions();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetAuctionById() {
        Auction auction = new Auction();
        auction.setId(1);

        when(auctionRepository.findById(1)).thenReturn(Optional.of(auction));

        Optional<Auction> result = auctionService.getAuctionById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    public void testCreateAuction() throws ResourceNotFoundException {
        Auction auction = new Auction();
        auction.setId(1);
        Item item = new Item();
        item.setId(1);

        when(itemRepository.findById(1)).thenReturn(java.util.Optional.of(item));
        when(auctionRepository.save(auction)).thenReturn(auction);

        Auction result = auctionService.createAuction(auction, 1);

        assertEquals(1, result.getId());
        assertEquals(1, result.getItem().getId());
    }
}