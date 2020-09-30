package io.spiral.express.app.web.rest;

import io.spiral.express.app.dto.EnvoiDTO;
import io.spiral.express.app.service.EnvoiAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("api/express")
public class EnvoiAppResource {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final EnvoiAppService envoiAppService;

    public EnvoiAppResource(EnvoiAppService envoiAppService) {
        this.envoiAppService = envoiAppService;
    }

    @PostMapping("/envois")
    public ResponseEntity<EnvoiDTO> create(@RequestBody EnvoiDTO envoiDTO) {
        return null;
    }

    @PutMapping("/envois")
    public ResponseEntity<EnvoiDTO> update(@RequestBody EnvoiDTO envoiDTO) {
        return null;
    }

    @GetMapping("/envois")
    public ResponseEntity<List<EnvoiDTO>> getAll() {
        return ResponseEntity.ok(envoiAppService.findAll());
    }

    @GetMapping("/envois/{envoiId}")
    public ResponseEntity<EnvoiDTO> getById(@PathVariable Long envoiId) {
        return ResponseEntity.ok(envoiAppService.findById(envoiId));
    }

    @GetMapping("/envois/{reference}/reference")
    public ResponseEntity<EnvoiDTO> getByReference(@PathVariable String reference) {
        return ResponseEntity.ok(envoiAppService.findByReference(reference));
    }

    @GetMapping(value = "/envois/{envoiId}/generer-fiche-envoi", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> genererFicheEnvoi(@PathVariable Long envoiId) {
        log.info("Requête REST, pour générer la fiche envoi: {}", envoiId);

        Path pdfPath = Paths.get("C:\\Users\\MediaMonster\\Desktop\\Projets\\spiral-express-backend\\envoi.pdf");

        byte[] pdf = null;
        try {
           pdf = Files.readAllBytes(pdfPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(pdf);
    }
 }
