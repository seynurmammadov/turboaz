package az.code.myauto.services.interfaces;

import az.code.myauto.exceptions.ThumbnailNotFoundException;
import az.code.myauto.models.Thumbnail;
import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.ThumbnailDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ThumbnailService {
    List<ThumbnailDTO> getThumbnailsByListingId(Long id) throws ThumbnailNotFoundException;
    ThumbnailDTO getThumbnailById(Long listingId,Long id) throws ThumbnailNotFoundException;
    String uploadImage(UserData user, MultipartFile multipartFile);
    ThumbnailDTO uploadImageToListing(Long id, UserData user, String url);
    Thumbnail thumbnailCheck(Long listingId, Long id) throws ThumbnailNotFoundException;
}
