package io.spiral.express.jhipster.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Coli.
 */
@Entity
@Table(name = "app_coli")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Coli implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "longueur")
    private Double longueur;

    @Column(name = "largeur")
    private Double largeur;

    @Column(name = "hauteur")
    private Double hauteur;

    @Column(name = "poids")
    private Double poids;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Coli description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLongueur() {
        return longueur;
    }

    public Coli longueur(Double longueur) {
        this.longueur = longueur;
        return this;
    }

    public void setLongueur(Double longueur) {
        this.longueur = longueur;
    }

    public Double getLargeur() {
        return largeur;
    }

    public Coli largeur(Double largeur) {
        this.largeur = largeur;
        return this;
    }

    public void setLargeur(Double largeur) {
        this.largeur = largeur;
    }

    public Double getHauteur() {
        return hauteur;
    }

    public Coli hauteur(Double hauteur) {
        this.hauteur = hauteur;
        return this;
    }

    public void setHauteur(Double hauteur) {
        this.hauteur = hauteur;
    }

    public Double getPoids() {
        return poids;
    }

    public Coli poids(Double poids) {
        this.poids = poids;
        return this;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coli)) {
            return false;
        }
        return id != null && id.equals(((Coli) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Coli{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", longueur=" + getLongueur() +
            ", largeur=" + getLargeur() +
            ", hauteur=" + getHauteur() +
            ", poids=" + getPoids() +
            "}";
    }
}
