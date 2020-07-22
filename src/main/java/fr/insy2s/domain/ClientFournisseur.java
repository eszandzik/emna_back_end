package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ClientFournisseur.
 */
@Entity
@Table(name = "client_fournisseur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClientFournisseur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private InfoEntreprise infoEntreprise;

    @OneToMany(mappedBy = "clientFournisseur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Facture> listeFactures = new HashSet<>();

    @OneToMany(mappedBy = "clientFournisseur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Devis> listeDevis = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "listeClientsFournisseurs", allowSetters = true)
    private Societe societe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InfoEntreprise getInfoEntreprise() {
        return infoEntreprise;
    }

    public ClientFournisseur infoEntreprise(InfoEntreprise infoEntreprise) {
        this.infoEntreprise = infoEntreprise;
        return this;
    }

    public void setInfoEntreprise(InfoEntreprise infoEntreprise) {
        this.infoEntreprise = infoEntreprise;
    }

    public Set<Facture> getListeFactures() {
        return listeFactures;
    }

    public ClientFournisseur listeFactures(Set<Facture> factures) {
        this.listeFactures = factures;
        return this;
    }

    public ClientFournisseur addListeFactures(Facture facture) {
        this.listeFactures.add(facture);
        facture.setClientFournisseur(this);
        return this;
    }

    public ClientFournisseur removeListeFactures(Facture facture) {
        this.listeFactures.remove(facture);
        facture.setClientFournisseur(null);
        return this;
    }

    public void setListeFactures(Set<Facture> factures) {
        this.listeFactures = factures;
    }

    public Set<Devis> getListeDevis() {
        return listeDevis;
    }

    public ClientFournisseur listeDevis(Set<Devis> devis) {
        this.listeDevis = devis;
        return this;
    }

    public ClientFournisseur addListeDevis(Devis devis) {
        this.listeDevis.add(devis);
        devis.setClientFournisseur(this);
        return this;
    }

    public ClientFournisseur removeListeDevis(Devis devis) {
        this.listeDevis.remove(devis);
        devis.setClientFournisseur(null);
        return this;
    }

    public void setListeDevis(Set<Devis> devis) {
        this.listeDevis = devis;
    }

    public Societe getSociete() {
        return societe;
    }

    public ClientFournisseur societe(Societe societe) {
        this.societe = societe;
        return this;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientFournisseur)) {
            return false;
        }
        return id != null && id.equals(((ClientFournisseur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientFournisseur{" +
            "id=" + getId() +
            "}";
    }
}
