package az.code.myauto.services.interfaces;

import az.code.myauto.models.City;
import az.code.myauto.models.dtos.CityDTO;
import az.code.myauto.models.dtos.ListingListDTO;
import az.code.myauto.models.dtos.MakeDTO;
import az.code.myauto.models.dtos.ModelDTO;
import az.code.myauto.models.enums.BodyType;
import az.code.myauto.models.enums.FuelType;

import java.util.List;

public interface SearchService {

    List<String> getAllModelsByMake(long makeId);

    List<String> getAllMakes();

    List<String> getAllFuelTypes();

    List<String> getAllBodyTypes();

    List<String> getAllCities();

    //TODO writing search parameters in better way
//    List<ListingListDTO> search();


}
