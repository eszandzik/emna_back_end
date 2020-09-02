package fr.insy2s.service.impl;

import fr.insy2s.domain.ClientFournisseur;
import fr.insy2s.domain.Document;
import fr.insy2s.repository.ClientFournisseurRepository;
import fr.insy2s.repository.DocumentRepository;
import fr.insy2s.repository.SocieteRepository;
import fr.insy2s.service.ClientFournisseurService;
import fr.insy2s.service.DocumentService;
import fr.insy2s.service.FactureService;
import fr.insy2s.domain.Facture;
import fr.insy2s.repository.FactureRepository;
import fr.insy2s.service.dto.ClientFournisseurDTO;
import fr.insy2s.service.dto.FactureDTO;
import fr.insy2s.service.dto.FactureTemp;
import fr.insy2s.service.mapper.ClientFournisseurMapper;
import fr.insy2s.service.mapper.FactureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Facture}.
 */
@Service
@Transactional
public class FactureServiceImpl implements FactureService {

    private final Logger log = LoggerFactory.getLogger(FactureServiceImpl.class);

    private final FactureRepository factureRepository;

    private final FactureMapper factureMapper;

    private final DocumentService documentService;

    private final DocumentRepository documentRepository;

    private final SocieteRepository societeRepository;

    private final ClientFournisseurService clientFournisseurService;

    private final ClientFournisseurMapper clientFournisseurMapper;

    private final ClientFournisseurRepository clientFournisseurRepository;

    public FactureServiceImpl(FactureRepository factureRepository, FactureMapper factureMapper, DocumentService documentService, DocumentRepository documentRepository, SocieteRepository societeRepository, ClientFournisseurService clientFournisseurService, ClientFournisseurMapper clientFournisseurMapper, ClientFournisseurRepository clientFournisseurRepository) {
        this.factureRepository = factureRepository;
        this.factureMapper = factureMapper;
        this.documentService = documentService;
        this.documentRepository = documentRepository;
        this.societeRepository = societeRepository;
        this.clientFournisseurService = clientFournisseurService;
        this.clientFournisseurMapper = clientFournisseurMapper;
        this.clientFournisseurRepository = clientFournisseurRepository;
    }

    @Override
    public FactureDTO save(FactureDTO factureDTO) {
        log.debug("Request to save Facture : {}", factureDTO);
        Facture facture = factureMapper.toEntity(factureDTO);
        facture = factureRepository.save(facture);
        return factureMapper.toDto(facture);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FactureDTO> findAll() {
        log.debug("Request to get all Factures");
        return factureRepository.findAll().stream()
            .map(factureMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FactureDTO> findOne(Long id) {
        log.debug("Request to get Facture : {}", id);
        return factureRepository.findById(id)
            .map(factureMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Facture : {}", id);
        factureRepository.deleteById(id);
    }

    @Override
    public FactureDTO postFactureWithFile(FactureTemp factureTemp) {
        Facture facture = factureTemp.toFacture();
        if(factureTemp.getListeFiles()!=null) {
            Set<Document> documents = documentService.multiPartFilesToDocuments(Arrays.asList(factureTemp.getListeFiles()));
            for (Document document : documents
            ) {
                document.setFacture(facture);
                documentRepository.save(document);
            }
            facture.setListeDocuments(documents);
        }

        facture.setSociete(societeRepository.getOne(factureTemp.getSocieteId()));

        Optional<ClientFournisseurDTO> clientFournisseurDTO = clientFournisseurService.findByNomAndSocieteId(factureTemp.getClient(), factureTemp.getSocieteId());
        if (clientFournisseurDTO.isPresent()) {
            facture.setClientFournisseur(clientFournisseurMapper.toEntity(clientFournisseurDTO.get()));
        } else {
            ClientFournisseur clientFournisseur = new ClientFournisseur();
            clientFournisseur.setNom(factureTemp.getClient());
            clientFournisseur.setSociete(societeRepository.getOne(factureTemp.getSocieteId()));
            facture.setClientFournisseur(clientFournisseurRepository.save(clientFournisseur));
        }

        Facture mafacture = factureRepository.save(facture);
        return this.factureMapper.toDto(mafacture);
    }

    @Override
    public List<FactureDTO> findAllBySocieteId(Long id) {
        return factureRepository.
            findAllBySocieteId(id).stream()
            .map(factureMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
