package az.code.myauto.controllers;

import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.exceptions.ThumbnailNotFoundException;
import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.ThumbnailDTO;
import az.code.myauto.services.ThumbnailServiceImpl;
import az.code.myauto.services.interfaces.ListingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public ResponseEntity<ThumbnailDTO> addImageToListing(@PathVariable long id,
                                            @RequestBody ThumbnailDTO thumbnailDTO,
                                            @RequestAttribute UserData user)  throws ListingNotFoundException {
        logger.info("Uploading image to listing");
        return new ResponseEntity<>(thumbnailService.uploadImageToListing(id, user, thumbnailDTO.getThumbnail()),HttpStatus.OK);
    }

    @PutMapping("add/image")
    public ResponseEntity<String> addImageToFirebase( @RequestParam("file") MultipartFile multipartFile,
                                                      @RequestAttribute UserData user) throws ListingNotFoundException {
        logger.info("Uploading image to Firebase");

        return new ResponseEntity<>( thumbnailService.uploadImage(user, multipartFile),HttpStatus.OK);
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

    @GetMapping("listings/{id}/images")
    public ResponseEntity<List<ThumbnailDTO>> getImagesByListingId(@PathVariable long id) throws ListingNotFoundException, ThumbnailNotFoundException {
        logger.info("Getting images by listing id");
        return new ResponseEntity<>(thumbnailService.getThumbnailsByListingId(id),HttpStatus.OK);
    }



}
