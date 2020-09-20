package io.spiral.express.app.web.rest;

import io.spiral.express.app.dto.EnvoiDTO;
import io.spiral.express.app.service.EnvoiService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/express")
public class EnvoiResource {

    private final EnvoiService envoiService;

    public EnvoiResource(EnvoiService envoiService) {
        this.envoiService = envoiService;
    }

    @PostMapping("/envoi")
    public ResponseEntity create() {
        return null;
    }

    @PutMapping("/envoi")
    public ResponseEntity update() {
        return null;
    }

    @GetMapping("/envoi")
    public ResponseEntity<List<EnvoiDTO>> getAll() {
        System.out.println("La connexion fonctionne!");
        return ResponseEntity.ok(null);
    }

    @GetMapping("/envoi/{envoiId}")
    public ResponseEntity getById() {
        return null;
    }

    @GetMapping(value = "/envoi/{envoiId}/generer-fiche-envoi", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] genererFicheEnvoi(@PathVariable Long envoiId) {
        return null;
    }
 }
