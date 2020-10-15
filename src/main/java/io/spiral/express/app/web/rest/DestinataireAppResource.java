package io.spiral.express.app.web.rest;

import io.spiral.express.app.dto.DestinataireDTO;
import io.spiral.express.app.dto.DestinataireDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/express")
public class DestinataireAppResource {
    private Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping("destinataires")
    public ResponseEntity<DestinataireDTO> create(@RequestBody DestinataireDTO DestinataireDTO) {
        log.info("Requête REST pour créer un nouveau Destinataire");
        return null;
    }

    @PutMapping("destinataires")
    public ResponseEntity<DestinataireDTO> update(@RequestBody DestinataireDTO DestinataireDTO) {
        return null;
    }

    @GetMapping("destinataires/:id")
    public ResponseEntity<DestinataireDTO> getById(@PathVariable Long clientId) {
        return null;
    }

    @GetMapping("destinataires")
    public ResponseEntity<List<DestinataireDTO>> getAll() {
        return null;
    }

    @GetMapping("destinataires/clients/:id")
    public ResponseEntity<List<DestinataireDTO>> getAllByClientId(@PathVariable Long clientId) {
        return null;
    }
}
