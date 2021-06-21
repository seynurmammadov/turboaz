package az.code.myauto.services;

import az.code.myauto.models.City;
import az.code.myauto.models.Make;
import az.code.myauto.models.dtos.ModelDTO;
import az.code.myauto.models.enums.BodyType;
import az.code.myauto.models.enums.FuelType;
import az.code.myauto.repositories.search_repos.CityRepo;
import az.code.myauto.repositories.search_repos.MakeRepo;
import az.code.myauto.services.interfaces.SearchService;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {
    final
    CityRepo cityRepo;

    final
    MakeRepo makeRepo;

    public SearchServiceImpl(CityRepo cityRepo, MakeRepo makeRepo) {
        this.cityRepo = cityRepo;
        this.makeRepo = makeRepo;
    }

    @Override
    public List<ModelDTO> getAllModelsByMake(long makeId) {
        return null;
    }

    @Override
    public List<String> getAllMakes() {
        return makeRepo.findAll().stream().map(Make::getName).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllFuelTypes() {
        return EnumSet.allOf(FuelType.class).stream().map(FuelType::getName).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBodyTypes() {
        return EnumSet.allOf(BodyType.class).stream().map(BodyType::getName).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllCities() {
        return cityRepo.findAll().stream().map(City::getName).collect(Collectors.toList());
    }
}
