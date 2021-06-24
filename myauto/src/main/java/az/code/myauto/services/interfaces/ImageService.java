package az.code.myauto.services.interfaces;

import az.code.myauto.exceptions.ThumbnailNotFoundException;
import az.code.myauto.models.Image;
import az.code.myauto.models.dtos.ImageDTO;
import az.code.myauto.models.dtos.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    /**
     * This method is for getting all images by listing id.
     * @param id
     * @return
     * @throws ThumbnailNotFoundException
     */
    List<ImageDTO> getImagesByListingId(Long id) throws ThumbnailNotFoundException;

    /**
     * This method is for getting image by listing id and image id.
     * @param listingId
     * @param id
     * @return
     * @throws ThumbnailNotFoundException
     */
    ImageDTO getImageByListingId(Long listingId, Long id) throws ThumbnailNotFoundException;

    /**
     * This method is for uploading image to FireBase.
     * @param user
     * @param multipartFile
     * @return
     */
    String uploadImageToFireBase(UserDTO user, MultipartFile multipartFile);

    /**
     * This method is for adding image url to the listing
     * @param id
     * @param user
     * @param url
     * @return
     */
    ImageDTO addImageToListing(Long id, UserDTO user, String url);

    /**
     * This method is for checking if image exists or not.
     * @param listingId
     * @param id
     * @return
     * @throws ThumbnailNotFoundException
     */
    Image isImageExist(Long listingId, Long id) throws ThumbnailNotFoundException;

    /**
     * This method is for deleting image url from listing.
     * @param user
     * @param listingId
     * @param id
     * @throws ThumbnailNotFoundException
     */
    void deleteImage(UserDTO user, Long listingId, Long id) throws ThumbnailNotFoundException;
}
