package az.code.myauto.services;

import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.exceptions.ThumbnailNotFoundException;
import az.code.myauto.models.Thumbnail;
import az.code.myauto.repositories.ThumbnailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThumbnailService {
    @Autowired
    ThumbnailRepo thumbnailRepo;

    public List<Thumbnail> getThumbnailByListingId(Long id) throws ThumbnailNotFoundException {
        Optional<List<Thumbnail>> t = thumbnailRepo.findThumbnailByListingId(id);
        if (t.isPresent()) {
            return t.get();
        }
        throw new ThumbnailNotFoundException();
    }

}
