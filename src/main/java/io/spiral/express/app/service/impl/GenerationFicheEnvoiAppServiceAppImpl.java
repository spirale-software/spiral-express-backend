package io.spiral.express.app.service.impl;

import com.itextpdf.html2pdf.HtmlConverter;
import io.spiral.express.app.repository.EnvoiAppRepository;
import io.spiral.express.app.service.GenerationFicheEnvoiAppService;
import io.spiral.express.app.service.GenerationQrCode;
import io.spiral.express.app.service.error.ElementNonExistantException;
import io.spiral.express.app.utils.EnvoiTemplateVars;
import io.spiral.express.app.utils.FreemarkerUtils;
import io.spiral.express.jhipster.domain.Adresse;
import io.spiral.express.jhipster.domain.Envoi;
import io.spiral.express.jhipster.domain.Personne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class GenerationFicheEnvoiAppServiceAppImpl implements GenerationFicheEnvoiAppService {
    private Logger log = LoggerFactory.getLogger(getClass());

    private final EnvoiAppRepository envoiAppRepository;

    public GenerationFicheEnvoiAppServiceAppImpl(EnvoiAppRepository envoiAppRepository) {
        this.envoiAppRepository = envoiAppRepository;
    }

    @Override
    public byte[] genererPdfAsByteArray(Long envoiId) {
        log.info("Generer le pdf de la fiche envoi avec pour id: {}", envoiId);

        Envoi envoi = envoiAppRepository
            .findById(envoiId)
            .orElseThrow(() -> new ElementNonExistantException("Pas d'Envoi avec cet ID: " + envoiId));

        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:templates");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String fileName = "envoi.ftl";
        String html = FreemarkerUtils.loadFtlHtml(file, fileName, getVariables(envoi));

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            HtmlConverter.convertToPdf(html, stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stream.toByteArray();
    }

    private Map<String, String> getVariables(Envoi envoi) {
        File qrCodeFile = null;
        try {
            qrCodeFile = ResourceUtils.getFile("classpath:qrcode/qrcode-01.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        GenerationQrCode.genererQrCode(envoi.getReference(), qrCodeFile.getPath());

        Map<String, String> map = new HashMap<>();
//        map.put(EnvoiTemplateVars.EXPEDITEUR_NOM, envoi.getExpediteur().getPersonne().getNom());
//        map.put(EnvoiTemplateVars.EXPEDITEUR_PRENOM, envoi.getExpediteur().getPersonne().getPrenom());
        map.put(EnvoiTemplateVars.EXPEDITEUR_FULL_NAME, getFullName(envoi.getExpediteur().getPersonne()));
        map.put(EnvoiTemplateVars.EXPEDITEUR_ADRESSE_1, getAdresse1(envoi.getExpediteur().getPersonne()));
        map.put(EnvoiTemplateVars.EXPEDITEUR_ADRESSE_2, getAdresse2(envoi.getExpediteur().getPersonne()));
        // map.put(EnvoiTemplateVars.EXPEDITEUR_ADRESSE, envoi.getExpediteur().getPersonne().getAdresse());

//        map.put(EnvoiTemplateVars.DESTINATAIRE_NOM, envoi.getDestinataire().getPersonne().getNom());
//        map.put(EnvoiTemplateVars.DESTINATAIRE_PRENOM, envoi.getDestinataire().getPersonne().getPrenom());
        map.put(EnvoiTemplateVars.DESTINATAIRE_FULL_NAME, getFullName(envoi.getDestinataire().getPersonne()));
        map.put(EnvoiTemplateVars.DESTINATAIRE_ADRESSE_1, getAdresse1(envoi.getDestinataire().getPersonne()));
        map.put(EnvoiTemplateVars.DESTINATAIRE_ADRESSE_2, getAdresse2(envoi.getDestinataire().getPersonne()));

        map.put(EnvoiTemplateVars.COLI_NBR_UNITE, "1");
        map.put(EnvoiTemplateVars.COLI_POIDS, envoi.getColi().getPoids().toString());
        Double volume = envoi.getColi().getHauteur() * envoi.getColi().getLargeur() * envoi.getColi().getLongueur();
        map.put(EnvoiTemplateVars.COLI_VOLUME, volume.toString());
        map.put(EnvoiTemplateVars.COLI_DESCRIPTION, envoi.getColi().getDescription());


//        map.put(EnvoiTemplateVars.LIEN_QR_CODE, "C:\\Users\\MediaMonster\\Desktop\\Projets\\spiral-express-backend\\src\\main\\resources\\qrcode\\qrcode-01.png");
        map.put(EnvoiTemplateVars.LIEN_QR_CODE, qrCodeFile.getPath());
        map.put(EnvoiTemplateVars.ENVOI_REFERENCE, envoi.getReference());

        return map;
    }

    private String getFullName(Personne personne) {
       return new StringBuilder()
           .append(personne.getNom().toUpperCase())
           .append(" ")
           .append(personne.getPrenom().toUpperCase()).toString();
    }

    private String getAdresse1(Personne personne) {
        Adresse adresse = personne.getAdresse();
        return new StringBuilder().append(adresse.getRue().toUpperCase()).toString();
    }

    private String getAdresse2(Personne personne) {
        Adresse adresse = personne.getAdresse();
        return new StringBuilder()
            .append(adresse.getCodePostal())
            .append(" ")
            .append(adresse.getVille().toUpperCase())
            .append(", ")
            .append(adresse.getPays()).toString();
    }
}
