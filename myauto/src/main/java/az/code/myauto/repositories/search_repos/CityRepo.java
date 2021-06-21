package az.code.myauto.repositories.search_repos;

import az.code.myauto.models.City;
import az.code.myauto.models.Listing;
import az.code.myauto.models.dtos.CityDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepo extends JpaRepository<City, Long> {

}
