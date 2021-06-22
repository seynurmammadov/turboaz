package az.code.myauto.repositories;

import az.code.myauto.models.Make;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MakeRepo extends JpaRepository<Make, Long> {
}
