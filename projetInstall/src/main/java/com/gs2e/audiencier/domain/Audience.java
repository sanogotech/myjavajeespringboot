package com.gs2e.audiencier.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

import com.gs2e.audiencier.domain.enumeration.Mois;

/**
 * A Audience.
 */
@Entity
@Table(name = "audience")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Audience implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "mois", nullable = false)
    private Mois mois;

    
    @Lob
    @Column(name = "stade", nullable = false)
    private String stade;

    @NotNull
    @Column(name = "date_audience", nullable = false)
    private LocalDate dateAudience;

    @ManyToOne
    @JsonIgnoreProperties(value = "audiences", allowSetters = true)
    private Contentieux contentieux;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mois getMois() {
        return mois;
    }

    public Audience mois(Mois mois) {
        this.mois = mois;
        return this;
    }

    public void setMois(Mois mois) {
        this.mois = mois;
    }

    public String getStade() {
        return stade;
    }

    public Audience stade(String stade) {
        this.stade = stade;
        return this;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }

    public LocalDate getDateAudience() {
        return dateAudience;
    }

    public Audience dateAudience(LocalDate dateAudience) {
        this.dateAudience = dateAudience;
        return this;
    }

    public void setDateAudience(LocalDate dateAudience) {
        this.dateAudience = dateAudience;
    }

    public Contentieux getContentieux() {
        return contentieux;
    }

    public Audience contentieux(Contentieux contentieux) {
        this.contentieux = contentieux;
        return this;
    }

    public void setContentieux(Contentieux contentieux) {
        this.contentieux = contentieux;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Audience)) {
            return false;
        }
        return id != null && id.equals(((Audience) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Audience{" +
            "id=" + getId() +
            ", mois='" + getMois() + "'" +
            ", stade='" + getStade() + "'" +
            ", dateAudience='" + getDateAudience() + "'" +
            "}";
    }
}
