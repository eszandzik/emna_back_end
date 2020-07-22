package fr.insy2s.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.AutresVariable} entity.
 */
public class AutresVariableDTO implements Serializable {
    
    private Long id;

    private String description;

    private LocalDate date;

    private Double montant;

    private String justificatif;


    private Long employeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getJustificatif() {
        return justificatif;
    }

    public void setJustificatif(String justificatif) {
        this.justificatif = justificatif;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AutresVariableDTO)) {
            return false;
        }

        return id != null && id.equals(((AutresVariableDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AutresVariableDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", date='" + getDate() + "'" +
            ", montant=" + getMontant() +
            ", justificatif='" + getJustificatif() + "'" +
            ", employeId=" + getEmployeId() +
            "}";
    }
}
