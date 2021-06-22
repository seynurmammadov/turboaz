package az.code.myauto.controllers;

import az.code.myauto.models.dtos.*;
import az.code.myauto.services.interfaces.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static az.code.myauto.utils.SpecificationUtil.*;

@RestController
@RequestMapping("api/v1/listings/dictionaries")
public class SearchController {

    final
    SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    Logger logger = LoggerFactory.getLogger(SearchController.class);

    @GetMapping("/makes/{id}/models")
    public ResponseEntity<List<String>> getModelsByMakeId(@PathVariable long id) {
        logger.info("Getting all models (by make id) by unregistered user");

        return new ResponseEntity<>(searchService.getAllModelsByMake(id), HttpStatus.OK);
    }

    @GetMapping("/makes")
    public ResponseEntity<List<MakeDTO>> getAllMakes() {
        logger.info("Getting all makes by unregistered user");
        return new ResponseEntity<>(searchService.getAllMakes(), HttpStatus.OK);
    }

    @GetMapping("/fuelTypes")
    public ResponseEntity<List<String>> getAllFuelTypes() {
        logger.info("Getting all fuel types by unregistered user");
        return new ResponseEntity<>(searchService.getAllFuelTypes(), HttpStatus.OK);
    }

    @GetMapping("/bodyTypes")
    public ResponseEntity<List<String>> getAllBodyTypes() {
        logger.info("Getting all body types by unregistered user");

        return new ResponseEntity<>(searchService.getAllBodyTypes(), HttpStatus.OK);
    }

    @GetMapping("/locations")
    public ResponseEntity<List<CityDTO>> getAllCities() {
        logger.info("Getting all cities by unregistered user");

        return new ResponseEntity<>(searchService.getAllCities(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ListingListDTO>> search(@RequestParam(required = false) String make,
                                                       @RequestParam(required = false) String model,
                                                       @RequestParam(required = false) String location,
                                                       @RequestParam(required = false) Integer minYear,
                                                       @RequestParam(required = false) Integer maxYear,
                                                       @RequestParam(required = false) Integer minPrice,
                                                       @RequestParam(required = false) Integer maxPrice,
                                                       @RequestParam(required = false) Integer minMileage,
                                                       @RequestParam(required = false) Integer maxMileage,
                                                       @RequestParam(required = false) String fuel,
                                                       @RequestParam(required = false) Boolean hasLoan,
                                                       @RequestParam(required = false) Boolean hasBarter,
                                                       @RequestParam(required = false) Boolean hasLease,
                                                       @RequestParam(required = false) Boolean hasCash,
                                                       @RequestParam(required = false) String bodyType,
                                                       @RequestParam(required = false, defaultValue = "10") Integer itemsCount,
                                                       @RequestParam(required = false, defaultValue = "0") Integer pageNo) {
        logger.info("Getting all listings (detailed search) by unregistered user");
        return new ResponseEntity<>(searchService.search(
                sameAuto("make", make)
                        .and(sameAuto("model", model))
                        .and(sameCity(location))
                        .and(between("year", minYear, maxYear))
                        .and(between("price", minPrice, maxPrice))
                        .and(between("mileage", minMileage, maxMileage))
                        .and(sameFuelType(fuel))
                        .and(sameBodyType(bodyType))
                        .and(sameOption("creditOption", hasLoan))
                        .and(sameOption("barterOption", hasBarter))
                        .and(sameOption("leaseOption", hasLease))
                        .and(sameOption("cashOption", hasCash)),
                PageRequest.of(pageNo, itemsCount)
        ), HttpStatus.OK);
    }
}
