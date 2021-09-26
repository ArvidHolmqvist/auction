package se.ah.auctionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ah.auctionservice.JPAEntities.AuctionItem;
import se.ah.auctionservice.Repositories.AuctionItemRepository;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AuctionItemServiceImpl implements AuctionItemService {
    AuctionItemRepository repository;

    @Autowired
    public AuctionItemServiceImpl(AuctionItemRepository repository){
        this.repository = repository;
    }


    @Override
    public void addAuctionItem(AuctionItem item) {
        repository.save(item);
    }

    @Override
    public AuctionItem getAuctionItemById(long id) {
        return repository.getById(id);
    }

    @Override
    public void deleteAuctionItemById(long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateAuctionItemById(long id, AuctionItem item) {
        repository.deleteById(id);
        repository.save(item);
    }

    @Override
    public List<AuctionItem> getAllAuctionItems() {
        return repository.findAll();
    }

    @Override
    public List<AuctionItem> getAllActiveAuctionItems() {
        return repository.findAll()
                .stream()
                .filter(AuctionItem::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuctionItem> getAllInactiveAuctionItems() {
        return repository.findAll()
                .stream()
                .filter(Predicate.not(AuctionItem::isActive))
                .collect(Collectors.toList());
    }
}
