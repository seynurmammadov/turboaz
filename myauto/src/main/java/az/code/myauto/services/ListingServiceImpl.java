package az.code.myauto.services;

import az.code.myauto.exceptions.FreeListingAlreadyPostedException;
import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.exceptions.TransactionInsufficientFundsException;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Transactional
    public ListingGetDTO create(ListingCreationDTO listing, UserDTO user) throws FreeListingAlreadyPostedException {
        LocalDateTime minusMonths = LocalDateTime.now().minusMonths(1);


        if (listingRepo.countOfDefaultUserListings(user.getUsername(), minusMonths, ListingType.DEFAULT) != 1
                && listing.getType().equals(ListingType.DEFAULT)) {
            Listing newListing = listingRepo.save(new Listing(listing, user));
            return new ListingGetDTO(newListing);
        }
        if (listing.getType().equals(ListingType.STANDARD)) {
            Listing newListing = listingRepo.save(new Listing(listing, user));
            transactionService.decreaseBalance(ListingType.STANDARD.getAmount(), user, newListing.getId());
            return new ListingGetDTO(newListing);
        }
        throw new FreeListingAlreadyPostedException();

    }

    @Override
    public ListingGetDTO update(long id, ListingCreationDTO listing, UserDTO user) throws ListingNotFoundException {
        Listing dbListing = isListingExist(id, user);

        dbListing.getAuto().setMake(Make.builder().id(listing.getMakeId()).build());
        dbListing.getAuto().setModel(Model.builder().id(listing.getModelId()).build());
        dbListing.getAuto().setYear(listing.getYear());
        dbListing.getAuto().setPrice(listing.getPrice());
        dbListing.getAuto().setMileage(listing.getMileage());
        dbListing.getAuto().setFueltype(FuelType.valueOf(listing.getFuelType()));
        dbListing.getAuto().setBodyType(BodyType.valueOf(listing.getBodyType()));
        dbListing.getAuto().setColor(Color.valueOf(listing.getColor()));
        dbListing.setCity(City.builder().id(listing.getCityId()).build());
        dbListing.getAuto().setGearBox(GearBox.valueOf(listing.getGearBox()));
        dbListing.setAuto_pay(listing.isAuto_pay());
        dbListing.setCreditOption(listing.getCreditOption());
        dbListing.setBarterOption(listing.getBarterOption());
        dbListing.setLeaseOption(listing.getLeaseOption());
        dbListing.setCashOption(listing.getCashOption());
        dbListing.setDescription(listing.getDescription());
        dbListing.setType(ListingType.valueOf(listing.getType()));
        dbListing.getImages().get(0).setName(listing.getThumbnailUrl());
        dbListing.getAuto().getEquipments().clear();
        dbListing.getAuto().addEquipments(listing.getCarSpecIds());
//        return new ListingGetDTO(listingRepo.save(dbListing));
        return null;
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
    public ListingGetDTO getListingById(long id) throws ListingNotFoundException {
        Optional<Listing> listing = listingRepo.findById(id);
        if (listing.isPresent()) {
            return mapper.entityToDTO(listing.get(), ListingGetDTO.class);
        }
        throw new ListingNotFoundException();
    }

    @Override
    public List<ListingListDTO> getListings(Integer pageNo, Integer itemsCount, String sortBy) {
        Pageable pageable = preparePage(pageNo, itemsCount, sortBy);
        Page<Listing> pages = listingRepo.findAllActiveListings(pageable);
        return getResult(pages.map(p->mapper.entityToDTO(p, ListingListDTO.class)));
    }


    @Override
    public List<ListingListDTO> getVIPListings(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = preparePage(pageNo, pageSize, sortBy);
        Page<Listing> pages = listingRepo.findAllActiveVIPListings(pageable, ListingType.VIP);
        return getResult(pages.map(p->mapper.entityToDTO(p, ListingListDTO.class)));
    }

    @Override
    public List<ListingListDTO> getUserListings(Integer pageNo, Integer pageSize, String sortBy, UserDTO user) {
        Pageable pageable = preparePage(pageNo, pageSize, sortBy);
        Page<Listing> pages = listingRepo.findAllUserListings(pageable, user.getUsername());
        return getResult(pages.map(p->mapper.entityToDTO(p, ListingListDTO.class)));
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
