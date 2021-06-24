package az.code.myauto.controllers;

import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.models.dtos.ListingGetDTO;
import az.code.myauto.models.dtos.ListingListDTO;
import az.code.myauto.services.interfaces.ListingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/listings")
public class ListingController {
    final
    ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    Logger logger = LoggerFactory.getLogger(ListingController.class);

    @ExceptionHandler(ListingNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ListingNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingGetDTO> getListingById(@PathVariable long id) throws ListingNotFoundException {
        logger.info("Getting listing (by id) by unregistered user");
        return new ResponseEntity<>(listingService.getListingById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<ListingListDTO>> getListings(@RequestParam(required = false, defaultValue = "0")
                                                            Integer pageNo,
                                                            @RequestParam(required = false, defaultValue = "10")
                                                            Integer itemsCount,
                                                            @RequestParam(required = false, defaultValue = "updatedAt")
                                                            String sortBy) {
        logger.info("Getting listings by unregistered user");
        List<ListingListDTO> listings = listingService.getListings(PageRequest.of(pageNo, itemsCount, Sort.by(sortBy)));
        return new ResponseEntity<>(listings, HttpStatus.OK);
    }

    @GetMapping("/vip")
    public ResponseEntity<List<ListingListDTO>> getVIPListings(@RequestParam(required = false, defaultValue = "0")
                                                               Integer pageNo,
                                                               @RequestParam(required = false, defaultValue = "10")
                                                               Integer itemsCount,
                                                               @RequestParam(required = false, defaultValue = "updatedAt")
                                                               String sortBy) {
        logger.info("Getting VIP listings by unregistered user");
        List<ListingListDTO> vipList =listingService.getVIPListings(PageRequest.of(pageNo, itemsCount, Sort.by(sortBy)));
        return new ResponseEntity<>(vipList, HttpStatus.OK);
    }
}
