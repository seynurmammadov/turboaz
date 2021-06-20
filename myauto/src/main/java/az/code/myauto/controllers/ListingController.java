package az.code.myauto.controllers;

import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.exceptions.ThumbnailNotFoundException;
import az.code.myauto.models.Listing;
import az.code.myauto.models.Thumbnail;
import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.ListingGetDTO;
import az.code.myauto.services.ThumbnailService;
import az.code.myauto.services.interfaces.ListingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/")
public class ListingController {
    final
    ListingService listingService;
    @Autowired
    ThumbnailService thumbnailService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    Logger logger = LoggerFactory.getLogger(ListingController.class);

    @GetMapping("listings/{id}")
    public ResponseEntity<ListingGetDTO> getListingById(@PathVariable long id) throws ListingNotFoundException {
        logger.info("Getting listing (by id) by unregistered user");
        return new ResponseEntity<>(listingService.getById(id), HttpStatus.OK);
    }

    @GetMapping("listings")
    public ResponseEntity<?> getListings(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                         @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                         @RequestParam(required = false, defaultValue = "updatedAt") String sortBy) {
        logger.info("Getting listings by unregistered user");
        return new ResponseEntity<>(listingService.getListings(pageNo, pageSize, sortBy), HttpStatus.OK);
    }
    @GetMapping("listings/vip")
    public ResponseEntity<?> getVIPListings(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                         @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                         @RequestParam(required = false, defaultValue = "updatedAt") String sortBy) {
        logger.info("Getting VIP listings by unregistered user");
        return new ResponseEntity<>(listingService.getVIPListings(pageNo, pageSize, sortBy), HttpStatus.OK);
    }

    @GetMapping("user/{username}/listings")
    public ResponseEntity<?> getListings( @RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                          @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                          @RequestParam(required = false, defaultValue = "updatedAt") String sortBy,
                                          @PathVariable String username) {
        logger.info("Getting listings (by username) by unregistered user");
        return new ResponseEntity<>(
                listingService.getUserListings(pageNo, pageSize, sortBy, UserData.builder().username(username).build())
                , HttpStatus.OK);
    }

    @GetMapping("listings/{id}/images/{imageId}")
    public ResponseEntity<ListingGetDTO> getListingImageById(@PathVariable long id,
                                                             @PathVariable long imageId) throws ListingNotFoundException, ThumbnailNotFoundException {
        logger.info("Getting listing image by unregistered user");
        //TODO after updating ListingService, change null

        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
