package az.code.myauto.controllers;

import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.ListingCreationDTO;
import az.code.myauto.models.dtos.ListingGetDTO;
import az.code.myauto.services.interfaces.ListingService;
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

    @PostMapping("/listings")
    public ResponseEntity<ListingGetDTO> getUser(@RequestBody ListingCreationDTO listingCreationDTO, @RequestAttribute UserData user) {

        return new ResponseEntity<>(listingService.create(listingCreationDTO,user), HttpStatus.CREATED);
    }
}
