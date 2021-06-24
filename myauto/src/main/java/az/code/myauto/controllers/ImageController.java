package az.code.myauto.controllers;

import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.exceptions.ThumbnailNotFoundException;
import az.code.myauto.models.dtos.ImageDTO;
import az.code.myauto.models.dtos.UserDTO;
import az.code.myauto.services.ImageServiceImpl;
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
public class ImageController {
    final
    ListingService listingService;

    final
    ImageServiceImpl imageService;

    Logger logger = LoggerFactory.getLogger(ImageController.class);

    public ImageController(ListingService listingService, ImageServiceImpl imageService) {
        this.listingService = listingService;
        this.imageService = imageService;
    }

    @PutMapping("profile/listings/{id}/image")
    public ResponseEntity<ImageDTO> addImageToListing(@PathVariable long id,
                                                      @RequestBody ImageDTO imageDTO,
                                                      @RequestAttribute UserDTO user) throws ListingNotFoundException {
        logger.info("Uploading image to listing");
        return new ResponseEntity<>(imageService.addImageToListing(id, user, imageDTO.getName()), HttpStatus.OK);
    }

    @PutMapping("add/image")
    public ResponseEntity<String> addImageToFirebase(@RequestParam("file") MultipartFile multipartFile,
                                                     @RequestAttribute UserDTO user) throws ListingNotFoundException {
        logger.info("Uploading image to Firebase");
        return new ResponseEntity<>(imageService.uploadImageToFireBase(user, multipartFile), HttpStatus.OK);
    }

    @DeleteMapping("profile/listings/{listingId}/image/{imageId}")
    public ResponseEntity deleteImageFromListing(@PathVariable Long listingId,
                                                 @PathVariable Long imageId,
                                                 @RequestAttribute UserDTO user) throws ListingNotFoundException,
                                                                                        ThumbnailNotFoundException {
        logger.info("Deleting image from listing");
        imageService.deleteImage(user, listingId, imageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("listings/{listingId}/image/{imageId}")
    public ResponseEntity<ImageDTO> getImageByImageId(@PathVariable Long listingId,
                                                      @PathVariable Long imageId) throws ThumbnailNotFoundException {
        logger.info("Getting image by image id");
        return new ResponseEntity<>(imageService.getImageByListingId(listingId, imageId), HttpStatus.OK);
    }

    @GetMapping("listings/{id}/images")
    public ResponseEntity<List<ImageDTO>> getImagesByListingId(@PathVariable long id) throws ListingNotFoundException,
                                                                                             ThumbnailNotFoundException {
        logger.info("Getting images by listing id");
        return new ResponseEntity<>(imageService.getImagesByListingId(id), HttpStatus.OK);
    }

}
