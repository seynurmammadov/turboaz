package az.code.myauto.controllers;

import az.code.myauto.models.dtos.*;
import az.code.myauto.models.enums.BodyType;
import az.code.myauto.models.enums.FuelType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class SearchController {

    Logger logger = LoggerFactory.getLogger(SearchController.class);

    @GetMapping("/listings/dictionaries/makes/{id}/models")
    public ResponseEntity<List<ModelDTO>> getModelsByMakeId(@PathVariable long id) {
        logger.info("Getting all models (by make id) by unregistered user");
        //TODO after updating SearchService, 'null' must be changed.
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/listings/dictionaries/makes")
    public ResponseEntity<List<MakeDTO>> getAllMakes() {
        logger.info("Getting all makes by unregistered user");
        //TODO after updating SearchService, 'null' must be changed.
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/listings/dictionaries/fuelTypes")
    public ResponseEntity<List<FuelType>> getAllFuelTypes() {
        logger.info("Getting all fuel types by unregistered user");
        //TODO after updating SearchService, 'null' must be changed.
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/listings/dictionaries/bodyTypes")
    public ResponseEntity<List<BodyType>> getAllBodyTypes() {
        logger.info("Getting all body types by unregistered user");
        //TODO after updating SearchService, 'null' must be changed.
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/listings/dictionaries/locations")
    public ResponseEntity<List<CityDTO>> getAllCities() {
        logger.info("Getting all cities by unregistered user");
        //TODO after updating SearchService, 'null' must be changed.
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/car/search")
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
                                                       @RequestParam(required = false) String bodyType) {
        logger.info("Getting all listings (detailed search) by unregistered user");
        //TODO after updating SearchService, 'null' must be changed.
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
