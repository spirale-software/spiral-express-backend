package io.spiral.express.app.service;


import com.itextpdf.html2pdf.HtmlConverter;
import io.spiral.express.app.utils.EnvoiTemplateVars;
import io.spiral.express.app.utils.FreemarkerUtils;
import io.spiral.express.app.utils.PdfFileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
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

        ClassLoader classLoader = getClass().getClassLoader();
        File pdfFile = new File(classLoader.getResource(".").getFile() + "/envoi.pdf");
        System.out.println("pdfFile: " + pdfFile);
        OutputStream out = null;
        try {

            out = new FileOutputStream(new File("envoi.pdf"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PdfFileUtils.savePdf(out, html);
    }

    private Map<String, String> getVariables() {
        Map<String, String> map = new HashMap<>();
        map.put(EnvoiTemplateVars.EXPEDITEUR_NOM, "Yannick");
        map.put(EnvoiTemplateVars.EXPEDITEUR_NOM, "Gérard");
        return map;
    }

}
