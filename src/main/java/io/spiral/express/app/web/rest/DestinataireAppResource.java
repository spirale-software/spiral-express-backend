package io.spiral.express.app.web.rest;

import io.spiral.express.app.dto.DestinataireDTO;
import io.spiral.express.app.service.DestinataireAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/express")
public class DestinataireAppResource {
    private Logger log = LoggerFactory.getLogger(getClass());

    private final DestinataireAppService destinataireAppService;

    public DestinataireAppResource(DestinataireAppService destinataireAppService) {
        this.destinataireAppService = destinataireAppService;
    }

    @PostMapping("destinataires")
    public ResponseEntity<DestinataireDTO> create(@RequestBody DestinataireDTO destinataireDTO) {
        log.info("Requête REST pour créer un nouveau Destinataire");
        return ResponseEntity.ok(destinataireAppService.sauver(destinataireDTO));
    }

    @PutMapping("destinataires")
    public ResponseEntity<DestinataireDTO> update(@RequestBody DestinataireDTO destinataireDTO) {
        log.info("Requête REST pour modifier Destinataire");
        return ResponseEntity.ok(destinataireAppService.modifier(destinataireDTO));
    }

    @GetMapping("destinataires/:id")
    public ResponseEntity<DestinataireDTO> getById(@PathVariable Long destinataireId) {
        return ResponseEntity.ok(destinataireAppService.findById(destinataireId));
    }

    @GetMapping("destinataires")
    public ResponseEntity<List<DestinataireDTO>> getAll() {
        return ResponseEntity.ok(destinataireAppService.findAll().getContent());
    }

    @GetMapping("destinataires/clients/:id")
    public ResponseEntity<List<DestinataireDTO>> getAllByClientId(@PathVariable Long clientId) {
        List<DestinataireDTO> destinataires = destinataireAppService.findAllByClientId(clientId);
        return ResponseEntity.ok(destinataires);
    }
}
