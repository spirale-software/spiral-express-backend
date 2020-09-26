package io.spiral.express.app.service.error;

public class ElementNonExistantException extends RuntimeException {

    public ElementNonExistantException(String message) {
        super(message);
    }
}
