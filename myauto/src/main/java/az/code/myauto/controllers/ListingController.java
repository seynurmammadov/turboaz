package az.code.myauto.controllers;

import az.code.myauto.models.dtos.ListingGetDTO;
import az.code.myauto.services.interfaces.ListingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @GetMapping("/{id}")
    public ResponseEntity<ListingGetDTO> getListingById(@PathVariable long id) {
        logger.info("Getting listing (by id) by unregistered user");
        return new ResponseEntity<>(listingService.getById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getListings() {
        logger.info("Getting listings by unregistered user");
        return new ResponseEntity<>(listingService.getListings(), HttpStatus.OK);
    }

    @GetMapping("/user/{slug}")
    public ResponseEntity<?> getListings(@PathVariable String username) {
        logger.info("Getting listings (by username) by unregistered user");
        return new ResponseEntity<>(listingService.getUserListingsByUsername(), HttpStatus.OK);
    }

    @GetMapping("/{id}/images/{imageId}")
    public ResponseEntity<ListingGetDTO> getListingImageById(@PathVariable long id,
                                                             @PathVariable long imageId) {
        logger.info("Getting listing image by unregistered user");
        //TODO after updating ListingService, change null
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
