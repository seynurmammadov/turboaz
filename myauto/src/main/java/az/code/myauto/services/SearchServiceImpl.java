package az.code.myauto.services;

import az.code.myauto.models.dtos.CityDTO;
import az.code.myauto.models.dtos.MakeDTO;
import az.code.myauto.models.dtos.ModelDTO;
import az.code.myauto.models.enums.BodyType;
import az.code.myauto.models.enums.FuelType;
import az.code.myauto.services.interfaces.SearchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SearchServiceImpl implements SearchService {
    @Override
    public List<ModelDTO> getAllModelsByMake(long makeId) {
        return null;
    }

    @Override
    public List<MakeDTO> getAllMakes() {

        return null;
    }

    @Override
    public List<String> getAllFuelTypes() {
        List<String> fuelTypes = Stream.of(FuelType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return fuelTypes;
    }

    @Override
    public List<String> getAllBodyTypes() {
        List<String> bodyTypes = Stream.of(BodyType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return bodyTypes;
    }

    @Override
    public List<CityDTO> getAllCities() {
        return null;
    }
}
