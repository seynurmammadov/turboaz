package az.code.myauto.services;

import az.code.myauto.exceptions.ThumbnailNotFoundException;
import az.code.myauto.models.Listing;
import az.code.myauto.models.Thumbnail;
import az.code.myauto.models.UserData;
import az.code.myauto.repositories.ListingRepo;
import az.code.myauto.repositories.ThumbnailRepo;
import az.code.myauto.services.interfaces.ListingService;
import az.code.myauto.services.interfaces.ThumbnailService;
import az.code.myauto.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ThumbnailServiceImpl implements ThumbnailService {
    @Autowired
    ThumbnailRepo thumbnailRepo;
    @Autowired
    FileUploadUtil fileService;
    @Autowired
    ListingService listingService;
    @Autowired
    ListingRepo listingRepo;

    public List<Thumbnail> getThumbnailByListingId(Long id) throws ThumbnailNotFoundException {
        Optional<List<Thumbnail>> t = thumbnailRepo.findThumbnailByListingId(id);
        if (t.isPresent()) {
            return t.get();
        }
        throw new ThumbnailNotFoundException();
    }
    public String uploadImage(UserData user, MultipartFile multipartFile){
        return fileService.upload(multipartFile);
    }

    @Override
    public Thumbnail uploadImageToListing(long id, UserData user, String url) {
        Listing listing = listingService.listingCheck(id,user);
        listing.getThumbnails().add(Thumbnail.builder().url(url).build());
        listingRepo.save(listing);
        return listing.getThumbnails().get(listing.getThumbnails().size()-1);
    }
}