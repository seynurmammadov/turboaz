package az.code.myauto.services;

import az.code.myauto.exceptions.ThumbnailNotFoundException;
import az.code.myauto.models.Listing;
import az.code.myauto.models.Image;
import az.code.myauto.models.dtos.ImageDTO;
import az.code.myauto.models.dtos.UserDTO;
import az.code.myauto.repositories.ListingRepo;
import az.code.myauto.repositories.ImageRepo;
import az.code.myauto.services.interfaces.ListingService;
import az.code.myauto.services.interfaces.ImageService;
import az.code.myauto.utils.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    final
    ImageRepo imageRepo;
    final
    FileUploadUtil fileService;
    final
    ListingService listingService;
    final
    ListingRepo listingRepo;

    public ImageServiceImpl(ImageRepo imageRepo, FileUploadUtil fileService, ListingService listingService, ListingRepo listingRepo) {
        this.imageRepo = imageRepo;
        this.fileService = fileService;
        this.listingService = listingService;
        this.listingRepo = listingRepo;
    }

    @Override
    public List<ImageDTO> getImagesByListingId(Long id) throws ThumbnailNotFoundException {
        Optional<List<Image>> t = imageRepo.findListingImagesById(id);
        if (t.isPresent()) {
            return t.get().stream().map(ImageDTO::new).collect(Collectors.toList());
        }
        throw new ThumbnailNotFoundException();
    }

    @Override
    public ImageDTO getImageByListingId(Long listingId, Long id) throws ThumbnailNotFoundException {
        return new ImageDTO(isImageExist(listingId, id));
    }

    @Override
    public String uploadImageToFireBase(UserDTO user, MultipartFile multipartFile) {
        return fileService.upload(multipartFile);
    }

    @Override
    public ImageDTO addImageToListing(Long id, UserDTO user, String url) {
        Listing listing = listingService.isListingExist(id, user);
        listing.getImages().add(Image.builder().url(url).listing(listing).build());
        return new ImageDTO(listingRepo.save(listing).getImages().get(listing.getImages().size() - 1));
    }

    @Override
    public Image isImageExist(Long listingId, Long id) throws ThumbnailNotFoundException {
        Optional<Image> image = imageRepo.findImageById(listingId, id);
        if (image.isPresent()) {
            return image.get();
        }
        throw new ThumbnailNotFoundException();
    }

    @Override
    public void deleteImage(UserDTO user, Long listingId, Long id) throws ThumbnailNotFoundException {
        String wholeImage = isImageExist(listingId, id).getUrl();
        fileService.delete(wholeImage.substring(72, wholeImage.length() - 10));
        imageRepo.deleteImageByid(listingId, id);
    }

}
