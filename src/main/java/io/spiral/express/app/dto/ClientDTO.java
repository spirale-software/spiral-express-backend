package io.spiral.express.app.dto;

public class ClientDTO extends PersonneDTO {
    private Long id;
    private Long numero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }
}
