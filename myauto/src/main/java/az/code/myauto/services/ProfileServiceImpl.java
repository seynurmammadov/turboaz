package az.code.myauto.services;

import az.code.myauto.exceptions.FreeListingAlreadyPostedException;
import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.exceptions.TransactionInsufficientFundsException;
import az.code.myauto.models.Listing;
import az.code.myauto.models.dtos.*;
import az.code.myauto.models.enums.*;
import az.code.myauto.models.mappers.MapperModel;
import az.code.myauto.repositories.ImageRepo;
import az.code.myauto.repositories.ListingRepo;
import az.code.myauto.services.interfaces.ProfileService;
import az.code.myauto.services.interfaces.TransactionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.Destination;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static az.code.myauto.utils.BaseUtils.paginationResult;

@Service
public class ProfileServiceImpl implements ProfileService {

    final
    ListingRepo listingRepo;

    final
    TransactionService transactionService;

    final
    MapperModel mapper;

    final
    ImageRepo imageRepo;

    final
    ModelMapper modelMapper;

    public ProfileServiceImpl(ListingRepo listingRepo, TransactionService transactionService, MapperModel mapper, ImageRepo imageRepo, ModelMapper modelMapper) {
        this.listingRepo = listingRepo;
        this.transactionService = transactionService;
        this.mapper = mapper;
        this.imageRepo = imageRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public ListingGetDTO create(ListingCreationDTO listing, UserDTO user) throws FreeListingAlreadyPostedException {
        LocalDateTime minusMonths = LocalDateTime.now().minusMonths(1);

        if (listingRepo.countOfDefaultUserListings(user.getUsername(), minusMonths, ListingType.DEFAULT) == 0
                && listing.getType().equals(ListingType.DEFAULT.name())) {
            Listing newListing = listingRepo.save(new Listing(listing, user));
            return mapper.entityToDTO(newListing, ListingGetDTO.class);
        }
        if (listing.getType().equals(ListingType.STANDARD.name())) {
            Listing newListing = listingRepo.save(new Listing(listing, user));
            transactionService.decreaseBalance(ListingType.STANDARD.getAmount(), user, newListing.getId());
            return mapper.entityToDTO(newListing, ListingGetDTO.class);
        }
        throw new FreeListingAlreadyPostedException();

    }

    @Override
    public ListingGetDTO update(long id, ListingCreationDTO listing, UserDTO user) throws ListingNotFoundException {
        Listing dbListing = isListingExist(id, user);
        return mapper.entityToDTO(listingRepo.save(mapper.listingCreationDTOToListing(listing, dbListing))
                , ListingGetDTO.class);
    }

    @Override
    public void delete(long id, UserDTO user) throws ListingNotFoundException {
        isListingExist(id, user);
        listingRepo.deactivateListing(id);
    }

    @Override
    public ListingGetDTO makeVip(long id, UserDTO user) throws ListingNotFoundException, TransactionIncorrectAmountException, TransactionInsufficientFundsException {
        Listing dbListing = isListingExist(id, user);
        transactionService.decreaseBalance(ListingType.VIP.getAmount(), user, dbListing.getId());
        dbListing.setType(ListingType.VIP);
        if (dbListing.getUpdatedAt().plusMonths(1).isAfter(LocalDateTime.now())) {
            dbListing.setUpdatedAt(LocalDateTime.now().plusMonths(1));
        } else {
            dbListing.setUpdatedAt(dbListing.getUpdatedAt().plusMonths(1));
        }
        return mapper.entityToDTO(listingRepo.save(dbListing), ListingGetDTO.class);
    }

    @Override
    public ListingGetDTO makePaid(long id, UserDTO user) {
        return null;
    }

    @Override
    public ListingGetDTO setNewThumbnail(long id, UserDTO user, ImageDTO imageDTO) {
        Listing listing = isListingExist(id, user);
        listing.getImages().get(0).setName(imageDTO.getName());
        return mapper.entityToDTO(listingRepo.save(listing), ListingGetDTO.class);
    }

    @Override
    public List<ListingListDTO> getUserListings(Pageable pageable, UserDTO user) {
        Page<Listing> pages = listingRepo.findAllUserListings(pageable, user.getUsername());
        return paginationResult(pages.map(p -> mapper.entityToDTO(p, ListingListDTO.class)));
    }

    @Override
    public ListingGetDTO getUserListingById(long id, UserDTO user) throws ListingNotFoundException {
        return mapper.entityToDTO(isListingExist(id, user), ListingGetDTO.class);
    }

    @Override
    public Listing isListingExist(long id, UserDTO user) throws ListingNotFoundException {
        Optional<Listing> listing = listingRepo.getUserListingById(id, user.getUsername());
        if (listing.isPresent()) {
            return listing.get();
        }
        throw new ListingNotFoundException();
    }

}
