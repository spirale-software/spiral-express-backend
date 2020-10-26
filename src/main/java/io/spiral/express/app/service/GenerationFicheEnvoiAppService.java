package io.spiral.express.app.service;

import io.spiral.express.jhipster.domain.Envoi;

public interface GenerationFicheEnvoiAppService {

    byte[] genererPdfAsByteArray(Long envoiId);
}
