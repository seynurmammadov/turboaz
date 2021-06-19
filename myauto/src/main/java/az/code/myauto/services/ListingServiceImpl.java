package az.code.myauto.services;

import az.code.myauto.models.Listing;
import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.ListingCreationDTO;
import az.code.myauto.models.dtos.ListingGetDTO;
import az.code.myauto.models.dtos.ListingListDTO;
import az.code.myauto.repositories.ListingRepo;
import az.code.myauto.services.interfaces.ListingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public ListingGetDTO delete(long id, UserData user) {
        return null;
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
        return null;
    }

    @Override
    public List<ListingListDTO> getListings(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = preparePage(pageNo,pageSize,sortBy);
        Page<Listing> pages= listingRepo.findAll(pageable);
        return getResult(pages.map(ListingListDTO::new));
    }

    @Override
    public List<ListingListDTO> getUserListingsByUsername() {
        return null;
    }

    @Override
    public List<ListingListDTO> getUserListings(UserData userData) {
        return null;
    }

    @Override
    public ListingGetDTO getUserListingById(long id, UserData userData) {
        return null;
    }



}
