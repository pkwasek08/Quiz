package pl.project.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageResponse {
    @JsonProperty
    String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
