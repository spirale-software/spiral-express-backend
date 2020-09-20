package io.spiral.express.app.utils;

import com.itextpdf.html2pdf.HtmlConverter;

import java.io.FileOutputStream;

public class PdfFileUtils {

    public static void savePdf(String outputFileName, String html) {
        try {
            HtmlConverter.convertToPdf(html, new FileOutputStream(outputFileName));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
