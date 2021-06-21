package az.code.myauto.repositories;

import az.code.myauto.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, Long> {

}
