package com.gs2e.audiencier.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Requerant.
 */
@Entity
@Table(name = "requerant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Requerant implements Serializable {

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
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @NotNull
    @Size(min = 2)
    @Column(name = "num_cni", nullable = false)
    private String numCNI;

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

    public Requerant nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Requerant prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumCNI() {
        return numCNI;
    }

    public Requerant numCNI(String numCNI) {
        this.numCNI = numCNI;
        return this;
    }

    public void setNumCNI(String numCNI) {
        this.numCNI = numCNI;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Requerant)) {
            return false;
        }
        return id != null && id.equals(((Requerant) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Requerant{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", numCNI='" + getNumCNI() + "'" +
            "}";
    }
}
