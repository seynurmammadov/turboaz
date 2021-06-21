package az.code.myauto.repositories;

import az.code.myauto.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepo extends JpaRepository<Model, Long> {
    List<Model> getAllByMake_Id(long makeId);
}
