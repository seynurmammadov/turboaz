package az.code.myauto.services;

import az.code.myauto.exceptions.FreeListingAlreadyPostedException;
import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.exceptions.TransactionInsufficientFundsException;
import az.code.myauto.models.*;
import az.code.myauto.models.dtos.ListingCreationDTO;
import az.code.myauto.models.dtos.ListingGetDTO;
import az.code.myauto.models.dtos.ListingListDTO;
import az.code.myauto.models.enums.*;
import az.code.myauto.repositories.ListingRepo;
import az.code.myauto.services.interfaces.ListingService;
import az.code.myauto.services.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public ListingServiceImpl(ListingRepo listingRepo, TransactionService transactionService) {
        this.listingRepo = listingRepo;
        this.transactionService = transactionService;
    }

    @Override
    public ListingGetDTO create(ListingCreationDTO listing, UserData user) throws FreeListingAlreadyPostedException {
        if(listingRepo.getDefaultInMonth(user.getUsername(),LocalDateTime.now().minusMonths(1),ListingType.DEFAULT)>1){
            throw new FreeListingAlreadyPostedException();
        }
        return new ListingGetDTO(listingRepo.save(new Listing(listing, user)));
    }

    @Override
    public ListingGetDTO update(long id, ListingCreationDTO listing, UserData user) throws ListingNotFoundException {
        Listing dbListing = listingCheck(id, user);

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
        dbListing.getThumbnails().get(0).setUrl(listing.getThumbnailUrl());
        dbListing.getAuto().getEquipments().clear();
        dbListing.getAuto().addEquipments(listing.getCarSpecIds());
        return new ListingGetDTO(listingRepo.save(dbListing));
    }

    @Override
    public void delete(long id, UserData user) throws ListingNotFoundException {
        listingCheck(id, user);
        listingRepo.deleteById(id);
    }

    @Override
    public ListingGetDTO makeVip(long id, UserData user) throws ListingNotFoundException, TransactionIncorrectAmountException, TransactionInsufficientFundsException {
        Listing dbListing = listingCheck(id, user);
        transactionService.decreaseBalance(ListingType.VIP.getAmount(), user, dbListing.getId());
        dbListing.setType(ListingType.VIP);
        dbListing.setUpdatedAt(LocalDateTime.now());
        return new ListingGetDTO(listingRepo.save(dbListing));
    }

    @Override
    public ListingGetDTO makePaid(long id, UserData user) {
        return null;
    }

    @Override
    public ListingGetDTO getById(long id) throws ListingNotFoundException {
        Optional<Listing> listing = listingRepo.findById(id);
        if (listing.isPresent()) {
            return new ListingGetDTO(listing.get());
        }
        throw new ListingNotFoundException();
    }

    @Override
    public List<ListingListDTO> getListings(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = preparePage(pageNo, pageSize, sortBy);
        Page<Listing> pages = listingRepo.findAllActive(pageable);
        return getResult(pages.map(ListingListDTO::new));
    }

    @Override
    public List<ListingListDTO> getVIPListings(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = preparePage(pageNo, pageSize, sortBy);
        Page<Listing> pages = listingRepo.findAllActiveVIP(pageable,ListingType.VIP);
        return getResult(pages.map(ListingListDTO::new));
    }


    @Override
    public List<ListingListDTO> getUserListings(Integer pageNo, Integer pageSize, String sortBy, UserData userData) {
        Pageable pageable = preparePage(pageNo, pageSize, sortBy);
        Page<Listing> pages = listingRepo.findAllUser(pageable, userData.getUsername());
        return getResult(pages.map(ListingListDTO::new));
    }

    @Override
    public ListingGetDTO getUserListingById(long id, UserData userData) throws ListingNotFoundException {
        Listing listing = listingCheck(id, userData);
        return new ListingGetDTO(listing);
    }

    public Listing listingCheck(long id, UserData user) throws ListingNotFoundException {
        Optional<Listing> listing = listingRepo.getUserListingById(id, user.getUsername());
        if (listing.isPresent()) {
            return listing.get();
        }
        throw new ListingNotFoundException();
    }
}
