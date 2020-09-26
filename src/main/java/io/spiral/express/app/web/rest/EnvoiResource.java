package io.spiral.express.app.web.rest;

import io.spiral.express.app.dto.EnvoiDTO;
import io.spiral.express.app.service.EnvoiAppService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/express")
public class EnvoiResource {

    private final EnvoiAppService envoiAppService;

    public EnvoiResource(EnvoiAppService envoiAppService) {
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
        return ResponseEntity.ok(envoiAppService.getAll());
    }

    @GetMapping("/envois/{envoiId}")
    public ResponseEntity<EnvoiDTO> getById(@PathVariable Long envoiId) {
        return ResponseEntity.ok(envoiAppService.findById(envoiId));
    }

    @GetMapping(value = "/envoi/{envoiId}/generer-fiche-envoi", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] genererFicheEnvoi(@PathVariable Long envoiId) {
        return null;
    }
 }
