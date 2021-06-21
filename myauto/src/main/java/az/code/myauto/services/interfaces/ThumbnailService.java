package az.code.myauto.services.interfaces;

import az.code.myauto.exceptions.ThumbnailNotFoundException;
import az.code.myauto.models.Thumbnail;
import az.code.myauto.models.UserData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ThumbnailService {
    List<Thumbnail> getThumbnailByListingId(Long id) throws ThumbnailNotFoundException;
    String uploadImage(UserData user, MultipartFile multipartFile);
    Thumbnail uploadImageToListing(long id,UserData user, String url);
}
