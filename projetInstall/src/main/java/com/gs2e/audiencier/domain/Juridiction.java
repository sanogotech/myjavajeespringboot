package com.gs2e.audiencier.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Juridiction.
 */
@Entity
@Table(name = "juridiction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Juridiction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2)
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Size(min = 2)
    @Column(name = "ville", nullable = false)
    private String ville;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Juridiction nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public Juridiction ville(String ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Juridiction)) {
            return false;
        }
        return id != null && id.equals(((Juridiction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Juridiction{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", ville='" + getVille() + "'" +
            "}";
    }
}
