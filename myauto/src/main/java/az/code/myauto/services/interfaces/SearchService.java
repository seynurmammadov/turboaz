package az.code.myauto.services.interfaces;

import az.code.myauto.models.Listing;
import az.code.myauto.models.dtos.ListingListDTO;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface SearchService {

    List<String> getAllModelsByMake(long makeId);

    List<String> getAllMakes();

    List<String> getAllFuelTypes();

    List<String> getAllBodyTypes();

    List<String> getAllCities();

    List<ListingListDTO> search(Specification<Listing> spec, Integer count, Integer page);


}
