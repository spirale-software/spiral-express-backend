package io.spiral.express.app.web.rest;

import io.spiral.express.app.dto.PartenaireDTO;
import io.spiral.express.app.service.PartenaireAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/express")
public class PartenaireAppResource {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final PartenaireAppService partenaireAppService;

    public PartenaireAppResource(PartenaireAppService partenaireAppService) {
        this.partenaireAppService = partenaireAppService;
    }

    @PostMapping("partenaires")
    public ResponseEntity<PartenaireDTO> create(@RequestBody PartenaireDTO partenaireDTO) {
        log.info("Requête REST pour créer un nouveau client");
        PartenaireDTO dto = partenaireAppService.sauver(partenaireDTO);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("partenaires")
    public ResponseEntity<PartenaireDTO> update(@RequestBody PartenaireDTO partenaireDTO) {
        log.info("Requête REST pour modifier un client");
        PartenaireDTO dto = partenaireAppService.modifier(partenaireDTO);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("partenaires/{partenaireId}")
    public ResponseEntity<PartenaireDTO> getById(@PathVariable Long partenaireId) {
        log.info("Requête REST pour obtenir un partenaire avec pour id: {}", partenaireId);
        PartenaireDTO dto = partenaireAppService.findById(partenaireId);
       return ResponseEntity.ok(dto);
    }

    @GetMapping("partenaires")
    public ResponseEntity<List<PartenaireDTO>> getAll() {
        log.info("Requête REST pour obtenir tous les partenaires");
        Page<PartenaireDTO> page = partenaireAppService.findAll();
        return ResponseEntity.ok(page.getContent());
    }
}
