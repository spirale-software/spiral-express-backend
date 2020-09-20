package io.spiral.express.app.service;

import com.itextpdf.html2pdf.HtmlConverter;
import io.spiral.express.jhipster.SpiralExpressApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpiralExpressApp.class)
class GenerationPdfTest {

    @Autowired
    private GenerationPdf generationPdf;

    @Test
    void test() {
        generationPdf.genererPdf();
    }
}
