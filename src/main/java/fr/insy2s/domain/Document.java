package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Document.
 */
@Entity
@Table(name = "document")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "chemin_fichier")
    private String cheminFichier;

    @Column(name = "type")
    private String type;

    @Column(name = "nom")
    private String nom;

    @ManyToOne
    @JsonIgnoreProperties("listeDocuments")
    private Facture facture;

    @ManyToOne
    @JsonIgnoreProperties("listeDocuments")
    private Releve releve;

    @ManyToOne
    @JsonIgnoreProperties("listeDocuments")
    private Contrat contrat;

    @ManyToOne
    @JsonIgnoreProperties("listeDocuments")
    private Employe employe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public Document cheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
        return this;
    }

    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    public String getType() {
        return type;
    }

    public Document type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public Document nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Facture getFacture() {
        return facture;
    }

    public Document facture(Facture facture) {
        this.facture = facture;
        return this;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Releve getReleve() {
        return releve;
    }

    public Document releve(Releve releve) {
        this.releve = releve;
        return this;
    }

    public void setReleve(Releve releve) {
        this.releve = releve;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public Document contrat(Contrat contrat) {
        this.contrat = contrat;
        return this;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Document employe(Employe employe) {
        this.employe = employe;
        return this;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Document)) {
            return false;
        }
        return id != null && id.equals(((Document) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Document{" +
            "id=" + getId() +
            ", cheminFichier='" + getCheminFichier() + "'" +
            ", type='" + getType() + "'" +
            ", nom='" + getNom() + "'" +
            "}";
    }
}