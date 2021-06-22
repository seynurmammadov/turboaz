package az.code.myauto.services.interfaces;

import az.code.myauto.exceptions.ThumbnailNotFoundException;
import az.code.myauto.models.Image;
import az.code.myauto.models.dtos.ImageDTO;
import az.code.myauto.models.dtos.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    List<ImageDTO> getImagesByListingId(Long id) throws ThumbnailNotFoundException;

    ImageDTO getImageByListingId(Long listingId, Long id) throws ThumbnailNotFoundException;

    String uploadImageToFireBase(UserDTO user, MultipartFile multipartFile);

    ImageDTO addImageToListing(Long id, UserDTO user, String url);

    Image isImageExist(Long listingId, Long id) throws ThumbnailNotFoundException;

    void deleteImage(UserDTO user, Long listingId, Long id) throws ThumbnailNotFoundException;
}
