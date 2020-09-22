package fr.insy2s.service;

import fr.insy2s.service.dto.FactureDTO;
import fr.insy2s.service.dto.FactureTemp;
import fr.insy2s.utils.wrapper.WrapperListeFacture;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.Facture}.
 */
public interface FactureService {

    /**
     * Save a facture.
     *
     * @param factureDTO the entity to save.
     * @return the persisted entity.
     */
    FactureDTO save(FactureDTO factureDTO);

    /**
     * Get all the factures.
     *
     * @return the list of entities.
     */
    List<FactureDTO> findAll();


    /**
     * Get the "id" facture.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FactureDTO> findOne(Long id);

    /**
     * Delete the "id" facture.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    FactureDTO postFacture(FactureTemp factureTemp);

    List<FactureDTO> findAllBySocieteId(Long id);


    List<WrapperListeFacture> findAllWrapperVenteBySocieteId(Long id);

    List<FactureDTO> findAllInvoicesByStatement(Long idReleve);

    Long getLastNumFact(Long id);

    /**
     * Get all the factures
     *
     * @param idOperation id of the entity
     * @return the list of entities
     */
    List<FactureDTO> findAllInvoicesByOperationId(Long idOperation);
}
