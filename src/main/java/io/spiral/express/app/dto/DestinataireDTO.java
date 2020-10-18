package io.spiral.express.app.dto;

public class DestinataireDTO extends PersonneDTO {
    private ClientDTO client;

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }
}
