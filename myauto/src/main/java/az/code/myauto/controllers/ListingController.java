package az.code.myauto.controllers;

import az.code.myauto.models.dtos.ListingGetDTO;
import az.code.myauto.services.interfaces.ListingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/listings")
public class ListingController {

    ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingGetDTO> getListingById(@PathVariable long id) {
        //TODO after updating ListingService, change null
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/{id}/images/{imageId}")
    public ResponseEntity<ListingGetDTO> getListingImageById(@PathVariable long id,
                                                             @PathVariable long imageId) {
        //TODO after updating ListingService, change null
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
