package com.example.webflux.model.llmclient.gpt;


import com.fasterxml.jackson.annotation.JsonValue;

public enum GptMessageRole {
    SYSTEM,
    USER,
    ASSISTANT
    ;

    @JsonValue
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
