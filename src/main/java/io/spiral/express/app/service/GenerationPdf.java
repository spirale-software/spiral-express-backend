package io.spiral.express.app.service;


import com.itextpdf.html2pdf.HtmlConverter;
import io.spiral.express.app.utils.EnvoiTemplateVars;
import io.spiral.express.app.utils.FreemarkerUtils;
import io.spiral.express.app.utils.PdfFileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class GenerationPdf {

    public void genererPdf() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:templates/envoi");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String fileName = "envoi.ftl";
        String html = FreemarkerUtils.loadFtlHtml(file, fileName, getVariables());

        String outFile = "envoi.pdf";
        PdfFileUtils.savePdf(outFile, html);
    }

    private Map<String, String> getVariables() {
        Map<String, String> map = new HashMap<>();
        map.put(EnvoiTemplateVars.EXPEDITEUR_NOM, "Yannick");
        map.put(EnvoiTemplateVars.EXPEDITEUR_PRENOM, "Gérard");
        map.put(EnvoiTemplateVars.EXPEDITEUR_ADRESSE, "Rue des aventuriers, 1000 Bruxelles, Belgique.");
        return map;
    }

}
