package az.code.myauto.services.interfaces;

import az.code.myauto.models.Listing;
import az.code.myauto.models.dtos.CityDTO;
import az.code.myauto.models.dtos.ListingListDTO;
import az.code.myauto.models.dtos.MakeDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface SearchService {

    List<String> getAllModelsByMake(long makeId);

    List<MakeDTO> getAllMakes();

    List<String> getAllFuelTypes();

    List<String> getAllBodyTypes();

    List<CityDTO> getAllCities();

    List<ListingListDTO> search(Specification<Listing> spec, Pageable pageable);


}
