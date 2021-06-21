package az.code.myauto.services;

import az.code.myauto.models.City;
import az.code.myauto.models.Listing;
import az.code.myauto.models.Make;
import az.code.myauto.models.Model;
import az.code.myauto.models.dtos.ListingGetDTO;
import az.code.myauto.models.dtos.ListingListDTO;
import az.code.myauto.models.enums.BodyType;
import az.code.myauto.models.enums.FuelType;
import az.code.myauto.repositories.ListingRepo;
import az.code.myauto.repositories.search_repos.CityRepo;
import az.code.myauto.repositories.search_repos.MakeRepo;
import az.code.myauto.repositories.search_repos.ModelRepo;
import az.code.myauto.services.interfaces.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import static az.code.myauto.utils.PaginationUtil.*;

@Service
public class SearchServiceImpl implements SearchService {
    final
    CityRepo cityRepo;

    final
    MakeRepo makeRepo;

    final
    ModelRepo modelRepo;
    final
    ListingRepo listingRepo;

    public SearchServiceImpl(CityRepo cityRepo, MakeRepo makeRepo, ModelRepo modelRepo, ListingRepo listingRepo) {
        this.cityRepo = cityRepo;
        this.makeRepo = makeRepo;
        this.modelRepo = modelRepo;
        this.listingRepo = listingRepo;
    }

    @Override
    public List<String> getAllModelsByMake(long makeId) {
        return modelRepo.getAllByMake_Id(makeId).stream().map(Model::getName).collect(Collectors.toList());
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
    @Override
    public List<ListingListDTO> search(Specification<Listing> spec, Integer count, Integer page) {
        Pageable paging = preparePage(page, count);
        Page<Listing> pageResult = listingRepo.findAll(spec, paging);
        return getResult(pageResult).stream()
                .map(ListingListDTO::new)
                .collect(Collectors.toList());
    }
}
