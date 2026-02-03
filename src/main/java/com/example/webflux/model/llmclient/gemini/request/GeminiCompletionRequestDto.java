package com.example.webflux.model.llmclient.gemini.request;


import com.example.webflux.model.llmclient.gemini.GeminiMessageRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeminiCompletionRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -7452853370797883118L;


    private GeminiMessageRole role;
    private String content;

    public GeminiCompletionRequestDto(String content) {
        this.content = content;
    }
}
