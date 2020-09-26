package io.spiral.express.jhipster.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import io.spiral.express.jhipster.domain.enumeration.StatutEnvoi;

/**
 * A Envoi.
 */
@Entity
@Table(name = "envoi")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Envoi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutEnvoi statut;

    @Column(name = "reference")
    private String reference;

    @Column(name = "rapport_quai")
    private String rapportQuai;

    @Column(name = "rapport_livraisaon")
    private String rapportLivraisaon;

    @OneToOne
    @JoinColumn(unique = true)
    private Coli coli;

    @OneToOne
    @JoinColumn(unique = true)
    private Client client;

    @OneToOne
    @JoinColumn(unique = true)
    private Destinataire destinataire;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public Envoi dateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public StatutEnvoi getStatut() {
        return statut;
    }

    public Envoi statut(StatutEnvoi statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(StatutEnvoi statut) {
        this.statut = statut;
    }

    public String getReference() {
        return reference;
    }

    public Envoi reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getRapportQuai() {
        return rapportQuai;
    }

    public Envoi rapportQuai(String rapportQuai) {
        this.rapportQuai = rapportQuai;
        return this;
    }

    public void setRapportQuai(String rapportQuai) {
        this.rapportQuai = rapportQuai;
    }

    public String getRapportLivraisaon() {
        return rapportLivraisaon;
    }

    public Envoi rapportLivraisaon(String rapportLivraisaon) {
        this.rapportLivraisaon = rapportLivraisaon;
        return this;
    }

    public void setRapportLivraisaon(String rapportLivraisaon) {
        this.rapportLivraisaon = rapportLivraisaon;
    }

    public Coli getColi() {
        return coli;
    }

    public Envoi coli(Coli coli) {
        this.coli = coli;
        return this;
    }

    public void setColi(Coli coli) {
        this.coli = coli;
    }

    public Client getClient() {
        return client;
    }

    public Envoi client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Destinataire getDestinataire() {
        return destinataire;
    }

    public Envoi destinataire(Destinataire destinataire) {
        this.destinataire = destinataire;
        return this;
    }

    public void setDestinataire(Destinataire destinataire) {
        this.destinataire = destinataire;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Envoi)) {
            return false;
        }
        return id != null && id.equals(((Envoi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Envoi{" +
            "id=" + getId() +
            ", dateCreation='" + getDateCreation() + "'" +
            ", statut='" + getStatut() + "'" +
            ", reference='" + getReference() + "'" +
            ", rapportQuai='" + getRapportQuai() + "'" +
            ", rapportLivraisaon='" + getRapportLivraisaon() + "'" +
            "}";
    }
}
