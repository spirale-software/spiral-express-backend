package io.spiral.express.app.service;

import io.spiral.express.jhipster.SpiralExpressApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpiralExpressApp.class)
class GenerationPdfTest {

    @Autowired
    private GenerationPdf generationPdf;

    @Test
    void test() {
        generationPdf.genererPdfFromThymeleaf();
    }
}
