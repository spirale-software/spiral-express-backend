package io.spiral.express.app.service;

import io.spiral.express.jhipster.domain.*;
import io.spiral.express.jhipster.domain.enumeration.StatutEnvoi;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenerationPdfTest {

    
    @Test
    void generationPdf() {
        Client expediteur = createExpediteur();
        Destinataire destinataire = createDestinataire(expediteur);
        Coli coli = createColi();
        Envoi envoi = createEnvoi(expediteur, destinataire, coli);
    }

    private Coli createColi() {
        Coli coli = new Coli();
        coli.setDescription("Document administratif");
        coli.setHauteur(9.00);
        coli.setLargeur(4.00);
        coli.setPoids(12.00);
        coli.setLongueur(5.00);

        return coli;
    }

    private Envoi createEnvoi(Client expediteur, Destinataire destinataire, Coli coli) {
        Envoi envoi = new Envoi();
        envoi.setExpediteur(expediteur);
        envoi.setDestinataire(destinataire);
        envoi.setColi(coli);
//        envoi.setDateCreation(LocalDate.now());
        envoi.setMontant(50.00);
        envoi.setReference("7895621459");
        envoi.setStatut(StatutEnvoi.PRISE_EN_CHARGE);
        envoi.setRapportLivraisaon("RAS");
        envoi.setRapportQuai("RAS");
        return envoi;

    }

    Client createExpediteur() {
        Personne personne = new Personne();
        personne.setNom("Nefer");
        personne.setPrenom("Ashanti");
//        personne.setPays("Cameroun");
        personne.setEmail("ahn@spiral.com");
        personne.setTelephone("023756699855");
//        personne.setAdresse("Douala, Km5");
        Client client = new Client();
        client.setNumero(456L);
        client.setPersonne(personne);
        return client;
    }

    Destinataire createDestinataire(Client expediteur) {
        Personne personne = new Personne();
        personne.setNom("Toutankamon");
        personne.setPrenom("Claris");
//        personne.setPays("Cameroun");
        personne.setEmail("tna@spiral.com");
        personne.setTelephone("023756699855");
//        personne.setAdresse("Bafang, penka michel");
        Destinataire destinataire = new Destinataire();
        destinataire.setPersonne(personne);
        destinataire.setClient(expediteur);

        return destinataire;
    }
}
