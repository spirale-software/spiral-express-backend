package io.spiral.express.app.utils;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
//import com.liuwill.text.view.AsianFontProvider;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.charset.Charset;

public class PdfFileUtils {

    public static void savePdf(OutputStream out, String html) {
        Document document = new Document(PageSize.A4, 50, 50, 60, 60);
        //Document document = new Document(PageSize.A4, 110, 110, 120, 140);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
           // document.open();
            //XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(html));
            HtmlConverter.convertToPdf(html, new FileOutputStream("string-to-pdf.pdf"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          //  document.close();
        }
    }

    public static void saveChinesePdf(OutputStream out, String html) {
        Document document = new Document(PageSize.A4, 50, 50, 60, 60);
        //Document document = new Document(PageSize.A4, 110, 110, 120, 140);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(html.getBytes()), Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
