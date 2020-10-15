package fr.insy2s.repository;

import fr.insy2s.domain.Avenant;

import fr.insy2s.service.dto.AvenantDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Avenant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvenantRepository extends JpaRepository<Avenant, Long> {
    @Query("from Avenant")
    List<Avenant> getAllAmendmentByIdContract(@Param("idContract") long idContract);


}

