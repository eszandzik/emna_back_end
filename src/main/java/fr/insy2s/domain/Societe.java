package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Societe.
 */
@Entity
@Table(name = "societe")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Societe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "civilite")
    private String civilite;

    @OneToOne
    @JoinColumn(unique = true)
    private Adresse adresse;

    @OneToOne
    @JoinColumn(unique = true)
    private InfoEntreprise infoEntreprise;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "societe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Facture> listeFactures = new HashSet<>();

    @OneToMany(mappedBy = "societe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Devis> listeDevis = new HashSet<>();

    @OneToMany(mappedBy = "societe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Releve> listeReleves = new HashSet<>();

    @OneToMany(mappedBy = "societe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Produit> listeProduits = new HashSet<>();

    @OneToMany(mappedBy = "societe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ClientFournisseur> listeClientsFournisseurs = new HashSet<>();

    @OneToMany(mappedBy = "societe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Clause> listeClauses = new HashSet<>();

    @OneToMany(mappedBy = "societe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Article> listeArticles = new HashSet<>();

    @OneToMany(mappedBy = "societe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Employe> listeEmployes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "listeSocietes", allowSetters = true)
    private Comptable comptable;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCivilite() {
        return civilite;
    }

    public Societe civilite(String civilite) {
        this.civilite = civilite;
        return this;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public Societe adresse(Adresse adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public InfoEntreprise getInfoEntreprise() {
        return infoEntreprise;
    }

    public Societe infoEntreprise(InfoEntreprise infoEntreprise) {
        this.infoEntreprise = infoEntreprise;
        return this;
    }

    public void setInfoEntreprise(InfoEntreprise infoEntreprise) {
        this.infoEntreprise = infoEntreprise;
    }

    public User getUser() {
        return user;
    }

    public Societe user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Facture> getListeFactures() {
        return listeFactures;
    }

    public Societe listeFactures(Set<Facture> factures) {
        this.listeFactures = factures;
        return this;
    }

    public Societe addListeFactures(Facture facture) {
        this.listeFactures.add(facture);
        facture.setSociete(this);
        return this;
    }

    public Societe removeListeFactures(Facture facture) {
        this.listeFactures.remove(facture);
        facture.setSociete(null);
        return this;
    }

    public void setListeFactures(Set<Facture> factures) {
        this.listeFactures = factures;
    }

    public Set<Devis> getListeDevis() {
        return listeDevis;
    }

    public Societe listeDevis(Set<Devis> devis) {
        this.listeDevis = devis;
        return this;
    }

    public Societe addListeDevis(Devis devis) {
        this.listeDevis.add(devis);
        devis.setSociete(this);
        return this;
    }

    public Societe removeListeDevis(Devis devis) {
        this.listeDevis.remove(devis);
        devis.setSociete(null);
        return this;
    }

    public void setListeDevis(Set<Devis> devis) {
        this.listeDevis = devis;
    }

    public Set<Releve> getListeReleves() {
        return listeReleves;
    }

    public Societe listeReleves(Set<Releve> releves) {
        this.listeReleves = releves;
        return this;
    }

    public Societe addListeReleves(Releve releve) {
        this.listeReleves.add(releve);
        releve.setSociete(this);
        return this;
    }

    public Societe removeListeReleves(Releve releve) {
        this.listeReleves.remove(releve);
        releve.setSociete(null);
        return this;
    }

    public void setListeReleves(Set<Releve> releves) {
        this.listeReleves = releves;
    }

    public Set<Produit> getListeProduits() {
        return listeProduits;
    }

    public Societe listeProduits(Set<Produit> produits) {
        this.listeProduits = produits;
        return this;
    }

    public Societe addListeProduits(Produit produit) {
        this.listeProduits.add(produit);
        produit.setSociete(this);
        return this;
    }

    public Societe removeListeProduits(Produit produit) {
        this.listeProduits.remove(produit);
        produit.setSociete(null);
        return this;
    }

    public void setListeProduits(Set<Produit> produits) {
        this.listeProduits = produits;
    }

    public Set<ClientFournisseur> getListeClientsFournisseurs() {
        return listeClientsFournisseurs;
    }

    public Societe listeClientsFournisseurs(Set<ClientFournisseur> clientFournisseurs) {
        this.listeClientsFournisseurs = clientFournisseurs;
        return this;
    }

    public Societe addListeClientsFournisseurs(ClientFournisseur clientFournisseur) {
        this.listeClientsFournisseurs.add(clientFournisseur);
        clientFournisseur.setSociete(this);
        return this;
    }

    public Societe removeListeClientsFournisseurs(ClientFournisseur clientFournisseur) {
        this.listeClientsFournisseurs.remove(clientFournisseur);
        clientFournisseur.setSociete(null);
        return this;
    }

    public void setListeClientsFournisseurs(Set<ClientFournisseur> clientFournisseurs) {
        this.listeClientsFournisseurs = clientFournisseurs;
    }

    public Set<Clause> getListeClauses() {
        return listeClauses;
    }

    public Societe listeClauses(Set<Clause> clauses) {
        this.listeClauses = clauses;
        return this;
    }

    public Societe addListeClauses(Clause clause) {
        this.listeClauses.add(clause);
        clause.setSociete(this);
        return this;
    }

    public Societe removeListeClauses(Clause clause) {
        this.listeClauses.remove(clause);
        clause.setSociete(null);
        return this;
    }

    public void setListeClauses(Set<Clause> clauses) {
        this.listeClauses = clauses;
    }

    public Set<Article> getListeArticles() {
        return listeArticles;
    }

    public Societe listeArticles(Set<Article> articles) {
        this.listeArticles = articles;
        return this;
    }

    public Societe addListeArticles(Article article) {
        this.listeArticles.add(article);
        article.setSociete(this);
        return this;
    }

    public Societe removeListeArticles(Article article) {
        this.listeArticles.remove(article);
        article.setSociete(null);
        return this;
    }

    public void setListeArticles(Set<Article> articles) {
        this.listeArticles = articles;
    }

    public Set<Employe> getListeEmployes() {
        return listeEmployes;
    }

    public Societe listeEmployes(Set<Employe> employes) {
        this.listeEmployes = employes;
        return this;
    }

    public Societe addListeEmployes(Employe employe) {
        this.listeEmployes.add(employe);
        employe.setSociete(this);
        return this;
    }

    public Societe removeListeEmployes(Employe employe) {
        this.listeEmployes.remove(employe);
        employe.setSociete(null);
        return this;
    }

    public void setListeEmployes(Set<Employe> employes) {
        this.listeEmployes = employes;
    }

    public Comptable getComptable() {
        return comptable;
    }

    public Societe comptable(Comptable comptable) {
        this.comptable = comptable;
        return this;
    }

    public void setComptable(Comptable comptable) {
        this.comptable = comptable;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Societe)) {
            return false;
        }
        return id != null && id.equals(((Societe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Societe{" +
            "id=" + getId() +
            ", civilite='" + getCivilite() + "'" +
            "}";
    }
}
