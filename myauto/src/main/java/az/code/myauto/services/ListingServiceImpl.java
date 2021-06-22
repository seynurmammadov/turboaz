package az.code.myauto.services;

import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.models.*;
import az.code.myauto.models.dtos.*;
import az.code.myauto.models.enums.*;
import az.code.myauto.models.mappers.MapperModel;
import az.code.myauto.repositories.ImageRepo;
import az.code.myauto.repositories.ListingRepo;
import az.code.myauto.services.interfaces.ListingService;
import az.code.myauto.services.interfaces.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static az.code.myauto.utils.BaseUtils.paginationResult;

@Service
public class ListingServiceImpl implements ListingService {
    final
    ListingRepo listingRepo;

    final
    TransactionService transactionService;

    final
    MapperModel mapper;

    final
    ImageRepo imageRepo;

    public ListingServiceImpl(ListingRepo listingRepo, TransactionService transactionService, MapperModel mapper, ImageRepo imageRepo) {
        this.listingRepo = listingRepo;
        this.transactionService = transactionService;
        this.mapper = mapper;
        this.imageRepo = imageRepo;
    }

    @Override
    public ListingGetDTO getListingById(long id) throws ListingNotFoundException {
        Optional<Listing> listing = listingRepo.findById(id);
        if (listing.isPresent()) {
            return mapper.entityToDTO(listing.get(), ListingGetDTO.class);
        }
        throw new ListingNotFoundException();
    }

    @Override
    public List<ListingListDTO> getListings(Pageable pageable) {
        Page<Listing> pages = listingRepo.findAllActiveListings(pageable);
        return paginationResult(pages.map(p -> mapper.entityToDTO(p, ListingListDTO.class)));
    }


    @Override
    public List<ListingListDTO> getVIPListings(Pageable pageable) {
        Page<Listing> pages = listingRepo.findAllActiveVIPListings(pageable, ListingType.VIP);
        return paginationResult(pages.map(p -> mapper.entityToDTO(p, ListingListDTO.class)));
    }

}
