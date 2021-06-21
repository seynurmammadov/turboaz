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

    Logger logger = LoggerFactory.getLogger(ThumbnailController.class);

    public ThumbnailController(ListingService listingService, ThumbnailServiceImpl thumbnailService) {
        this.listingService = listingService;
        this.thumbnailService = thumbnailService;
    }

    @PutMapping("profile/listings/{id}/image")
    public ResponseEntity<String> addImageToListing(@PathVariable long id,
                                            @RequestParam("file") String url,
                                            @RequestAttribute UserData user) throws ThumbnailNotFoundException, ListingNotFoundException {
        logger.info("Uploading image to listing");
        thumbnailService.uploadImageToListing(id, user, url);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("add/image")
    public ResponseEntity<String> addImageToFirebase( @RequestParam("file") MultipartFile multipartFile,
                                                      @RequestAttribute UserData user) throws ListingNotFoundException {
        logger.info("Uploading image to Firebase");
        thumbnailService.uploadImage(user, multipartFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("listings/{listingId}/image/{imageId}")
    public ResponseEntity<String> deleteImageFromListing( @PathVariable long listingId,
                                                          @PathVariable long imageId,
                                                          @RequestAttribute UserData user) throws ListingNotFoundException {
        logger.info("Deleting image from listing");
//        thumbnailService.deleteImage(user, listingId, imageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("listings/{listingId}/image/{imageId}")
    public ResponseEntity<String> getImageByImageId(@PathVariable long listingId,
                                                    @PathVariable long imageId) throws ListingNotFoundException {
        logger.info("Getting image by image id");
//        thumbnailService.getImageById(imageId, listingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listings/{id}/images")
    public ResponseEntity<String> getImagesByListingId(@PathVariable long id) throws ListingNotFoundException {
        logger.info("Getting images by listing id");
//        thumbnailService.getImageById(imageId, listingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
