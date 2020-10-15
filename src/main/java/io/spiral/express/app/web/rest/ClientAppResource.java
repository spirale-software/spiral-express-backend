package io.spiral.express.app.web.rest;

import io.spiral.express.app.dto.ClientDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/express")
public class ClientAppResource {
    private Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping("clients")
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO clientDTO) {
        log.info("Requête REST pour créer un nouveau client");
        return null;
    }

    @PutMapping("clients")
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO clientDTO) {
        log.info("Requête REST pour modifier un client");

        return null;
    }

    @GetMapping("clients/:id")
    public ResponseEntity<ClientDTO> getById(@PathVariable Long clientId) {
        log.info("Requête REST pour obtenir un client avec pour id: {}", clientId);
        return null;
    }

    @GetMapping("clients")
    public ResponseEntity<List<ClientDTO>> getAll() {
        log.info("Requête REST pour obtenir tous les clients");

        return null;
    }
}
