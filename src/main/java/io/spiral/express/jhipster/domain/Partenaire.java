package io.spiral.express.jhipster.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Partenaire.
 */
@Entity
@Table(name = "partenaire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Partenaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "numero")
    private Long numero;

    @OneToOne
    @JoinColumn(unique = true)
    private Personne personne;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public Partenaire numero(Long numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Personne getPersonne() {
        return personne;
    }

    public Partenaire personne(Personne personne) {
        this.personne = personne;
        return this;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Partenaire)) {
            return false;
        }
        return id != null && id.equals(((Partenaire) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Partenaire{" +
            "id=" + getId() +
            ", numero=" + getNumero() +
            "}";
    }
}
