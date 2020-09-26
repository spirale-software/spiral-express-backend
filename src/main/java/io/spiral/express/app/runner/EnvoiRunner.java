package io.spiral.express.app.runner;

import io.spiral.express.app.repository.*;
import io.spiral.express.jhipster.domain.*;
import io.spiral.express.jhipster.domain.enumeration.StatutEnvoi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@Profile("dev")
public class EnvoiRunner implements ApplicationRunner {

    @Autowired
    private EnvoiAppRepository envoiAppRepository;
    @Autowired
    private ColiAppRepository coliAppRepository;
    @Autowired
    private DestinataireAppRepository destinataireAppRepository;
    @Autowired
    private ClientAppRepository clientAppRepository;
    @Autowired
    private PersonneAppRepository personneAppRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        envoiAppRepository.deleteAll();
        Client expediteur = createExpediteur();
        List<Destinataire> destinataires = createDestinataire(expediteur);
        Coli coli = createColi();
        createEnvoi(expediteur, destinataires.get(0), coli);
    }

    private Coli createColi() {
        Coli coli = new Coli();
        coli.setDescription("Document administratif");
        coli.setHauteur(9.00);
        coli.setLargeur(4.00);
        coli.setPoids(12.00);
        coli.setLongueur(5.00);
        return coliAppRepository.save(coli);
    }

    private void createEnvoi(Client expediteur, Destinataire destinataire, Coli coli) {
        Envoi envoi = new Envoi();
        envoi.setExpediteur(expediteur);
        envoi.setDestinataire(destinataire);
        envoi.setColi(coli);
        envoi.setDateCreation(LocalDate.now());
        envoi.setMontant(50.00);
        envoi.setReference("7895621459");
        envoi.setStatut(StatutEnvoi.PRISE_EN_CHARGE);
        envoi.setRapportLivraisaon("RAS");
        envoi.setRapportQuai("RAS");
        envoiAppRepository.save(envoi);
    }

    Client createExpediteur() {
        Personne personne = new Personne();
        personne.setNom("Nefer");
        personne.setPrenom("Ashanti");
        personne.setPays("Cameroun");
        personne.setEmail("ahn@spiral.com");
        personne.setTelephone("023756699855");
        personne.setAdresse("Douala, Km5");
        Client client = new Client();
        client.setNumero(456L);
        client.setPersonne(personneAppRepository.save(personne));
        return clientAppRepository.save(client);
    }

    List<Destinataire> createDestinataire(Client expediteur) {
        Personne personne = new Personne();
        personne.setNom("Toutankamon");
        personne.setPrenom("Claris");
        personne.setPays("Cameroun");
        personne.setEmail("tna@spiral.com");
        personne.setTelephone("023756699855");
        personne.setAdresse("Bafang, penka michel");
        Destinataire destinataire = new Destinataire();
        destinataire.setPersonne(personneAppRepository.save(personne));
        destinataire.setClient(expediteur);
        Destinataire dest1 = destinataireAppRepository.save(destinataire);

        return Arrays.asList(dest1);
    }

}
