package az.code.myauto.services;

import az.code.myauto.models.dtos.CityDTO;
import az.code.myauto.models.dtos.MakeDTO;
import az.code.myauto.models.dtos.ModelDTO;
import az.code.myauto.models.enums.BodyType;
import az.code.myauto.models.enums.FuelType;
import az.code.myauto.services.interfaces.SearchService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<FuelType> getAllFuelTypes() {
        return null;
    }

    @Override
    public List<BodyType> getAllBodyTypes() {
        return null;
    }

    @Override
    public List<CityDTO> getAllCities() {
        return null;
    }
}
