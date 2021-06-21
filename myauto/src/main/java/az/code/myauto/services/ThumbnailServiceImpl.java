package az.code.myauto.services;

import az.code.myauto.exceptions.ThumbnailNotFoundException;
import az.code.myauto.models.Listing;
import az.code.myauto.models.Thumbnail;
import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.ThumbnailDTO;
import az.code.myauto.repositories.ListingRepo;
import az.code.myauto.repositories.ThumbnailRepo;
import az.code.myauto.services.interfaces.ListingService;
import az.code.myauto.services.interfaces.ThumbnailService;
import az.code.myauto.utils.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ThumbnailServiceImpl implements ThumbnailService {
    final
    ThumbnailRepo thumbnailRepo;
    final
    FileUploadUtil fileService;
    final
    ListingService listingService;
    final
    ListingRepo listingRepo;

    public ThumbnailServiceImpl(ThumbnailRepo thumbnailRepo, FileUploadUtil fileService, ListingService listingService, ListingRepo listingRepo) {
        this.thumbnailRepo = thumbnailRepo;
        this.fileService = fileService;
        this.listingService = listingService;
        this.listingRepo = listingRepo;
    }

    public List<ThumbnailDTO> getThumbnailsByListingId(Long id) throws ThumbnailNotFoundException {
        Optional<List<Thumbnail>> t = thumbnailRepo.findThumbnailsByListingId(id);
        if (t.isPresent()) {
            return t.get().stream().map(thumbnail -> new ThumbnailDTO(thumbnail)).collect(Collectors.toList());
        }
        throw new ThumbnailNotFoundException();
    }

    @Override
    public ThumbnailDTO getThumbnailById(Long listingId, Long id) throws ThumbnailNotFoundException {
        return new ThumbnailDTO(thumbnailCheck(listingId,id));
    }

    @Override
    public String uploadImage(UserData user, MultipartFile multipartFile){
        return fileService.upload(multipartFile);
    }

    @Override
    public ThumbnailDTO uploadImageToListing(Long id, UserData user, String url) {
        Listing listing = listingService.listingCheck(id,user);
        listing.getThumbnails().add(Thumbnail.builder().url(url).listing(listing).build());
        return new ThumbnailDTO(listingRepo.save(listing).getThumbnails().get(listing.getThumbnails().size()-1));
    }
    @Override
    public Thumbnail thumbnailCheck(Long listingId, Long id) throws ThumbnailNotFoundException {
        Optional<Thumbnail> thumb =  thumbnailRepo.findThumbById(listingId,id);
        if (thumb.isPresent()) {
            return thumb.get();
        }
        throw new ThumbnailNotFoundException();
    }

    @Override
    public void deleteThumbnail(UserData user, Long listingId, Long id) throws ThumbnailNotFoundException {
        String wholeImage = thumbnailCheck(listingId,id).getUrl();
        fileService.delete(wholeImage.substring(72, wholeImage.length() - 10));
        thumbnailRepo.deleteThumbById(listingId,id);
    }

}
