package io.spiral.express.app.service;


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

    private final SpringTemplateEngine templateEngine;


    public GenerationPdf(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }


    public void genererPdfFromThymeleaf() {
        Locale locale = Locale.forLanguageTag("fr");
        Context context = new Context();
        context.setVariable(EnvoiTemplateVars.EXPEDITEUR_NOM, "The best");
        context.setVariable(EnvoiTemplateVars.EXPEDITEUR_PRENOM, "Gyle");
        String content = templateEngine.process("envoi", context);

        System.out.println(content);
    }

    public void genererPdf() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:templates");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String fileName = "envoi.ftl";
        Map<String, String> map = new HashMap<>();
        map.put("user", "Gérard");
        String html = FreemarkerUtils.loadFtlHtml(file, fileName, map);


        String pdfFile = file.getPath() + "\\envoi.pdf";
        System.out.println("pdfFile: " + pdfFile);
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File("envoi.pdf"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PdfFileUtils.savePdf(out, html);
    }

}
