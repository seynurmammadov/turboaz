package az.code.myauto.repositories.search_repos;

import az.code.myauto.models.Make;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MakeRepo extends JpaRepository<Make, Long> {
}
