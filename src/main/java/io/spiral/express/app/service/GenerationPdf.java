package io.spiral.express.app.service;


import com.itextpdf.html2pdf.HtmlConverter;
import io.spiral.express.app.utils.EnvoiTemplateVars;
import io.spiral.express.app.utils.FreemarkerUtils;
import io.spiral.express.app.utils.PdfFileUtils;
import io.spiral.express.jhipster.domain.Envoi;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class GenerationPdf {

    public void genererPdf(Envoi envoi) {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:templates/envoi");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String fileName = "envoi.ftl";
        String html = FreemarkerUtils.loadFtlHtml(file, fileName, getVariables(envoi));

        String outFile = "envoi.pdf";
        PdfFileUtils.savePdf(outFile, html);
    }

    private Map<String, String> getVariables(Envoi envoi) {
        GenerationQrCode.genererQrCode(envoi.getReference());

        Map<String, String> map = new HashMap<>();
        map.put(EnvoiTemplateVars.EXPEDITEUR_NOM, envoi.getExpediteur().getPersonne().getNom());
        map.put(EnvoiTemplateVars.EXPEDITEUR_PRENOM, envoi.getExpediteur().getPersonne().getPrenom());
       // map.put(EnvoiTemplateVars.EXPEDITEUR_ADRESSE, envoi.getExpediteur().getPersonne().getAdresse());

        map.put(EnvoiTemplateVars.DESTINATAIRE_NOM, envoi.getDestinataire().getPersonne().getNom());
        map.put(EnvoiTemplateVars.DESTINATAIRE_PRENOM, envoi.getDestinataire().getPersonne().getPrenom());
       // map.put(EnvoiTemplateVars.DESTINATAIRE_ADRESSE, envoi.getDestinataire().getPersonne().getAdresse());

        map.put(EnvoiTemplateVars.COLI_NBR_UNITE, "1");
        map.put(EnvoiTemplateVars.COLI_POIDS, envoi.getColi().getPoids().toString());
        Double volume = envoi.getColi().getHauteur() * envoi.getColi().getLargeur() * envoi.getColi().getLongueur();
        map.put(EnvoiTemplateVars.COLI_VOLUME, volume.toString());
        map.put(EnvoiTemplateVars.COLI_DESCRIPTION, envoi.getColi().getDescription());


        map.put(EnvoiTemplateVars.LIEN_QR_CODE, "C:\\Users\\MediaMonster\\Desktop\\Projets\\spiral-express-backend\\src\\main\\resources\\qrcode\\qrcode-01.png");
        map.put(EnvoiTemplateVars.ENVOI_REFERENCE, envoi.getReference());

        return map;
    }

}
