package io.spiral.express.app.service;

public interface GenerationFicheEnvoiAppService {

    byte[] genererPdfAsByteArray(Long envoiId);
}
