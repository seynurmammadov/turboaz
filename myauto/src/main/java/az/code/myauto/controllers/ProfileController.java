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
        return new ResponseEntity<>(listingService.create(listingCreationDTO,user), HttpStatus.CREATED);
    }

    @PutMapping("/listings/{id}")
    public ResponseEntity<ListingGetDTO> updateUser(@PathVariable long id,
                                                    @RequestBody ListingCreationDTO listingCreationDTO,
                                                    @RequestAttribute UserData user) {
        logger.info("Updating listing by registered user");
        return new ResponseEntity<>(listingService.update(id, listingCreationDTO, user), HttpStatus.OK);
    }

    @DeleteMapping("/listings/{id}")
    public ResponseEntity<ListingGetDTO> deleteUser(@PathVariable long id,
                                                    @RequestAttribute UserData user) {
        logger.info("Deleting listing by registered user");
        return new ResponseEntity<>(listingService.delete(id, user), HttpStatus.OK);
    }




}
