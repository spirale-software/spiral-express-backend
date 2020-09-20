package io.spiral.express.app.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerationPdfTest {



    @Test
    void generationPdf() {
        GenerationPdf generationPdf = new GenerationPdf();
        generationPdf.genererPdf();
    }

    @Test
    void generationQrCode() {
        GenerationQrCode.genererQrCode();
    }
}
