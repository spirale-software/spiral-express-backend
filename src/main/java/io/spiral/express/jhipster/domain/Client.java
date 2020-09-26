package io.spiral.express.jhipster.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Client implements Serializable {

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

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Destinataire> destinataires = new HashSet<>();

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

    public Client numero(Long numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Personne getPersonne() {
        return personne;
    }

    public Client personne(Personne personne) {
        this.personne = personne;
        return this;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Set<Destinataire> getDestinataires() {
        return destinataires;
    }

    public Client destinataires(Set<Destinataire> destinataires) {
        this.destinataires = destinataires;
        return this;
    }

    public Client addDestinataire(Destinataire destinataire) {
        this.destinataires.add(destinataire);
        destinataire.setClient(this);
        return this;
    }

    public Client removeDestinataire(Destinataire destinataire) {
        this.destinataires.remove(destinataire);
        destinataire.setClient(null);
        return this;
    }

    public void setDestinataires(Set<Destinataire> destinataires) {
        this.destinataires = destinataires;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", numero=" + getNumero() +
            "}";
    }
}
