package az.code.myauto.services;

import az.code.myauto.config.FireBaseProperties;
import az.code.myauto.exceptions.ThumbnailNotFoundException;
import az.code.myauto.models.Listing;
import az.code.myauto.models.Image;
import az.code.myauto.models.dtos.ImageDTO;
import az.code.myauto.models.dtos.UserDTO;
import az.code.myauto.models.mappers.MapperModel;
import az.code.myauto.repositories.ListingRepo;
import az.code.myauto.repositories.ImageRepo;
import az.code.myauto.services.interfaces.ListingService;
import az.code.myauto.services.interfaces.ImageService;
import az.code.myauto.services.interfaces.ProfileService;
import az.code.myauto.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    ProfileService profileService;

    final
    MapperModel mapperModel;

    @Autowired
    FireBaseProperties fireBaseProperties;

    public ImageServiceImpl(ImageRepo imageRepo, FileUploadUtil fileService, ProfileService profileService, MapperModel mapperModel) {
        this.imageRepo = imageRepo;
        this.fileService = fileService;
        this.profileService = profileService;
        this.mapperModel = mapperModel;
    }

    @Override
    public List<ImageDTO> getImagesByListingId(Long id) throws ThumbnailNotFoundException {
        Optional<List<Image>> t = imageRepo.findListingImagesById(id);
        if (t.isPresent()) {
            return t.get().stream().map(i -> mapperModel.entityToDTO(i, ImageDTO.class)).collect(Collectors.toList());
        }
        throw new ThumbnailNotFoundException();
    }

    @Override
    public ImageDTO getImageByListingId(Long listingId, Long id) throws ThumbnailNotFoundException {
        return mapperModel.entityToDTO(isImageExist(listingId, id), ImageDTO.class);
    }

    @Override
    public String uploadImageToFireBase(UserDTO user, MultipartFile multipartFile) {
        return fileService.upload(multipartFile);
    }

    @Override
    public ImageDTO addImageToListing(Long id, UserDTO user, String url) {
        Listing listing = profileService.isListingExist(id, user);
        Image image = imageRepo.save(Image.builder().name(url).listing(listing).build());
        return mapperModel.entityToDTO(image, ImageDTO.class);
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
        String wholeImage = isImageExist(listingId, id).getName();
        fileService.delete(wholeImage.substring(fireBaseProperties.getUrlFirstPart().length(), wholeImage.length() - fireBaseProperties.getUrlSecondPart().length()));
        imageRepo.deleteImageByid(listingId, id);
    }
}
