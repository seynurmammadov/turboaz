package az.code.myauto.services.interfaces;

import az.code.myauto.models.Listing;
import az.code.myauto.models.dtos.CityDTO;
import az.code.myauto.models.dtos.ListingListDTO;
import az.code.myauto.models.dtos.MakeDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface SearchService {

    /**
     * This method is for getting all models by make id by unregistered user
     * @param makeId
     * @return
     */
    List<String> getAllModelsByMake(long makeId);

    /**
     * This method is for getting all makes by unregistered user
     * @return
     */
    List<MakeDTO> getAllMakes();

    /**
     * This method is for getting all fuel types by unregistered user
     * @return
     */
    List<String> getAllFuelTypes();

    /**
     * This method is for getting all body types by unregistered user
     * @return
     */
    List<String> getAllBodyTypes();

    /**
     * This method is for getting all cities by unregistered user
     * @return
     */
    List<CityDTO> getAllCities();

    /**
     * This method is for detailed search of listings by unregistered user
     * @param spec
     * @param pageable
     * @return
     */
    List<ListingListDTO> search(Specification<Listing> spec, Pageable pageable);


}
