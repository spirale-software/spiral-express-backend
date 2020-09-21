package io.spiral.express.app.domain;

import javax.persistence.Entity;

@Entity
public class Client extends Personne {
    private Long numero;

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }
}
