package be.g00glen00b.apps.medicationassistant.availability;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface MedicationAvailabilityRepository extends JpaRepository<MedicationAvailability, MedicationAvailabilityId> {
    @Query("select a from MedicationAvailability a where a.id.userId = ?1")
    Page<MedicationAvailability> findAllByUserId(UUID userId, Pageable pageable);
}