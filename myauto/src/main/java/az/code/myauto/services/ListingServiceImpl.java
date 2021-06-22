package az.code.myauto.services;

import az.code.myauto.exceptions.ListingNotFoundException;;
import az.code.myauto.models.*;
import az.code.myauto.models.dtos.*;
import az.code.myauto.models.enums.*;
import az.code.myauto.repositories.ListingRepo;
import az.code.myauto.services.interfaces.ListingService;
import az.code.myauto.services.interfaces.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static az.code.myauto.utils.PaginationUtil.getResult;
import static az.code.myauto.utils.PaginationUtil.preparePage;

@Service
public class ListingServiceImpl implements ListingService {
    final
    ListingRepo listingRepo;

    final
    TransactionService transactionService;

    public ListingServiceImpl(ListingRepo listingRepo, TransactionService transactionService) {
        this.listingRepo = listingRepo;
        this.transactionService = transactionService;
    }


    @Override
    public ListingGetDTO getListingById(long id) throws ListingNotFoundException {
        Optional<Listing> listing = listingRepo.findById(id);
        if (listing.isPresent()) {
            return new ListingGetDTO(listing.get());
        }
        throw new ListingNotFoundException();
    }

    @Override
    public List<ListingListDTO> getListings(Integer pageNo, Integer itemsCount, String sortBy) {
        Pageable pageable = preparePage(pageNo, itemsCount, sortBy);
        Page<Listing> pages = listingRepo.findAllActiveListings(pageable);
        return getResult(pages.map(ListingListDTO::new));
    }

    @Override
    public List<ListingListDTO> getVIPListings(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = preparePage(pageNo, pageSize, sortBy);
        Page<Listing> pages = listingRepo.findAllActiveVIPListings(pageable, ListingType.VIP);
        return getResult(pages.map(ListingListDTO::new));
    }

}
