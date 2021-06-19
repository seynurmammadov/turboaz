package az.code.myauto.controllers;

import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.ListingCreationDTO;
import az.code.myauto.models.dtos.ListingGetDTO;
import az.code.myauto.services.interfaces.ListingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {
    final
    ListingService listingService;

    public ProfileController(ListingService listingService) {
        this.listingService = listingService;
    }

    Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @PostMapping("/listings")
    public ResponseEntity<ListingGetDTO> getUser(@RequestBody ListingCreationDTO listingCreationDTO,
                                                 @RequestAttribute UserData user) {
        logger.info("Posting new listing by registered user");
        return new ResponseEntity<>(listingService.create(listingCreationDTO, user), HttpStatus.CREATED);
    }

    @PutMapping("/listings/{id}")
    public ResponseEntity<ListingGetDTO> updateUser(@PathVariable long id,
                                                    @RequestBody ListingCreationDTO listingCreationDTO,
                                                    @RequestAttribute UserData user) {
        logger.info("Updating listing by registered user");
        return new ResponseEntity<>(listingService.update(id, listingCreationDTO, user), HttpStatus.OK);
    }

    //TODO Delete must return deleted listing. Should be fixed in ListingService

    @DeleteMapping("/listings/{id}")
    public void deleteUser(@PathVariable long id,
                           @RequestAttribute UserData user) {
        logger.info("Deleting listing by registered user");
//        new ResponseEntity<>(listingService.delete(id, user), HttpStatus.OK);
        listingService.delete(id, user);
    }

    @PutMapping("/listings/{id}/makevip")
    public ResponseEntity<ListingGetDTO> makeVip(@PathVariable long id,
                                                 @RequestAttribute UserData user) {
        logger.info("Making listing vip by registered user");
        return new ResponseEntity<>(listingService.makeVip(id, user), HttpStatus.OK);
    }

    @PutMapping("/listings/{id}/makepaid")
    public ResponseEntity<ListingGetDTO> makePaid(@PathVariable long id,
                                                  @RequestAttribute UserData user) {
        logger.info("Making listing paid by registered user");
        return new ResponseEntity<>(listingService.makePaid(id, user), HttpStatus.OK);
    }

    @GetMapping("/listings/{id}")
    public ResponseEntity<ListingGetDTO> getUserListingsById(@PathVariable long id,
                                                             @RequestAttribute UserData user) {
        logger.info("Getting user listing (by id) by registered user");
        return new ResponseEntity<>(listingService.getUserListingById(id, user), HttpStatus.OK);
    }

    @GetMapping("/listings")
    public ResponseEntity<?> getUserListingsById(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                                 @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                 @RequestParam(required = false,
                                                         defaultValue = "updatedAt") String sortBy,
                                                 @RequestAttribute UserData user) {
        logger.info("Getting user listings by registered user");
        return new ResponseEntity<>(listingService.getUserListings(pageNo, pageSize, sortBy, user), HttpStatus.OK);
    }


}
