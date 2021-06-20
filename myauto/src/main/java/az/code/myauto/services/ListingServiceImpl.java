package az.code.myauto.services;

import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.models.*;
import az.code.myauto.models.dtos.ListingCreationDTO;
import az.code.myauto.models.dtos.ListingGetDTO;
import az.code.myauto.models.dtos.ListingListDTO;
import az.code.myauto.models.enums.*;
import az.code.myauto.repositories.ListingRepo;
import az.code.myauto.services.interfaces.ListingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import static az.code.myauto.utils.PaginationUtil.getResult;
import static az.code.myauto.utils.PaginationUtil.preparePage;

@Service
public class ListingServiceImpl implements ListingService {
    final
    ListingRepo listingRepo;


    public ListingServiceImpl(ListingRepo listingRepo) {
        this.listingRepo = listingRepo;
    }

    @Override
    public ListingGetDTO create(ListingCreationDTO listing, UserData user) {
        return new ListingGetDTO(listingRepo.save(new Listing(listing,user)));
    }

    @Override
    public ListingGetDTO update(long id, ListingCreationDTO listing, UserData user) {
        Optional<Listing> dbListing = listingRepo.getUserListingById(id,user.getUsername());
        if(dbListing.isPresent()){
            dbListing.get().getAuto().setMake(Make.builder().id(listing.getMakeId()).build());
            dbListing.get().getAuto().setModel(Model.builder().id(listing.getModelId()).build());
            dbListing.get().getAuto().setYear(listing.getYear());
            dbListing.get().getAuto().setPrice(listing.getPrice());
            dbListing.get().getAuto().setMileage(listing.getMileage());
            dbListing.get().getAuto().setFueltype(FuelType.valueOf(listing.getFuelType()));
            dbListing.get().getAuto().setBodyType(BodyType.valueOf(listing.getBodyType()));
            dbListing.get().getAuto().setColor(Color.valueOf(listing.getColor()));
            dbListing.get().setCity(City.builder().id(listing.getCityId()).build());
            dbListing.get().getAuto().setGearBox(GearBox.valueOf(listing.getGearBox()));
            dbListing.get().setAuto_pay(listing.isAuto_pay());
            dbListing.get().setCreditOption(listing.getCreditOption());
            dbListing.get().setBarterOption(listing.getBarterOption());
            dbListing.get().setLeaseOption(listing.getLeaseOption());
            dbListing.get().setCashOption(listing.getCashOption());
            dbListing.get().setDescription(listing.getDescription());
            dbListing.get().setType(ListingType.valueOf(listing.getType()));
            dbListing.get().getThumbnails().get(0).setUrl(listing.getThumbnailUrl());
            dbListing.get().getAuto().getEquipments().clear();
            dbListing.get().getAuto().addEquipments(listing.getCarSpecIds());
            return new ListingGetDTO(listingRepo.save(dbListing.get()));
        }
        throw new ListingNotFoundException();
    }

    @Override
    public void delete(long id, UserData user)throws ListingNotFoundException {

        getUserListingById(id,user);
        listingRepo.deleteById(id);
    }

    @Override
    public ListingGetDTO makeVip(long id, UserData user) {
        return null;
    }

    @Override
    public ListingGetDTO makePaid(long id, UserData user) {
        return null;
    }

    @Override
    public ListingGetDTO getById(long id) {
        Optional<Listing> listing =listingRepo.findById(id);
        if(listing.isPresent()){
            return new ListingGetDTO(listing.get());
        }
        throw new ListingNotFoundException();
    }

    @Override
    public List<ListingListDTO> getListings(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = preparePage(pageNo,pageSize,sortBy);
        Page<Listing> pages= listingRepo.findAllActive(pageable);
        return getResult(pages.map(ListingListDTO::new));
    }


    @Override
    public List<ListingListDTO> getUserListings(Integer pageNo, Integer pageSize, String sortBy,UserData userData) {
        Pageable pageable = preparePage(pageNo,pageSize,sortBy);
        Page<Listing> pages= listingRepo.findAllUser(pageable,userData.getUsername());
        return getResult(pages.map(ListingListDTO::new));
    }

    @Override
    public ListingGetDTO getUserListingById(long id, UserData userData) throws ListingNotFoundException {
        Optional<Listing> listing = listingRepo.getUserListingById(id, userData.getUsername());
        if(listing.isPresent()){
            return new ListingGetDTO(listing.get());
        }
        throw new ListingNotFoundException();
    }
}
