package az.code.myauto.repositories;

import az.code.myauto.models.City;
import az.code.myauto.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepo extends JpaRepository<Equipment, Long> {
}
