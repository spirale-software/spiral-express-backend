package io.spiral.express.app.service;

import io.spiral.express.jhipster.domain.Envoi;

public interface GenerationFicheEnvoi {

    byte[] genererPdfAsByteArray(Long envoiId);
}
