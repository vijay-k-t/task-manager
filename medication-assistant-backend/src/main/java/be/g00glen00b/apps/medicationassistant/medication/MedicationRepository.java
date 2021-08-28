package be.g00glen00b.apps.medicationassistant.medication;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MedicationRepository extends JpaRepository<Medication, UUID> {
    Optional<Medication> findByNameIgnoreCase(String name);
    Page<Medication> findAllByNameLike(String partialName, Pageable pageable);
}