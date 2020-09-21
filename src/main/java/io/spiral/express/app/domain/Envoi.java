package io.spiral.express.app.domain;

import io.spiral.express.app.domain.enumeration.StatutEnvoi;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Envoi {
    private LocalDate dateCreation;
    private StatutEnvoi statut;
    private String reference;
    private Coli coli;
    private Client expediteur;
    private Personne destinataire;
    private String rapportQuai;
    private String rapportLivraisaon;

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
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

    public Coli getColi() {
        return coli;
    }

    public void setColi(Coli coli) {
        this.coli = coli;
    }

    public Client getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Client expediteur) {
        this.expediteur = expediteur;
    }

    public Personne getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Personne destinataire) {
        this.destinataire = destinataire;
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
}
