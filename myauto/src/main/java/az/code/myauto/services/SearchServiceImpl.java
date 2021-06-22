package az.code.myauto.services;

import az.code.myauto.models.Listing;
import az.code.myauto.models.Model;
import az.code.myauto.models.dtos.CityDTO;
import az.code.myauto.models.dtos.ListingListDTO;
import az.code.myauto.models.dtos.MakeDTO;
import az.code.myauto.models.enums.BodyType;
import az.code.myauto.models.enums.FuelType;
import az.code.myauto.models.mappers.MapperModel;
import az.code.myauto.repositories.ListingRepo;
import az.code.myauto.repositories.CityRepo;
import az.code.myauto.repositories.MakeRepo;
import az.code.myauto.repositories.ModelRepo;
import az.code.myauto.services.interfaces.SearchService;
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
final
MapperModel mapper;
    public SearchServiceImpl(CityRepo cityRepo, MakeRepo makeRepo, ModelRepo modelRepo, ListingRepo listingRepo, MapperModel mapper) {
        this.cityRepo = cityRepo;
        this.makeRepo = makeRepo;
        this.modelRepo = modelRepo;
        this.listingRepo = listingRepo;
        this.mapper = mapper;
    }

    @Override
    public List<String> getAllModelsByMake(long makeId) {
        return modelRepo.getAllByMake_Id(makeId).stream().map(Model::getName).collect(Collectors.toList());
    }

    @Override
    public List<MakeDTO> getAllMakes() {
       return makeRepo.findAll().stream().map(m->mapper.entityToDTO(m,MakeDTO.class)).collect(Collectors.toList());
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
    public List<CityDTO> getAllCities() {
        return cityRepo.findAll().stream().map(m->mapper.entityToDTO(m, CityDTO.class)).collect(Collectors.toList());
    }
    @Override
    public List<ListingListDTO> search(Specification<Listing> spec, Integer count, Integer page) {
        Pageable paging = preparePage(page, count);
        Page<Listing> pageResult = listingRepo.findAll(spec, paging);
        return getResult(pageResult).stream()
                .map(r->mapper.entityToDTO(r,ListingListDTO.class))
                .collect(Collectors.toList());
    }
}
