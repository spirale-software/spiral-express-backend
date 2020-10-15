package io.spiral.express.app.web.rest;

import io.spiral.express.app.dto.PartenaireDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/express")
public class PartenaireAppResource {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping("partenaires")
    public ResponseEntity<PartenaireDTO> create(@RequestBody PartenaireDTO PartenaireDTO) {
        log.info("Requête REST pour créer un nouveau client");
        return null;
    }

    @PutMapping("partenaires")
    public ResponseEntity<PartenaireDTO> update(@RequestBody PartenaireDTO PartenaireDTO) {
        log.info("Requête REST pour modifier un client");
        return null;
    }

    @GetMapping("partenaires/:id")
    public ResponseEntity<PartenaireDTO> getById(@PathVariable Long clientId) {
        log.info("Requête REST pour obtenir un client avec pour id: {}", clientId);
        return null;
    }

    @GetMapping("partenaires")
    public ResponseEntity<List<PartenaireDTO>> getAll() {
        log.info("Requête REST pour obtenir tous les partenaires");

        return null;
    }
}
