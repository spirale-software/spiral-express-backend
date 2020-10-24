package io.spiral.express.app.dto;

import io.spiral.express.jhipster.domain.enumeration.StatutEnvoi;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class EnvoiDTO {
    private Long id;
    private ZonedDateTime dateCreation;
    private StatutEnvoi statut;
    private String reference;
    private String rapportQuai;
    private String rapportLivraisaon;
    private Double montant;
    private ColiDTO coli;
    private ClientDTO expediteur;
    private DestinataireDTO destinataire;
    private PartenaireDTO partenaire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(ZonedDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public StatutEnvoi getStatut() {
        return statut;
    }

    public void setStatut(StatutEnvoi statut) {
        this.statut = statut;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getRapportQuai() {
        return rapportQuai;
    }

    public void setRapportQuai(String rapportQuai) {
        this.rapportQuai = rapportQuai;
    }

    public String getRapportLivraisaon() {
        return rapportLivraisaon;
    }

    public void setRapportLivraisaon(String rapportLivraisaon) {
        this.rapportLivraisaon = rapportLivraisaon;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public ColiDTO getColi() {
        return coli;
    }

    public void setColi(ColiDTO coli) {
        this.coli = coli;
    }

    public ClientDTO getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(ClientDTO expediteur) {
        this.expediteur = expediteur;
    }

    public DestinataireDTO getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(DestinataireDTO destinataire) {
        this.destinataire = destinataire;
    }

    public PartenaireDTO getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(PartenaireDTO partenaire) {
        this.partenaire = partenaire;
    }

    @Override
    public String toString() {
        return "EnvoiDTO{" +
            "id=" + id +
            ", dateCreation=" + dateCreation +
            ", statut=" + statut +
            ", reference='" + reference + '\'' +
            ", rapportQuai='" + rapportQuai + '\'' +
            ", rapportLivraisaon='" + rapportLivraisaon + '\'' +
            ", montant=" + montant +
            ", coli=" + coli +
            ", expediteur=" + expediteur +
            ", destinataire=" + destinataire +
            ", partenaire=" + partenaire +
            '}';
    }
}
