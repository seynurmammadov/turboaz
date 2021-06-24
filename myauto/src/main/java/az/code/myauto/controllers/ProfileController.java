package az.code.myauto.controllers;

import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.exceptions.TransactionInsufficientFundsException;
import az.code.myauto.models.dtos.*;

import az.code.myauto.models.enums.ListingType;
import az.code.myauto.services.interfaces.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/profile/listings")
public class ProfileController {

    final
    ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @ExceptionHandler(ListingNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ListingNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionIncorrectAmountException.class)
    public ResponseEntity<String> handleNotFound(TransactionIncorrectAmountException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionInsufficientFundsException.class)
    public ResponseEntity<String> handleNotFound(TransactionInsufficientFundsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingGetDTO> getUserListingsById(@PathVariable long id, @RequestAttribute UserDTO user)
                                                                                    throws ListingNotFoundException {
        logger.info("Getting user listing (by id) by registered user");
        return new ResponseEntity<>(profileService.getUserListingById(id, user), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<ListingListDTO>> getUserListingsById(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                                 @RequestParam(required = false, defaultValue = "10") Integer itemsCount,
                                                 @RequestParam(required = false,
                                                         defaultValue = "updatedAt") String sortBy,
                                                 @RequestAttribute UserDTO user) {
        logger.info("Getting user listings by registered user");
        List<ListingListDTO> listings=profileService.getUserListings(
                PageRequest.of(pageNo, itemsCount, Sort.by(sortBy)), user);
        return new ResponseEntity<>(listings, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("")
    public ResponseEntity<ListingGetDTO> getUser(@RequestBody ListingCreationDTO listingCreationDTO,
                                                 @RequestAttribute UserDTO user) {
        logger.info("Post: New listing by user");
        return new ResponseEntity<>(profileService.create(listingCreationDTO, user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListingGetDTO> updateUser(@PathVariable long id,
                                              @RequestBody ListingCreationDTO listingCreationDTO,
                                              @RequestAttribute UserDTO user) throws ListingNotFoundException {
        logger.info("Updating listing by registered user");
        return new ResponseEntity<>(profileService.update(id, listingCreationDTO, user), HttpStatus.OK);
    }

    @PutMapping("/{id}/setThumbnail")
    public ResponseEntity<ListingGetDTO> setThumbnail(@PathVariable long id,
                                                      @RequestBody ImageDTO imageDTO,
                                                      @RequestAttribute UserDTO user) throws ListingNotFoundException {
        logger.info("Set thumbnail for register user");
        return new ResponseEntity<>(profileService.setNewThumbnail(id, user, imageDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}/makevip")
    public ResponseEntity<ListingGetDTO> makeVip(@PathVariable long id, @RequestAttribute UserDTO user)
                                                                        throws TransactionIncorrectAmountException,
                                                                        ListingNotFoundException,
                                                                        TransactionInsufficientFundsException {
        logger.info("Making listing vip by registered user");
        return new ResponseEntity<>(profileService.updateStatus(id, user, ListingType.VIP), HttpStatus.OK);
    }

    @PutMapping("/{id}/makepaid")
    public ResponseEntity<ListingGetDTO> makePaid(@PathVariable long id,
                                                  @RequestAttribute UserDTO user) {
        logger.info("Making listing paid by registered user");
        return new ResponseEntity<>(profileService.updateStatus(id, user, ListingType.STANDARD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable long id,
                                     @RequestAttribute UserDTO user) throws ListingNotFoundException {
        logger.info("Deleting listing by registered user");
        profileService.delete(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{username}/all")
    public ResponseEntity<?> getListings(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                         @RequestParam(required = false, defaultValue = "10") Integer itemsCount,
                                         @RequestParam(required = false, defaultValue = "updatedAt") String sortBy,
                                         @PathVariable String username) {
        logger.info("Getting listings (by username) by unregistered user");
        List<ListingListDTO> listings =profileService.getUserListings(PageRequest.of(pageNo, itemsCount, Sort.by(sortBy)),
                                                                      UserDTO.builder().username(username).build());
        return new ResponseEntity<>(listings, HttpStatus.OK);
    }

}
