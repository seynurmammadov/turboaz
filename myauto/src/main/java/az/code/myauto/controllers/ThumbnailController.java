package az.code.myauto.controllers;

import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.exceptions.ThumbnailNotFoundException;
import az.code.myauto.models.UserData;
import az.code.myauto.services.ThumbnailServiceImpl;
import az.code.myauto.services.interfaces.ListingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/")
public class ThumbnailController {
    final
    ListingService listingService;

    final
    ThumbnailServiceImpl thumbnailService;

    Logger logger = LoggerFactory.getLogger(ProfileController.class);

    public ThumbnailController(ListingService listingService, ThumbnailServiceImpl thumbnailService) {
        this.listingService = listingService;
        this.thumbnailService = thumbnailService;
    }

    @PutMapping("profile/listings/{id}/image")
    public ResponseEntity addImageToListing(@PathVariable long id,
                                            @RequestParam("file") String url, @RequestAttribute UserData user) throws ThumbnailNotFoundException, ListingNotFoundException {
        thumbnailService.uploadImageToListing(id, user, url);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PutMapping("add/image")
    public ResponseEntity<String> addImageToFirebase( @RequestParam("file") MultipartFile multipartFile
            , @RequestAttribute UserData user) throws ListingNotFoundException {
        thumbnailService.uploadImage(user, multipartFile);
        return new ResponseEntity(HttpStatus.OK);
    }
}
