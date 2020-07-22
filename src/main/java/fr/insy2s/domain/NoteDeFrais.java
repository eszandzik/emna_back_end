package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A NoteDeFrais.
 */
@Entity
@Table(name = "note_de_frais")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NoteDeFrais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "designation", nullable = false)
    private String designation;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "montant", nullable = false)
    private Double montant;

    @Column(name = "justificatif")
    private String justificatif;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeNoteDeFrais", allowSetters = true)
    private Employe employe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public NoteDeFrais designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public LocalDate getDate() {
        return date;
    }

    public NoteDeFrais date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public NoteDeFrais montant(Double montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getJustificatif() {
        return justificatif;
    }

    public NoteDeFrais justificatif(String justificatif) {
        this.justificatif = justificatif;
        return this;
    }

    public void setJustificatif(String justificatif) {
        this.justificatif = justificatif;
    }

    public Employe getEmploye() {
        return employe;
    }

    public NoteDeFrais employe(Employe employe) {
        this.employe = employe;
        return this;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NoteDeFrais)) {
            return false;
        }
        return id != null && id.equals(((NoteDeFrais) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NoteDeFrais{" +
            "id=" + getId() +
            ", designation='" + getDesignation() + "'" +
            ", date='" + getDate() + "'" +
            ", montant=" + getMontant() +
            ", justificatif='" + getJustificatif() + "'" +
            "}";
    }
}
