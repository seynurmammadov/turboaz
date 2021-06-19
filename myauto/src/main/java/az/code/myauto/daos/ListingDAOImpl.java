package az.code.myauto.daos;

import az.code.myauto.daos.interfaces.ListingDAO;
import az.code.myauto.models.Listing;
import az.code.myauto.repositories.ListingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListingDAOImpl implements ListingDAO {
    final
    ListingRepo listingRepo;

    public ListingDAOImpl(ListingRepo listingRepo) {
        this.listingRepo = listingRepo;
    }

    @Override
    public Listing create(Listing listing) {
        return listingRepo.save(listing);
    }


}
