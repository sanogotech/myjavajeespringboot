package com.gs2e.audiencier.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gs2e.audiencier.domain.enumeration.Entite;

/**
 * A Contentieux.
 */
@Entity
@Table(name = "contentieux")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contentieux implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "entite")
    private Entite entite;

    
    @Column(name = "ref_contentieux", unique = true)
    private String refContentieux;

    @NotNull
    @Column(name = "date_premiere_audience", nullable = false)
    private LocalDate datePremiereAudience;

    @ManyToOne
    @JsonIgnoreProperties(value = "contentieuxes", allowSetters = true)
    private Requerant requerant;

    @ManyToOne
    @JsonIgnoreProperties(value = "contentieuxes", allowSetters = true)
    private Avocat avocat;

    @ManyToOne
    @JsonIgnoreProperties(value = "contentieuxes", allowSetters = true)
    private Juridiction juridiction;
    
    
    @OneToMany(mappedBy = "contentieux", cascade = CascadeType.REMOVE)
    private Set<Audience> audiences = new HashSet<Audience>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Entite getEntite() {
        return entite;
    }

    public Contentieux entite(Entite entite) {
        this.entite = entite;
        return this;
    }

    public void setEntite(Entite entite) {
        this.entite = entite;
    }

    public String getRefContentieux() {
        return refContentieux;
    }

    public Contentieux refContentieux(String refContentieux) {
        this.refContentieux = refContentieux;
        return this;
    }

    public void setRefContentieux(String refContentieux) {
        this.refContentieux = refContentieux;
    }

    public LocalDate getDatePremiereAudience() {
        return datePremiereAudience;
    }

    public Contentieux datePremiereAudience(LocalDate datePremiereAudience) {
        this.datePremiereAudience = datePremiereAudience;
        return this;
    }

    public void setDatePremiereAudience(LocalDate datePremiereAudience) {
        this.datePremiereAudience = datePremiereAudience;
    }

    public Requerant getRequerant() {
        return requerant;
    }

    public Contentieux requerant(Requerant requerant) {
        this.requerant = requerant;
        return this;
    }

    public void setRequerant(Requerant requerant) {
        this.requerant = requerant;
    }

    public Avocat getAvocat() {
        return avocat;
    }

    public Contentieux avocat(Avocat avocat) {
        this.avocat = avocat;
        return this;
    }

    public void setAvocat(Avocat avocat) {
        this.avocat = avocat;
    }

    public Juridiction getJuridiction() {
        return juridiction;
    }

    public Contentieux juridiction(Juridiction juridiction) {
        this.juridiction = juridiction;
        return this;
    }

    public void setJuridiction(Juridiction juridiction) {
        this.juridiction = juridiction;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contentieux)) {
            return false;
        }
        return id != null && id.equals(((Contentieux) o).id);
    }

    public Set<Audience> getAudiences() {
		return audiences;
	}

	public void setAudiences(Set<Audience> audiences) {
		this.audiences = audiences;
	}

	@Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contentieux{" +
            "id=" + getId() +
            ", entite='" + getEntite() + "'" +
            ", refContentieux='" + getRefContentieux() + "'" +
            ", datePremiereAudience='" + getDatePremiereAudience() + "'" +
            "}";
    }
}
