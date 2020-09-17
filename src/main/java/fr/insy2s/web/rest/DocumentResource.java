package fr.insy2s.web.rest;

import fr.insy2s.service.DocumentService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.DocumentDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.insy2s.domain.Document}.
 */
@RestController
@RequestMapping("/api")
public class DocumentResource {

    private final Logger log = LoggerFactory.getLogger(DocumentResource.class);

    private static final String ENTITY_NAME = "document";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DocumentService documentService;

    public DocumentResource(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * {@code POST  /documents} : Create a new document.
     *
     * @param documentDTO the documentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new documentDTO, or with status {@code 400 (Bad Request)} if the document has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/documents")
    public ResponseEntity<DocumentDTO> createDocument(@RequestBody DocumentDTO documentDTO) throws URISyntaxException {
        log.debug("REST request to save Document : {}", documentDTO);
        if (documentDTO.getId() != null) {
            throw new BadRequestAlertException("A new document cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DocumentDTO result = documentService.save(documentDTO);
        return ResponseEntity.created(new URI("/api/documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /documents} : Updates an existing document.
     *
     * @param documentDTO the documentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentDTO,
     * or with status {@code 400 (Bad Request)} if the documentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the documentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/documents")
    public ResponseEntity<DocumentDTO> updateDocument(@RequestBody DocumentDTO documentDTO) throws URISyntaxException {
        log.debug("REST request to update Document : {}", documentDTO);
        if (documentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DocumentDTO result = documentService.save(documentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, documentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /documents} : get all the documents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of documents in body.
     */
    @GetMapping("/documents")
    public List<DocumentDTO> getAllDocuments() {
        log.debug("REST request to get all Documents");
        return documentService.findAll();
    }

    /**
     * {@code GET  /documents/:id} : get the "id" document.
     *
     * @param id the id of the documentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the documentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/documents/{id}")
    public ResponseEntity<DocumentDTO> getDocument(@PathVariable Long id) {
        log.debug("REST request to get Document : {}", id);
        Optional<DocumentDTO> documentDTO = documentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documentDTO);
    }

    /**
     * {@code DELETE  /documents/:id} : delete the "id" document.
     *
     * @param id the id of the documentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/documents/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        log.debug("REST request to delete Document : {}", id);
        documentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /documentsAbsence/:id} : get all documents by "id" Absence.
     *
     * @param id the id of Absence.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and single or List Documents in body.
     */
    @GetMapping("/documentsAbsence/{id}")
    public List<DocumentDTO> getDocumentsByAbsencesId(@PathVariable Long id) {
        log.debug("REST request to get all Documents by Absence id");
        return documentService.findAllByAbsenceId(id);
    }

    /**
     * {@code GET  /documentsNoteDeFrais/:id} : get all documents by "id" Note de Frais.
     *
     * @param id the id of Absence.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and single or List Documents in body.
     */
    @GetMapping("/documentsNoteDeFrais/{id}")
    public List<DocumentDTO> getDocumentsByNoteDeFraisId(@PathVariable Long id) {
        log.debug("REST request to get all Documents by Note de Frais id");
        return documentService.findAllByNoteDeFraisId(id);
    }

    /**
     * {@code GET  /documentsAutre/:id} : get all documents by "id" Autre.
     *
     * @param id the id of Autre.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and single or List Documents in body.
     */
    /*@GetMapping("/documentsAutre/{id}")
    public List<DocumentDTO> getDocumentsByAutreId(@PathVariable Long id) {
        log.debug("REST request to get all Documents by Autre id");
        return documentService.findAllByAutresVariableId(id);
    }*/
}
